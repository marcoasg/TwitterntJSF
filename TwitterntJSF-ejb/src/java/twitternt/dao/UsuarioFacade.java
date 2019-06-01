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
import twitternt.entity.Usuario;

/**
 *
 * @author adry1
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {
    @PersistenceContext(unitName = "TwitterntJSF-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
        public Usuario findByUserName(String name){
        Usuario res;
        try{
          res = (Usuario)em.createNamedQuery("Usuario.findByNombreUsuario").setParameter("nombreUsuario", name).getSingleResult();
        }catch(Exception e){
            res = null;
            return res;
        }
        return res;
    }
    
    public Usuario findById(Integer id){
        return (Usuario) this.em.createNamedQuery("Usuario.findById").setParameter("id", id).getSingleResult();
    }
    public List<Usuario> findLikeName(String n){
        return (List<Usuario>) this.em.createQuery("SELECT u FROM Usuario u WHERE u.nombreUsuario LIKE '%"+n+"%' OR u.nombre LIKE '%"+n+"%' OR u.apellidos LIKE '%"+n+"%'")
                                .getResultList();
    }
    
    @Override
    public List<Usuario> findAll(){
       return super.findAll();
    }
}
