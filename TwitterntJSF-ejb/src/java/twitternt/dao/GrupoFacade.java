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
import twitternt.entity.Grupo;
import twitternt.entity.Usuario;

/**
 *
 * @author adry1
 */
@Stateless
public class GrupoFacade extends AbstractFacade<Grupo> {
    @PersistenceContext(unitName = "TwitterntJSF-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GrupoFacade() {
        super(Grupo.class);
    }
    
        public Grupo findById(int id){
        return (Grupo) this.em.createNamedQuery("Grupo.findById").setParameter("id", id).getSingleResult();
    }

    public List<Grupo> findLikeName(String n) {
        return (List<Grupo>) this.em.createQuery("SELECT g FROM Grupo g WHERE g.nombre LIKE '%"+n+"%' OR g.nombre LIKE '%"+n+"%'").getResultList();
    }

    public List<Grupo> findByAdmin(Usuario usuario) {
        return (List<Grupo>) this.em.createQuery("SELECT g FROM Grupo g WHERE g.admin = :u").setParameter("u", usuario).getResultList();
    }
    
}
