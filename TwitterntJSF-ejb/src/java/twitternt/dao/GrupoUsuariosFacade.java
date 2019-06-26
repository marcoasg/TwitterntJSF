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
import javax.persistence.Query;
import twitternt.entity.Grupo;
import twitternt.entity.GrupoUsuarios;
import twitternt.entity.Usuario;

/**
 *
 * @author adry1
 */
@Stateless
public class GrupoUsuariosFacade extends AbstractFacade<GrupoUsuarios> {
    @PersistenceContext(unitName = "TwitterntJSF-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GrupoUsuariosFacade() {
        super(GrupoUsuarios.class);
    }

    public void removeGroup(Integer id) {
        Query q =  em.createQuery("DELETE FROM GrupoUsuarios g WHERE g.grupo1.id = :id ");
        q.setParameter("id", id).executeUpdate();
    }

    public List<GrupoUsuarios> findGrupoByUser(Usuario usuario) {
       return (List<GrupoUsuarios>) this.em.createQuery("SELECT g FROM GrupoUsuarios g WHERE g.usuario1 = :u and g.solicitudAceptada = 1").setParameter("u", usuario).getResultList();
    }
    
    public List<GrupoUsuarios> findSolicitudesByUser(Usuario usuario){
        return (List<GrupoUsuarios>) this.em.createQuery("SELECT g FROM GrupoUsuarios g WHERE g.usuario1 = :u and g.solicitudAceptada = 0").setParameter("u", usuario).getResultList();
    }
    
    public GrupoUsuarios findGrupo(Usuario usuario, Grupo grupo){
        return (GrupoUsuarios) this.em.createQuery("SELECT  g FROM GrupoUsuarios g WHERE g.usuario1 = :u and g.grupo1 = :g").setParameter("u", usuario).setParameter("g", grupo).getSingleResult();
    }

    public List<Usuario> findUsuarios(Grupo grupo) {
       return (List<Usuario>) this.em.createQuery("SELECT g.usuario1 FROM GrupoUsuarios g WHERE g.grupo1 = :g").setParameter("g", grupo).getResultList();
    }
}
