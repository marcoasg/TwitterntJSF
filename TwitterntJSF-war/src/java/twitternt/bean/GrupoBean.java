/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitternt.bean;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import twitternt.dao.AmigosFacade;
import twitternt.dao.GrupoFacade;
import twitternt.dao.GrupoUsuariosFacade;
import twitternt.dao.PostFacade;
import twitternt.dao.UsuarioFacade;
import twitternt.entity.Grupo;
import twitternt.entity.GrupoUsuarios;
import twitternt.entity.Post;
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
    private PostFacade postFacade;
    
    @EJB 
    private GrupoFacade grupoFacade;
    
    @EJB
    private GrupoUsuariosFacade grupoUsuariosFacade;
    
    @EJB
    private AmigosFacade amigosFacade;
    
    @Inject
    private LoginBean loginBean;
    
    @Inject 
    private GruposBean gruposBean;
    
    private Usuario usuario;
    
    private Usuario usuarioSeleccionado;
    
    private List<Usuario> usuarios;
    
    private List<Usuario> listaAmigos;
    
    private Integer grupoId;
    
    private Grupo grupo;
    
    private Post post;

    private boolean admin;
    
        public GrupoBean() {
    }
    
     @PostConstruct
    public void init(){
        usuario = usuarioFacade.findById(loginBean.getUserId());
        grupo = grupoFacade.findById(gruposBean.getGrupoId());
        usuarios = grupoUsuariosFacade.findUsuarios(grupo);
        usuarios.remove(usuario);
        listaAmigos = amigosFacade.findByUser(loginBean.getUserId());
        listaAmigos.removeAll(usuarios);
        
        if(grupo.getAdmin().equals(usuario)){
                admin = true;
        }else{
                admin = false;
        }
        post = new Post();
    }
    
    public String doPost(){

        post.setFechaPublicacion(new Date());
        post.setUsuario(usuario);
        post.setVisibilidad(1);
        post.setGrupo(grupo);
        usuario.getPostList().add(post);
        grupo.getPostList().add(post);
        loginBean.getListaPostPropios().add(post);
        postFacade.create(post);
        usuarioFacade.edit(usuario);
        grupoFacade.edit(grupo);
        this.init();
        return null;
    }
    
    public String doSalir(){
        init();
        if(admin){
            this.grupoFacade.remove(grupo);
        }else{
            GrupoUsuarios gu = this.grupoUsuariosFacade.findGrupo(usuario, grupo);
            grupoUsuariosFacade.remove(gu);
        }
        gruposBean.init();
        return "grupos";
    }
    
    public String doEliminar(){
        if(usuarioSeleccionado.equals(usuario)){
            return doSalir();
        }else{
            GrupoUsuarios gu = this.grupoUsuariosFacade.findGrupo(usuarioSeleccionado, grupo);
            grupoUsuariosFacade.remove(gu);
            init();
            return null;
        }
    }
    
    public String doInvitar(){
        GrupoUsuarios g = new GrupoUsuarios(grupo.getId(), usuarioSeleccionado.getId());
        g.setGrupo1(grupo);
        g.setUsuario1(usuarioSeleccionado);
        short solicitudAceptada = 0;
        g.setSolicitudAceptada(solicitudAceptada);
        grupoUsuariosFacade.create(g);
        
        init();
        return null;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

     public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
     public int getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(int grupoId) {
        this.grupoId = grupoId;
    }
    
    
    public Usuario getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    
    public List<Usuario> getListaAmigos() {
        return listaAmigos;
    }

    public void setListaAmigos(List<Usuario> listaAmigos) {
        this.listaAmigos = listaAmigos;
    }
    
      public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}