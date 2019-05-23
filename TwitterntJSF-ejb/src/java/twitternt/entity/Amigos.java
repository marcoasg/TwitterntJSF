/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitternt.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jesús Muley
 */
@Entity
@Table(name = "amigos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Amigos.findAll", query = "SELECT a FROM Amigos a")
    , @NamedQuery(name = "Amigos.findByAmigo1", query = "SELECT a FROM Amigos a WHERE a.amigosPK.amigo1 = :amigo1")
    , @NamedQuery(name = "Amigos.findByAmigo2", query = "SELECT a FROM Amigos a WHERE a.amigosPK.amigo2 = :amigo2")
    , @NamedQuery(name = "Amigos.findBySolicitudAceptada", query = "SELECT a FROM Amigos a WHERE a.solicitudAceptada = :solicitudAceptada")})
public class Amigos implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AmigosPK amigosPK;
    @Column(name = "solicitudAceptada")
    private Boolean solicitudAceptada;
    @JoinColumn(name = "amigo1", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;
    @JoinColumn(name = "amigo2", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario1;

    public Amigos() {
    }

    public Amigos(AmigosPK amigosPK) {
        this.amigosPK = amigosPK;
    }

    public Amigos(int amigo1, int amigo2) {
        this.amigosPK = new AmigosPK(amigo1, amigo2);
    }

    public AmigosPK getAmigosPK() {
        return amigosPK;
    }

    public void setAmigosPK(AmigosPK amigosPK) {
        this.amigosPK = amigosPK;
    }

    public Boolean getSolicitudAceptada() {
        return solicitudAceptada;
    }

    public void setSolicitudAceptada(Boolean solicitudAceptada) {
        this.solicitudAceptada = solicitudAceptada;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario1() {
        return usuario1;
    }

    public void setUsuario1(Usuario usuario1) {
        this.usuario1 = usuario1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (amigosPK != null ? amigosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Amigos)) {
            return false;
        }
        Amigos other = (Amigos) object;
        if ((this.amigosPK == null && other.amigosPK != null) || (this.amigosPK != null && !this.amigosPK.equals(other.amigosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "twitternt.entity.Amigos[ amigosPK=" + amigosPK + " ]";
    }
    
}
