/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitternt.bean;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import twitternt.dao.AmigosFacade;
import twitternt.entity.Amigos;
import twitternt.entity.AmigosPK;
import twitternt.entity.Usuario;

/**
 *
 * @author Trigi
 */
@Named(value = "perfilBean")
@RequestScoped
public class PerfilBean {

    @EJB
    private AmigosFacade amigosFacade;

    private Usuario usuario;
    
    @Inject
    private LoginBean loginBean;
    /**
     * Creates a new instance of PerfilBean
     */
    public PerfilBean() {
    }

    public String cargaPerfil() {
        return "perfil.jsf";
    }

    public String doEnviarSolicitud() {
        Amigos solicitud = new Amigos();
        solicitud.setUsuario(loginBean.getUsuario());
        solicitud.setUsuario1(usuario);
        solicitud.setSolicitudAceptada(false);

        AmigosPK pk = new AmigosPK(loginBean.getUserId(), usuario.getId());
        solicitud.setAmigosPK(pk);

        amigosFacade.create(solicitud);
        return null;
    }
    
    public boolean isSolicitudDisponible() {
        return amigosFacade.sendPetitionAvailable(loginBean.getUserId(), usuario.getId());
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
