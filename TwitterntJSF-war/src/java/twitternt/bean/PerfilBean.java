/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitternt.bean;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import twitternt.entity.Usuario;

/**
 *
 * @author Trigi
 */
@Named(value = "perfilBean")
@RequestScoped
public class PerfilBean {
    
    private Usuario usuario;

    /**
     * Creates a new instance of PerfilBean
     */
    public PerfilBean() {
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public String cargaPerfil(){
        return "perfil";
    }
}
