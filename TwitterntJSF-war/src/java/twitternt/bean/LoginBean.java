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
import javax.inject.Inject;
import twitternt.dao.UsuarioFacade;
import twitternt.entity.Usuario;

/**
 *
 * @author Trigi
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    @EJB
    private UsuarioFacade usuarioFacade;
    
    @Inject
    protected UsuarioBean usuarioBean;
    
    protected Usuario usuario;
    protected String user;
    protected String pass;
    protected Integer userId;
    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }
    
    @PostConstruct
    public void init(){
       this.usuario = this.usuarioFacade.findByUserName(this.user);
       if(this.usuario != null){
        this.userId = this.usuario.getId();
       }
    }
    
    public String doLogin(){
        System.out.println(user + "," + pass);
      this.init();
      if(this.usuario != null && this.usuario.getPassword().equals(pass)){
        return "index";
      }
      return "login";
    }

    
    public String logout() {
        this.usuario = null;
        this.user = null;
        this.pass = null;
        this.userId = null;
        return "login";
    }
    
    public UsuarioFacade getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }

    public UsuarioBean getUsuarioBean() {
        return usuarioBean;
    }

    public void setUsuarioBean(UsuarioBean usuarioBean) {
        this.usuarioBean = usuarioBean;
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
    
    
}

