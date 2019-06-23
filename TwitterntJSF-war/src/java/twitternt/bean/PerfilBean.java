/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitternt.bean;

import javax.annotation.PostConstruct;
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
    private Boolean noEsAmigo;
    private Usuario usuario;
    
    @Inject
    private LoginBean loginBean;
    /**
     * Creates a new instance of PerfilBean
     */
    public PerfilBean() {
    }
    
    @PostConstruct
    public void init(){
        usuario = loginBean.getUsuarioSeleccionado();
/*      try{
 *        noEsAmigo = !((amigosFacade.findFriendByPair(loginBean.getUsuario().getId(), this.usuario.getId()) != null)
 *                   ||(amigosFacade.findFriendByPair( this.usuario.getId(), loginBean.getUsuario().getId()) != null));
 *       } 
 *       catch(Exception e){
 *           noEsAmigo = true;
 *       }
 */   }


    public String doEnviarSolicitud() {
        Amigos solicitud = new Amigos(new AmigosPK(loginBean.getUserId(), usuario.getId()));
        solicitud.setUsuario(loginBean.getUsuario());
        solicitud.setUsuario1(usuario);
        solicitud.setSolicitudAceptada(false);

        amigosFacade.create(solicitud);
        this.init();
        return null;
    }
    
    public boolean isSolicitudDisponible() {
        return amigosFacade.sendPetitionAvailable(loginBean.getUserId(), usuario.getId()) || this.usuario.getId().equals(this.loginBean.getUserId());
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Boolean getNoEsAmigo() {
        return noEsAmigo;
    }

    public void setNoEsAmigo(Boolean noEsAmigo) {
        this.noEsAmigo = noEsAmigo;
    }

    public AmigosFacade getAmigosFacade() {
        return amigosFacade;
    }

    public void setAmigosFacade(AmigosFacade amigosFacade) {
        this.amigosFacade = amigosFacade;
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }
    
}
