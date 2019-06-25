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
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import twitternt.dao.PostFacade;
import twitternt.dao.UsuarioFacade;
import twitternt.entity.Post;
import twitternt.entity.Usuario;

/**
 *
 * @author Trigi
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    @EJB
    private PostFacade postFacade;

    @EJB
    private UsuarioFacade usuarioFacade;

    private Usuario usuario;
    private String user;
    private String passIntroducida;
    private String pass;
    private String passRep;
    private Integer userId;
    private String imagen;

    private String nombre;
    private String apellidos;
    private String email;
    private List<Post> listaPostPropios;
    private Usuario usuarioSeleccionado;

    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }

    @PostConstruct
    public void init() {
        this.usuario = this.usuarioFacade.findByUserName(this.user);
        if (this.usuario != null) {
            this.userId = this.usuario.getId();
            this.user = this.usuario.getNombreUsuario();
            this.pass = this.usuario.getPassword();
            this.passRep = this.pass;
            this.nombre = this.usuario.getNombre();
            this.apellidos = this.usuario.getApellidos();
            this.email = this.usuario.getEmail();
            this.imagen = this.usuario.getImagen();
            this.listaPostPropios = this.postFacade.findOwnPost(this.userId);
        }
    }

    public String doLogin() {
        this.init();
        if (this.usuario != null && this.usuario.getPassword().equals(passIntroducida)) {
            return "index.jsf";
        }
        return "login.jsf";
    }

    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        Object session = externalContext.getSession(false);
        HttpSession httpSession = (HttpSession) session;
        httpSession.invalidate();
        return "login.jsf";
    }

    public UsuarioFacade getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getPassRep() {
        return passRep;
    }

    public void setPassRep(String passRep) {
        this.passRep = passRep;
    }

    public Usuario getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }

    public List<Post> getListaPostPropios() {
        //this.init();
        return listaPostPropios;
    }

    public void setListaPostPropios(List<Post> listaPostPropios) {
        this.listaPostPropios = listaPostPropios;
    }

    public PostFacade getPostFacade() {
        return postFacade;
    }

    public void setPostFacade(PostFacade postFacade) {
        this.postFacade = postFacade;
    }

    public String getPassIntroducida() {
        return passIntroducida;
    }

    public void setPassIntroducida(String passIntroducida) {
        this.passIntroducida = passIntroducida;
    }

    public String doEditar() {
        if (this.pass.equals(this.passRep)) {
            this.usuario.setNombre(this.nombre);
            this.usuario.setPassword(this.pass);
            this.usuario.setApellidos(this.apellidos);
            this.usuario.setEmail(this.email);
            this.usuario.setImagen(this.imagen);
            this.usuarioFacade.edit(this.usuario);
            this.init();
        } else {
            this.init();
        }
        return null;
    }

    public String doSeleccionarUsuario(Usuario u) {
        usuarioSeleccionado = u;
        if (usuarioSeleccionado.equals(usuario)) {
            return "perfilPropio.jsf";
        }
        return "perfil.jsf";
    }

    public String getImagenFormateada(){
        return "http://drive.google.com/uc?export=view&id=" + this.imagen;
    }
    
    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
