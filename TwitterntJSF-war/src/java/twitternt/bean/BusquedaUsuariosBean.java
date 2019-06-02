/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitternt.bean;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import twitternt.dao.UsuarioFacade;
import twitternt.entity.Usuario;

/**
 *
 * @author Trigi
 */
@Named(value = "busquedaUsuariosBean")
@SessionScoped
public class BusquedaUsuariosBean implements Serializable {

    @EJB
    private UsuarioFacade usuarioFacade;
    
    private List<Usuario> resultadoBusqueda;
    String pattern;

    
    /**
     * Creates a new instance of BusquedaUsuariosBean
     */
    public BusquedaUsuariosBean() {
    }
    
    public String doBusqueda() {
        if (pattern.length() > 0) {
            resultadoBusqueda = usuarioFacade.findLikeName(pattern);
        } else{
            resultadoBusqueda = null;
        }
        return null;
    }

    public List<Usuario> getResultadoBusqueda() {
        return resultadoBusqueda;
    }

    public void setResultadoBusqueda(List<Usuario> resultadoBusqueda) {
        this.resultadoBusqueda = resultadoBusqueda;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
    
    
}
