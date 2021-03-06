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
@SessionScoped
public class GruposBean implements Serializable {
    
    @EJB
    private UsuarioFacade usuarioFacade;
        
    @EJB
    private GrupoFacade  grupoFacade;
    
    @EJB
    private GrupoUsuariosFacade grupoUsuariosFacade;
    
    @Inject
    private LoginBean loginBean;
    
    private Usuario usuario;
    
    private List<GrupoUsuarios> grupos;

    private List<GrupoUsuarios> solicitudes;
    
    private Grupo grupoAdminSeleccionado;
    
    private GrupoUsuarios grupoSeleccionado;
    
    private String nombre;
    
    private String descripcion;
    
    private short solicitudAceptada;
    
    private int grupoId;

    public int getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(int grupoId) {
        this.grupoId = grupoId;
    }
 

    public GruposBean() {
    }
    
     @PostConstruct
    public void init(){
            usuario = usuarioFacade.findById(loginBean.getUserId());
            grupos = grupoUsuariosFacade.findGrupoByUser(usuario);
            solicitudes = grupoUsuariosFacade.findSolicitudesByUser(usuario);
       
    }
    
    public String doSeleccionarGrupo(){
        return "grupo";
    }
    
    public String doEliminar(){
        this.grupoFacade.remove(grupoAdminSeleccionado);
        init();
        return null;
    }
    
    public String doSalir(){
        if(grupoSeleccionado.getGrupo1().getAdmin().equals(usuario)){
            grupoAdminSeleccionado = grupoSeleccionado.getGrupo1();
            return doEliminar();
        }else{
            this.grupoUsuariosFacade.remove(grupoSeleccionado);
            init();
            return null;
        }
    }
    
      public String doCrearGrupo(){
        if(!this.nombre.equals("") && !this.descripcion.equals("")){
            
            Grupo grupoAdmin = new Grupo();
            grupoAdmin.setId(0);
            grupoAdmin.setNombre(nombre);
            grupoAdmin.setDescripcion(descripcion);
            grupoAdmin.setAdmin(usuario);
            grupoFacade.create(grupoAdmin);
            
            GrupoUsuarios grupo = new GrupoUsuarios(grupoAdmin.getId(), usuario.getId());
            grupo.setGrupo1(grupoAdmin);
            grupo.setUsuario1(usuario);
            solicitudAceptada = 1;
            grupo.setSolicitudAceptada(solicitudAceptada);
            grupoUsuariosFacade.create(grupo);
            
            init();
            return "grupos";
        }else{
            return "crearGrupo";
        }
    }
      
    public String doAceptarSolicitud(){
        short aceptada = 1;
        grupoSeleccionado.setSolicitudAceptada(aceptada);
        grupoUsuariosFacade.edit(grupoSeleccionado);
        init();
        return null;
                
    }
   
    
    public String doRechazarSolicitud(){
        return doSalir();
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

    public Short getSolicitudAceptada() {
        return solicitudAceptada;
    }

    public void setSolicitudAceptada(Short solicitudAceptada) {
        this.solicitudAceptada = solicitudAceptada;
    }
    
     public List<GrupoUsuarios> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(List<GrupoUsuarios> solicitudes) {
        this.solicitudes = solicitudes;
    }
    
 
}
