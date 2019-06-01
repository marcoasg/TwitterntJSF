/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitternt.bean;

import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import twitternt.dao.AmigosFacade;
import twitternt.entity.Amigos;
import twitternt.entity.Usuario;

/**
 *
 * @author Trigi
 */
@Named(value = "amigosBean")
@RequestScoped
public class AmigosBean implements Serializable {

    @EJB
    private AmigosFacade amigosFacade;

    @Inject
    protected LoginBean loginBean;
    
    private List<Usuario> listaAmigos;
    private List<Usuario> listaSolicitudes;
    
    private Usuario seleccionado;

    /**
     * Creates a new instance of AmigosBean
     */
    public AmigosBean() {
    }
    
    @PostConstruct
    public void init(){
        listaAmigos = amigosFacade.findByUser(loginBean.getUserId());
        listaSolicitudes = amigosFacade.findPetitionsByUser(loginBean.getUserId());
    }
    
    public String doEliminar(){
        amigosFacade.remove(amigosFacade.findFriendByPair(seleccionado.getId(), loginBean.getUserId()));
        init();
        return null;
    }
    
    public String doAceptarSolicitud() {
        Amigos solicitud = amigosFacade.findPetitionByPair(seleccionado.getId(), loginBean.getUserId());
        solicitud.setSolicitudAceptada(true);
        amigosFacade.edit(solicitud);
        init();
        return null;
    }
    
    public String doRechazarSolicitud() {
        Amigos solicitud = amigosFacade.findPetitionByPair(seleccionado.getId(), loginBean.getUserId());
        amigosFacade.remove(solicitud);
        init();
        return null;
    }
    
    public List<Usuario> getListaAmigos(){
        return listaAmigos;
    }
    
    public void setListaAmigos(List<Usuario> listaAmigos){
        this.listaAmigos = listaAmigos;
    }

    public Usuario getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Usuario seleccionado) {
        this.seleccionado = seleccionado;
    }

    public List<Usuario> getListaSolicitudes() {
        return listaSolicitudes;
    }

    public void setListaSolicitudes(List<Usuario> listaSolicitudes) {
        this.listaSolicitudes = listaSolicitudes;
    }
}
