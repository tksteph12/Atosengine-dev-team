/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.atos.ressources;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author a573405
 */
@Entity
@Table(name = "personne")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personne.findAll", query = "SELECT p FROM Personne p"),
    @NamedQuery(name = "Personne.findByCodeDas", query = "SELECT p FROM Personne p WHERE p.codeDas = :codeDas"),
    @NamedQuery(name = "Personne.findByNom", query = "SELECT p FROM Personne p WHERE p.nom = :nom"),
    @NamedQuery(name = "Personne.findByPrenom", query = "SELECT p FROM Personne p WHERE p.prenom = :prenom"),
    @NamedQuery(name = "Personne.findByCodeGcm", query = "SELECT p FROM Personne p WHERE p.codeGcm = :codeGcm"),
    @NamedQuery(name = "Personne.findByNiveau", query = "SELECT p FROM Personne p WHERE p.niveau = :niveau")})
public class Personne implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "code_das")
    private String codeDas;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "nom")
    private String nom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "prenom")
    private String prenom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "code_gcm")
    private String codeGcm;
    @Basic(optional = false)
    @NotNull
    @Column(name = "niveau")
    private int niveau;

    public Personne() {
    }

    public Personne(String codeDas) {
        this.codeDas = codeDas;
    }

    public Personne(String codeDas, String nom, String prenom, String codeGcm, int niveau) {
        this.codeDas = codeDas;
        this.nom = nom;
        this.prenom = prenom;
        this.codeGcm = codeGcm;
        this.niveau = niveau;
    }

    public String getCodeDas() {
        return codeDas;
    }

    public void setCodeDas(String codeDas) {
        this.codeDas = codeDas;
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

    public String getCodeGcm() {
        return codeGcm;
    }

    public void setCodeGcm(String codeGcm) {
        this.codeGcm = codeGcm;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeDas != null ? codeDas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personne)) {
            return false;
        }
        Personne other = (Personne) object;
        if ((this.codeDas == null && other.codeDas != null) || (this.codeDas != null && !this.codeDas.equals(other.codeDas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ressources.Personne[ codeDas=" + codeDas + " ]";
    }
    
}
