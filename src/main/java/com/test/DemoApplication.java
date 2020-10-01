package com.test;

import com.test.domain.Abonne;
import com.test.domain.Adresse;
import com.test.domain.Contrat;
import com.test.enumerations.ConditionDeResidence;
import com.test.repository.AbonneRepository;
import com.test.repository.AdresseRepository;
import com.test.repository.ContratAuditRepository;
import com.test.repository.ContratRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements ApplicationRunner {


    @Autowired
    ContratAuditRepository contratAuditRepository;
    @Autowired
    private AbonneRepository abonneRepository;
    @Autowired
    private AdresseRepository adresseRepository;
    @Autowired
    private ContratRepository contratRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

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

//		contrat = contratRepository.findById(1L).get();
//
//
//		contratService.changerAdresse(contrat.getId(), adresse2);


    }


}