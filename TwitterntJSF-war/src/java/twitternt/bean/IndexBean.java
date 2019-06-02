/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitternt.bean;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import twitternt.dao.PostFacade;
import twitternt.entity.Post;
import twitternt.entity.Usuario;

/**
 *
 * @author David-PC
 */
@Named(value = "indexBean")
@RequestScoped
public class IndexBean {

    @EJB
    private PostFacade postFacade;

            @Inject
        protected LoginBean loginBean;
            
        protected Usuario usuario;
        private List<Post> listaPost;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Post> getListaPost() {
        return listaPost;
    }

    public void setListaPost(List<Post> listaPost) {
        this.listaPost = listaPost;
    }

        @PostConstruct
        public void init(){
            listaPost = postFacade.findByVisibilidad(0);
        }
        
    public IndexBean() {
    }
    
}
