/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitternt.bean;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import twitternt.dao.GrupoFacade;
import twitternt.dao.GrupoUsuariosFacade;
import twitternt.dao.UsuarioFacade;
import twitternt.entity.Grupo;
import twitternt.entity.GrupoUsuarios;
import twitternt.entity.Usuario;

/**
 *
 * @author adry1
 */
@Named(value = "gruposBean")
@RequestScoped
public class GruposBean implements Serializable {
    
    @EJB
    private UsuarioFacade usuarioFacade;
        
    @EJB
    private GrupoFacade  grupoFacade;
    
    @EJB
    private GrupoUsuariosFacade grupoUsuariosFacade;
    
    @Inject
    private LoginBean loginBean;
    
    private List<Grupo> gruposAdmin;
    
    private List<GrupoUsuarios> grupos;
    
    private Grupo grupoAdminSeleccionado;
    
    private GrupoUsuarios grupoSeleccionado;

    public GruposBean() {
    }
    
     @PostConstruct
    public void init(){
            Usuario u = usuarioFacade.findById((Integer) loginBean.getUserId());
            grupos = u.getGrupoUsuariosList();
            gruposAdmin = u.getGrupoList();
    }
    
    public String doEliminar(){
        if(grupoAdminSeleccionado != null)
            this.grupoFacade.remove(grupoAdminSeleccionado);
        else
            this.grupoUsuariosFacade.remove(grupoSeleccionado);
        
        return "grupos";
    }
    
    public List<Grupo> getGruposAdmin() {
        return gruposAdmin;
    }

    public void setGruposAdmin(List<Grupo> gruposAdmin) {
        this.gruposAdmin = gruposAdmin;
    }

    public List<GrupoUsuarios> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<GrupoUsuarios> grupos) {
        this.grupos = grupos;
    }

    public Grupo getGrupoAdminSeleccionado() {
        return grupoAdminSeleccionado;
    }

    public void setGrupoAdminSeleccionado(Grupo grupoAdminSeleccionado) {
        this.grupoAdminSeleccionado = grupoAdminSeleccionado;
    }

    public GrupoUsuarios getGrupoSeleccionado() {
        return grupoSeleccionado;
    }

    public void setGrupoSeleccionado(GrupoUsuarios grupoSeleccionado) {
        this.grupoSeleccionado = grupoSeleccionado;
    }
 
}
