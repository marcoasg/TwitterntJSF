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
@Named(value = "grupoBean")
@RequestScoped
public class GrupoBean implements Serializable {
    
    @EJB
    private UsuarioFacade usuarioFacade;
        
    @EJB
    private GrupoFacade  grupoFacade;
    
    @EJB
    private GrupoUsuariosFacade grupoUsuariosFacade;
    
    @Inject
    private LoginBean loginBean;
    
    @Inject
    private GruposBean gruposBean;
    
    private Usuario usuario;
    
    private Grupo grupoAdmin;
    
    private GrupoUsuarios grupo;
    
    private String nombre;
    
    private String descripcion;
    
    private boolean solicitudAceptada;

    
        public GrupoBean() {
    }
    
     @PostConstruct
    public void init(){
            usuario = usuarioFacade.findById((Integer) loginBean.getUserId());
            grupoAdmin = gruposBean.getGrupoAdminSeleccionado();
            grupo = gruposBean.getGrupoSeleccionado();
    }
    
    public String doCrearGrupo(){
        init();
        if(!this.nombre.equals("") && !this.descripcion.equals("")){
            
            grupoAdmin = new Grupo();
            grupoAdmin.setId(0);
            grupoAdmin.setNombre(nombre);
            grupoAdmin.setDescripcion(descripcion);
            grupoAdmin.setAdmin(usuario);
            grupoFacade.create(grupoAdmin);
            
            grupo = new GrupoUsuarios();
            grupo.setGrupo1(grupoAdmin);
            grupo.setUsuario1(usuario);
            short aceptada = 1;
            grupo.setSolicitudAceptada(aceptada);
            grupoUsuariosFacade.create(grupo);
            
            return "grupos";
        }else{
            return "crearGrupo";
        }
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Grupo getGrupoAdmin() {
        return grupoAdmin;
    }

    public void setGrupoAdmin(Grupo grupoAdmin) {
        this.grupoAdmin = grupoAdmin;
    }

    public GrupoUsuarios getGrupo() {
        return grupo;
    }

    public void setGrupo(GrupoUsuarios grupo) {
        this.grupo = grupo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isSolicitudAceptada() {
        return solicitudAceptada;
    }

    public void setSolicitudAceptada(boolean solicitudAceptada) {
        this.solicitudAceptada = solicitudAceptada;
    }
   
}