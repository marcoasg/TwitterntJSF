/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitternt.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import twitternt.entity.Post;
import twitternt.entity.Usuario;

/**
 *
 * @author adry1
 */
@Stateless
public class PostFacade extends AbstractFacade<Post> {
    @PersistenceContext(unitName = "TwitterntJSF-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PostFacade() {
        super(Post.class);
    }
    
    public List<Post> findByVisibilidad(Integer visibilidad){
        return em.createQuery("SELECT p FROM Post p WHERE p.visibilidad = :visibilidad ORDER BY p.id DESC")
                .setParameter("visibilidad", visibilidad)
                .getResultList();
    }
       // Convenio Visibilidad: 0 Publico 1 Amigos 2 Privado

    public Post findById(int id) {
        return (Post) this.em.createNamedQuery("Post.findById").setParameter("id", id).getSingleResult();
    }
    
    
    public List<Post> findPublicAndYourPrivate(List<Usuario> listaAmigos){
        return em.createQuery("SELECT p FROM Post p WHERE p.visibilidad = 0 OR p.usuario IN :listaAmigos").setParameter("listaAmigos", listaAmigos).getResultList();
        
    }
    
    
    public List<Post> findOwnPost(Integer user){
       return em.createQuery("SELECT p FROM Post p WHERE p.usuario.id = :usuario").setParameter("usuario", user).getResultList();
    }
    
}
