/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.atos.ressources;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author a573405
 */
@Entity
@Table(name = "rr")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rr.findAll", query = "SELECT r FROM Rr r"),
    @NamedQuery(name = "Rr.findByIdRr", query = "SELECT r FROM Rr r WHERE r.idRr = :idRr"),
    @NamedQuery(name = "Rr.findByNomRr", query = "SELECT r FROM Rr r WHERE r.nomRr = :nomRr"),
    @NamedQuery(name = "Rr.findByRole", query = "SELECT r FROM Rr r WHERE r.role = :role"),
    @NamedQuery(name = "Rr.findByDemandeurRr", query = "SELECT r FROM Rr r WHERE r.demandeurRr = :demandeurRr"),
    @NamedQuery(name = "Rr.findByEquipeRm", query = "SELECT r FROM Rr r WHERE r.equipeRm = :equipeRm"),
    @NamedQuery(name = "Rr.findByDateDebut", query = "SELECT r FROM Rr r WHERE r.dateDebut = :dateDebut"),
    @NamedQuery(name = "Rr.findByDateFin", query = "SELECT r FROM Rr r WHERE r.dateFin = :dateFin"),
    @NamedQuery(name = "Rr.findByCompetenceRr", query = "SELECT r FROM Rr r WHERE r.competenceRr = :competenceRr"),
    @NamedQuery(name = "Rr.findByGcmRr", query = "SELECT r FROM Rr r WHERE r.gcmRr = :gcmRr"),
    @NamedQuery(name = "Rr.findByAdresse", query = "SELECT r FROM Rr r WHERE r.adresse = :adresse"),
    @NamedQuery(name = "Rr.findByVille", query = "SELECT r FROM Rr r WHERE r.ville = :ville"),
    @NamedQuery(name = "Rr.findByNiveauMin", query = "SELECT r FROM Rr r WHERE r.niveauMin = :niveauMin"),
    @NamedQuery(name = "Rr.findByNiveauMax", query = "SELECT r FROM Rr r WHERE r.niveauMax = :niveauMax"),
    @NamedQuery(name = "Rr.findAllCities", query = "SELECT DISTINCT r.ville FROM Rr r"),
    @NamedQuery(name="Rr.findwithoutkeywords",query="SELECT r FROM Rr r WHERE r.gcmRr = :gcmRr AND r.ville = :ville AND r.dateDebut >= :from "),
    @NamedQuery(name="Rr.findwithoutkeywords",query="SELECT r FROM Rr r WHERE r.gcmRr = :gcmRr AND r.ville = :ville AND r.dateDebut >= :from "),
    @NamedQuery(name="Rr.findwithfromandgcm",query="SELECT r FROM Rr r WHERE r.gcmRr = :gcmRr AND r.dateDebut >= :from "),
    @NamedQuery(name="Rr.findwithfromandville",query="SELECT r FROM Rr r WHERE r.ville = :ville AND r.dateDebut >= :from ")
                
})

public class Rr implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_rr")
    private Integer idRr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nom_rr")
    private String nomRr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "role")
    private String role;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "demandeur_rr")
    private String demandeurRr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "equipe_rm")
    private String equipeRm;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_debut")
    @Temporal(TemporalType.DATE)
    private Date dateDebut;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_fin")
    @Temporal(TemporalType.DATE)
    private Date dateFin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "competence_rr")
    private String competenceRr;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gcm_rr")
    private String gcmRr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "adresse")
    private String adresse;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ville")
    private String ville;
    @Basic(optional = false)
    @NotNull
    @Column(name = "niveau_min")
    private int niveauMin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "niveau_max")
    private int niveauMax;

    public Rr() {
    }

    public Rr(Integer idRr) {
        this.idRr = idRr;
    }

    public Rr(Integer idRr, String nomRr, String role, String demandeurRr, String equipeRm, Date dateDebut, Date dateFin, String competenceRr, String gcmRr, String adresse, String ville, int niveauMin, int niveauMax) {
        this.idRr = idRr;
        this.nomRr = nomRr;
        this.role = role;
        this.demandeurRr = demandeurRr;
        this.equipeRm = equipeRm;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.competenceRr = competenceRr;
        this.gcmRr = gcmRr;
        this.adresse = adresse;
        this.ville = ville;
        this.niveauMin = niveauMin;
        this.niveauMax = niveauMax;
    }

    public Integer getIdRr() {
        return idRr;
    }

    public void setIdRr(Integer idRr) {
        this.idRr = idRr;
    }

    public String getNomRr() {
        return nomRr;
    }

    public void setNomRr(String nomRr) {
        this.nomRr = nomRr;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDemandeurRr() {
        return demandeurRr;
    }

    public void setDemandeurRr(String demandeurRr) {
        this.demandeurRr = demandeurRr;
    }

    public String getEquipeRm() {
        return equipeRm;
    }

    public void setEquipeRm(String equipeRm) {
        this.equipeRm = equipeRm;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getCompetenceRr() {
        return competenceRr;
    }

    public void setCompetenceRr(String competenceRr) {
        this.competenceRr = competenceRr;
    }

    public String getGcmRr() {
        return gcmRr;
    }

    public void setGcmRr(String gcmRr) {
        this.gcmRr = gcmRr;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public int getNiveauMin() {
        return niveauMin;
    }

    public void setNiveauMin(int niveauMin) {
        this.niveauMin = niveauMin;
    }

    public int getNiveauMax() {
        return niveauMax;
    }

    public void setNiveauMax(int niveauMax) {
        this.niveauMax = niveauMax;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRr != null ? idRr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rr)) {
            return false;
        }
        Rr other = (Rr) object;
        if ((this.idRr == null && other.idRr != null) || (this.idRr != null && !this.idRr.equals(other.idRr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ressources.Rr[ idRr=" + idRr + " ]";
    }
    
}
