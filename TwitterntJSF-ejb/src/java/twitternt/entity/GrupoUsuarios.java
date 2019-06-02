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
 * @author David-PC
 */
@Entity
@Table(name = "grupo_usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GrupoUsuarios.findAll", query = "SELECT g FROM GrupoUsuarios g")
    , @NamedQuery(name = "GrupoUsuarios.findByGrupo", query = "SELECT g FROM GrupoUsuarios g WHERE g.grupoUsuariosPK.grupo = :grupo")
    , @NamedQuery(name = "GrupoUsuarios.findByUsuario", query = "SELECT g FROM GrupoUsuarios g WHERE g.grupoUsuariosPK.usuario = :usuario")
    , @NamedQuery(name = "GrupoUsuarios.findBySolicitudAceptada", query = "SELECT g FROM GrupoUsuarios g WHERE g.solicitudAceptada = :solicitudAceptada")})
public class GrupoUsuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GrupoUsuariosPK grupoUsuariosPK;
    @Column(name = "solicitudAceptada")
    private Short solicitudAceptada;
    @JoinColumn(name = "grupo", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Grupo grupo1;
    @JoinColumn(name = "usuario", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario1;

    public GrupoUsuarios() {
    }

    public GrupoUsuarios(GrupoUsuariosPK grupoUsuariosPK) {
        this.grupoUsuariosPK = grupoUsuariosPK;
    }

    public GrupoUsuarios(int grupo, int usuario) {
        this.grupoUsuariosPK = new GrupoUsuariosPK(grupo, usuario);
    }

    public GrupoUsuariosPK getGrupoUsuariosPK() {
        return grupoUsuariosPK;
    }

    public void setGrupoUsuariosPK(GrupoUsuariosPK grupoUsuariosPK) {
        this.grupoUsuariosPK = grupoUsuariosPK;
    }

    public Short getSolicitudAceptada() {
        return solicitudAceptada;
    }

    public void setSolicitudAceptada(Short solicitudAceptada) {
        this.solicitudAceptada = solicitudAceptada;
    }

    public Grupo getGrupo1() {
        return grupo1;
    }

    public void setGrupo1(Grupo grupo1) {
        this.grupo1 = grupo1;
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
        hash += (grupoUsuariosPK != null ? grupoUsuariosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GrupoUsuarios)) {
            return false;
        }
        GrupoUsuarios other = (GrupoUsuarios) object;
        if ((this.grupoUsuariosPK == null && other.grupoUsuariosPK != null) || (this.grupoUsuariosPK != null && !this.grupoUsuariosPK.equals(other.grupoUsuariosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "twitternt.entity.GrupoUsuarios[ grupoUsuariosPK=" + grupoUsuariosPK + " ]";
    }
    
}
