package com.test;

import com.test.enumerations.ConditionDeResidence;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(CucumberWithSerenity.class)
@ContextConfiguration(loader = SpringBootContextLoader.class)
@WebAppConfiguration
@SpringBootTest(classes = DemoApplication.class)
public class Stepdefs {


    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockServer;

    @Before
    public void setUpMockServer() {
        mockServer = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Given("^un abonné avec une adresse principale active en France$")
    public void un_abonné_avec_une_adresse_principale_active_en_France() throws Throwable {
        mockServer.perform(get("/contrats/1")).andExpect(status().isOk())//

                .andExpect(jsonPath("$.id").value("1"))//
                .andExpect(jsonPath("$.canal").value("canal1"))//
                .andExpect(jsonPath("$.adresse.id").value("1"))//
                .andExpect(jsonPath("$.adresse.ville").value("Ville1"));
    }

    @When("^le conseiller connecté à canal modifie l'adresse de l'abonné condition$")
    public void le_conseiller_connecté_à_canal_modifie_l_adresse_de_l_abonné_condition() throws Throwable {
        mockServer.perform(put("/contrats/1")//
                .content(
                        "{\"id\":\"2\",\"numero\":\"22\",\"voie\":\"adresse2\",\"ville\":\"Ville2\",\"active\":true,\"pays\":\"France\",\"condition\":\"ADE\"}")//
                .contentType(MediaType.APPLICATION_JSON)//
                .accept(MediaType.APPLICATION_JSON))//
                .andExpect(status().isOk()).andExpect(jsonPath("$.adresse.id").value("2"));
    }

    @Then("^l'adresse de l'abonné modifiée est enregistrée sur l'ensemble des contrats de l'abonné$")
    public void l_adresse_de_l_abonné_modifiée_est_enregistrée_sur_l_ensemble_des_contrats_de_l_abonné()
            throws Throwable {
        mockServer.perform(get("/contrats/1")).andExpect(status().isOk())//
                .andExpect(content().contentType("application/json"))//
                .andExpect(jsonPath("$.id").value("1"))//
                .andExpect(jsonPath("$.canal").value("canal1"))//
                .andExpect(jsonPath("$.adresse.id").value("2"))//
                .andExpect(jsonPath("$.adresse.ville").value("Ville2"));
    }

    @And("^un mouvement de modification d'adresse est créés$")
    public void un_mouvement_de_modification_d_adresse_est_créés() throws Throwable {
        mockServer.perform(get("/contrats/1/audit")).andExpect(status().isOk())
                .andExpect(jsonPath("$.adresse.id").value("1")).andExpect(jsonPath("$.adresse.ville").value("Ville1"))
                .andExpect(jsonPath("$.adresse.condition").value(ConditionDeResidence.SDE.name()));
    }
}
