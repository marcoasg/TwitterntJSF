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
    protected List<Usuario> listaUsuarios;
    Integer userId;
    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }

    public Integer getUserId() {
        return 1;
    }
    
    @PostConstruct
    public void init(){
       this.listaUsuarios = this.usuarioFacade.findAll();
    }
    
    public void doComprobacion(String user, String pass){
      
    }
}

