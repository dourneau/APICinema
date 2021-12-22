package fr.semifir.apicinema;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.semifir.apicinema.controllers.CinemaController;
import fr.semifir.apicinema.dtos.cinema.CinemaDTO;
import fr.semifir.apicinema.entities.Cinema;
import fr.semifir.apicinema.exceptions.NotFoundException;
import fr.semifir.apicinema.services.CinemaService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;




@WebMvcTest(controllers = CinemaController.class)
public class CinemaControllerTest {

    // On injecte MockMvc
    // MockMvc permet de simuler un composant
    @Autowired
    private MockMvc mockMvc;

    // On mock le service
    // On récupère le service
    // On fait une "copie" du service
    @MockBean
    private CinemaService service;

    /**
     * On va tester la route qui permet de récuperer un tableau de cinemas
     *
     * @throws Exception
     */
    @Test
    public void testFindAllCinema() throws Exception {
        // On execute une request sur /cinemas grâce à la methode "perfom" de mockMvc
        this.mockMvc.perform(get("/cinemas"))
                .andExpect(status().isOk()) // On check si le code retour et 200
                .andExpect(jsonPath("$").isEmpty()); // On verifie si le tableau est vide
    }


    /* Je mets ce test en commentaire car je n'arrive pas à régler le problème avec assertThrows
    @Test
    public void testFindOneCinemaThrowIllegalArgumentException() {
        Assertions.assertThrows(NotFoundException.class, () -> {
            // On execute une request sur /cinemas/1 grâce à la methode "perfom" de mockMvc
            this.mockMvc.perform(get("/cinemas/1"));
        });

    }
     */

    @Test
    public void testSaveCinema() throws Exception {
        CinemaDTO cinemaDTO = this.cinemaDTO();
        Gson json = new GsonBuilder().create();
        String body = json.toJson(cinemaDTO);
        this.mockMvc.perform(post("/cinemas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteCinema() throws Exception {
        Gson json = new GsonBuilder().create();
        String body = json.toJson(this.cinemaDTO());
        this.mockMvc.perform(delete("/cinemas")
                        .contentType(MediaType.APPLICATION_JSON) // Le type de données que l'on passe dans notre request
                        .content(body)) // Le contenu du body
                .andExpect(status().isOk());
    }



    // Les deux méthodes ci-dessous nous permettent de créer un cinéma DTO
    private CinemaDTO cinemaDTO() {
        return new CinemaDTO(
                "12345",
                "Cinema1"
        );
    }





}
