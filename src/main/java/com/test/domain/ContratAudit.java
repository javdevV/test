package com.test.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CONTRAT_Audit")
public class ContratAudit {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "CONT_ID")
    private Long idContrat;
    private String reference;
    private String canal;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ABN_ID")
    private Abonne abonne;
    @OneToOne
    @JoinColumn(name = "ADR_ID")
    private Adresse adresse;

    private Date dateChangement;

    public ContratAudit() {

    }

    public ContratAudit(Long idContrat, String reference, String canal, Abonne abonne, Adresse adresse) {

        this.idContrat = idContrat;
        this.reference = reference;
        this.canal = canal;
        this.abonne = abonne;
        this.adresse = adresse;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdContrat() {
        return idContrat;
    }

    public void setIdContrat(Long idContrat) {
        this.idContrat = idContrat;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }

    public Abonne getAbonne() {
        return abonne;
    }

    public void setAbonne(Abonne abonne) {
        this.abonne = abonne;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Date getDateChangement() {
        return dateChangement;
    }

    public void setDateChangement(Date dateChangement) {
        this.dateChangement = dateChangement;
    }


}
