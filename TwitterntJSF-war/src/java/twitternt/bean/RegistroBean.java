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
import twitternt.dao.UsuarioFacade;
import twitternt.entity.Usuario;

/**
 *
 * @author Jesús Muley
 */
@Named(value = "registroBean")
@RequestScoped
public class RegistroBean {

    @EJB
    private UsuarioFacade usuarioFacade;

    protected Usuario usuario;
    protected String user;
    protected String pass;
    protected String pass2;
    protected String nombre;
    protected String apellidos;
    protected String email;
    /**
     * Creates a new instance of RegistroBean
     */
    public RegistroBean() {
    }
    
    @PostConstruct
    public void init(){
        this.usuario = this.usuarioFacade.findByUserName(user);
    }

    public UsuarioFacade getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass2() {
        return pass2;
    }

    public void setPass2(String pass2) {
        this.pass2 = pass2;
    }
    
    public String doRegistro(){
        if(this.user.equals("") == false && this.nombre != null && this.pass != null && this.pass2 != null
           && this.apellidos != null && this.email != null){
             this.init();
             if(this.usuario == null && this.pass.equals(this.pass2)){
                this.usuario = new Usuario();
                this.usuario.setNombreUsuario(this.user);
                this.usuario.setPassword(this.pass);
                this.usuario.setNombre(this.nombre);
                this.usuario.setApellidos(this.apellidos);
                this.usuario.setImagen("1_Yn9aAJ8nNju1HZEuzWgA07u94EQWXVh");
                 this.usuario.setEmail(this.email);
                this.usuarioFacade.create(this.usuario);
                 return "login";
            }
        }
        return "registro";
    }
    
    
    public String doCancelar(){
        return "login";
    }
}
