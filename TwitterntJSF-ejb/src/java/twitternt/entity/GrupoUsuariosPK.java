/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitternt.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Trigi
 */
@Embeddable
public class GrupoUsuariosPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "grupo")
    private int grupo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "usuario")
    private int usuario;

    public GrupoUsuariosPK() {
    }

    public GrupoUsuariosPK(int grupo, int usuario) {
        this.grupo = grupo;
        this.usuario = usuario;
    }

    public int getGrupo() {
        return grupo;
    }

    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) grupo;
        hash += (int) usuario;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GrupoUsuariosPK)) {
            return false;
        }
        GrupoUsuariosPK other = (GrupoUsuariosPK) object;
        if (this.grupo != other.grupo) {
            return false;
        }
        if (this.usuario != other.usuario) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "twitternt.entity.GrupoUsuariosPK[ grupo=" + grupo + ", usuario=" + usuario + " ]";
    }
    
}
