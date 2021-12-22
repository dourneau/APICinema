package fr.semifir.apicinema;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.semifir.apicinema.controllers.CinemaController;
import fr.semifir.apicinema.controllers.SalleController;
import fr.semifir.apicinema.dtos.cinema.CinemaDTO;
import fr.semifir.apicinema.dtos.salle.SalleDTO;
import fr.semifir.apicinema.services.CinemaService;
import fr.semifir.apicinema.services.SalleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = SalleController.class)
public class SalleControllerTest {

    // On injecte MockMvc
    // MockMvc permet de simuler un composant
    @Autowired
    private MockMvc mockMvc;

    // On mock le service
    // On récupère le service
    // On fait une "copie" du service
    @MockBean
    private SalleService service;

    /**
     * On va tester la route qui permet de récuperer un tableau de cinemas
     *
     * @throws Exception
     */
    @Test
    public void testFindAllSalle() throws Exception {
        // On execute une request sur /salles grâce à la methode "perfom" de mockMvc
        this.mockMvc.perform(get("/salles"))
                .andExpect(status().isOk()) // On check si le code retour et 200
                .andExpect(jsonPath("$").isEmpty()); // On verifie si le tableau est vide
    }


    /**
     * On vérifie si la route qui permet de retrouver une salle
     * renvoi bien une salle
     * @throws Exception
     */
    @Test
    public void testFindOneSalle() throws Exception {
        /**
         * START
         * On mock le service pour qu'il renvoi une salleDTO
         * On simule l"'existence d'une salleDTO dans le BDD
         */
        /// On créé une salleDTO
        SalleDTO salleDTO = this.salleDTO();
        // On appelle la methode “given" de BDDMockito pour mocker le service
        // On lui passe en parametre quelle méthode du service il faut mocker
        BDDMockito.given(service.findByID("12345")
                .willReturn(Optional.of(salleDTO)); // On indique à BDDMockito : "que va tu répondre quand on appelle cette methode ?"
        // Et on lui passe en paramètre sa réponse


    }

    // La méthode ci-dessous nous permet de créer une salleDTO
    private SalleDTO salleDTO() {
        return new SalleDTO(
                "12345",
                1,
                1,
                {"11","Cinema1"}
        );
    }





}
