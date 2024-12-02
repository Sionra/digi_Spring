package fr.digi_hello.test;

import fr.digi_hello.classes.Departement;
import fr.digi_hello.classes.Ville;
import fr.digi_hello.repositorys.VilleRepository;
import fr.digi_hello.services.VilleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class VilleServiceImplTest {

    @Autowired
    private VilleService villeService;

    @MockitoBean
    private VilleRepository villeRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getVilleNom() throws Exception {
        Departement d = new Departement();
        Ville ville = new Ville();
        ville.setNom("Toulouse");
        ville.setNbHabitants(475438);
        d.setNom("Haute-Garonne");
        d.setCode("31");
        ville.setDepartement(d);

        when(villeRepository.findByNom("Toulouse")).thenReturn(ville);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/villes/name/Toulouse")).andDo(print())
                .andExpect((status().isOk()))
                .andExpect(content().string(containsString("Toulouse")))
                .andExpect(jsonPath("nom", is("Toulouse")))
                .andExpect(jsonPath("nbHabitants", is(475438)));
    }
}
