package com.test.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Abonne {
    @Id
    private Long id;
    private String nom;
    private String prenom;
    @OneToMany(mappedBy = "abonne")
    private List<Contrat> contrats;

    public Abonne() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public List<Contrat> getContrats() {
        return contrats;
    }

    public void setContrats(List<Contrat> contrats) {
        this.contrats = contrats;
    }

    @Override
    public String toString() {
        return "Abonne [id=" + id + ", nom=" + nom + ", prenom=" + prenom + "]";
    }


}
