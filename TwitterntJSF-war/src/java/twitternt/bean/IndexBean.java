/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitternt.bean;

import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import twitternt.dao.PostFacade;
import twitternt.dao.UsuarioFacade;
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
    private UsuarioFacade usuarioFacade;

    @EJB
    private PostFacade postFacade;

            @Inject
        protected LoginBean loginBean;
            
        protected Usuario usuario;
        private List<Post> listaPost;
        private Post post = new Post();
        

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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

        @PostConstruct
        public void init(){
            usuario = loginBean.getUsuario();
            listaPost = postFacade.findByVisibilidad(0);
        }
        
    public IndexBean() {
    }
    public String doPost(){

        post.setFechaPublicacion(new Date());
        post.setUsuario(usuario);
        post.setVisibilidad(0);
        usuario.getPostList().add(post);
        postFacade.create(post);
        usuarioFacade.edit(usuario);
        this.init();
        return "index.jsf";
    }
    public String horaminuto(Integer i){
        
        if (i<10){
            return ("0" + i.toString());
        } else {
            return i.toString();
        }
    }
}
