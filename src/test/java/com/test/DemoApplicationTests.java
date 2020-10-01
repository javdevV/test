package com.test;

import com.test.domain.Abonne;
import com.test.domain.Adresse;
import com.test.domain.Contrat;
import com.test.enumerations.ConditionDeResidence;
import com.test.repository.AbonneRepository;
import com.test.repository.AdresseRepository;
import com.test.repository.ContratRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
public class DemoApplicationTests extends AbstractTestNGSpringContextTests {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    ContratRepository contratRepository;
    @Autowired
    AdresseRepository adresseRepository;
    @Autowired
    AbonneRepository abonneRepository;

    private MockMvc mockMvc;

    @BeforeClass
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        Abonne abonne = new Abonne();
        abonne.setId(1L);
        abonne.setNom("ABo1");
        abonne.setPrenom("AB1");

        abonneRepository.save(abonne);

        Adresse adresse = new Adresse();
        adresse.setId(1L);
        adresse.setNumero("12");
        adresse.setVoie("adresse");
        adresse.setVille("Ville1");
        adresse.setActive(true);
        adresse.setPays("France");
        adresse.setCondition(ConditionDeResidence.SDE);
        adresseRepository.save(adresse);

        Adresse adresse2 = new Adresse();
        adresse2.setId(2L);
        adresse2.setNumero("22");
        adresse2.setVoie("adresse2");
        adresse2.setVille("Ville2");
        adresse2.setActive(true);
        adresse2.setPays("France");
        adresse2.setCondition(ConditionDeResidence.ADE);
        adresseRepository.save(adresse2);

        adresse = adresseRepository.findById(1L).get();
        Contrat contrat = new Contrat();
        contrat.setId(1L);
        contrat.setAbonne(abonne);
        contrat.setAdresse(adresse);
        contrat.setCanal("canal1");
        contratRepository.save(contrat);
    }


    @Test(priority=0)
    public void test_change_adresse() throws Exception{
        mockMvc.perform(put("/contrats/1")//
                .content("{\"id\":\"2\",\"numero\":\"22\",\"voie\":\"adresse2\",\"ville\":\"Ville2\",\"active\":true,\"pays\":\"France\",\"condition\":\"ADE\"}")//
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)//
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))//
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.adresse.id").value("2"));
    }

    @Test(priority=1)
    public void test_get_last_adresse_audit() throws Exception {
        mockMvc.perform(get("/contrats/1/audit")).andExpect(status().isOk())
                .andExpect(jsonPath("$.adresse.id").value("1"))
                .andExpect(jsonPath("$.adresse.condition").value(ConditionDeResidence.SDE.name()));
    }


}