package com.test.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.enumerations.ConditionDeResidence;

import javax.persistence.*;


@Entity
public class Adresse {
    @Id
    private Long id;
    private String numero;
    private String voie;
    @JsonIgnore
    private String etage;
    @JsonIgnore
    private String codePostal;
    private String ville;
    private String pays;
    private boolean active;
    @Enumerated(EnumType.STRING)
    private ConditionDeResidence condition;
    @JsonIgnore
    @OneToOne(mappedBy = "adresse")
    private Contrat contrat;

    public Adresse() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getVoie() {
        return voie;
    }

    public void setVoie(String voie) {
        this.voie = voie;
    }

    public String getEtage() {
        return etage;
    }

    public void setEtage(String etage) {
        this.etage = etage;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public ConditionDeResidence getCondition() {
        return condition;
    }

    public void setCondition(ConditionDeResidence condition) {
        this.condition = condition;
    }

    public Contrat getContrat() {
        return contrat;
    }

    public void setContrat(Contrat contrat) {
        this.contrat = contrat;
    }

    @Override
    public String toString() {
        return "Adresse [id=" + id + ", numero=" + numero + ", voie=" + voie + ", etage=" + etage + ", codePostal="
                + codePostal + ", ville=" + ville + ", pays=" + pays + ", active=" + active + ", condition=" + condition
                + "]";
    }


}
