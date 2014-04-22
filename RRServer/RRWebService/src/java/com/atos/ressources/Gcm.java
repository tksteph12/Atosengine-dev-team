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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "gcm")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gcm.findAll", query = "SELECT g FROM Gcm g ORDER BY g.code"),
    @NamedQuery(name = "Gcm.findByIdGcm", query = "SELECT g FROM Gcm g WHERE g.idGcm = :idGcm"),
    @NamedQuery(name = "Gcm.findByCode", query = "SELECT g FROM Gcm g WHERE g.code = :code"),
    @NamedQuery(name = "Gcm.findByLibelleGcm", query = "SELECT g FROM Gcm g WHERE g.libelleGcm = :libelleGcm"),
    @NamedQuery(name = "Gcm.findByNiveauMin", query = "SELECT g FROM Gcm g WHERE g.niveauMin = :niveauMin"),
    @NamedQuery(name = "Gcm.findByNiveauMax", query = "SELECT g FROM Gcm g WHERE g.niveauMax = :niveauMax")})
public class Gcm implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_gcm")
    private Integer idGcm;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "code")
    private String code;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "libelle_gcm")
    private String libelleGcm;
    @Basic(optional = false)
    @NotNull
    @Column(name = "niveau_min")
    private int niveauMin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "niveau_max")
    private int niveauMax;

    public Gcm() {
    }

    public Gcm(Integer idGcm) {
        this.idGcm = idGcm;
    }

    public Gcm(Integer idGcm, String code, String libelleGcm, int niveauMin, int niveauMax) {
        this.idGcm = idGcm;
        this.code = code;
        this.libelleGcm = libelleGcm;
        this.niveauMin = niveauMin;
        this.niveauMax = niveauMax;
    }

    public Integer getIdGcm() {
        return idGcm;
    }

    public void setIdGcm(Integer idGcm) {
        this.idGcm = idGcm;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelleGcm() {
        return libelleGcm;
    }

    public void setLibelleGcm(String libelleGcm) {
        this.libelleGcm = libelleGcm;
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
        hash += (idGcm != null ? idGcm.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gcm)) {
            return false;
        }
        Gcm other = (Gcm) object;
        if ((this.idGcm == null && other.idGcm != null) || (this.idGcm != null && !this.idGcm.equals(other.idGcm))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ressources.Gcm[ idGcm=" + idGcm + " ]";
    }
    
    
}
