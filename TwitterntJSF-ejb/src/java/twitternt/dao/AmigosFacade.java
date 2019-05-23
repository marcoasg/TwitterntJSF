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
import twitternt.entity.Amigos;
import twitternt.entity.Usuario;

/**
 *
 * @author Jesús Muley
 */
@Stateless
public class AmigosFacade extends AbstractFacade<Amigos> {

    @PersistenceContext(unitName = "TwitterntJSF-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AmigosFacade() {
        super(Amigos.class);
    }
    
        private List<Usuario> findByUser1(Integer userId){
        return em.createQuery("SELECT a.usuario FROM Amigos a WHERE a.usuario1.id = :userId AND a.solicitudAceptada = TRUE")
                .setParameter("userId", userId)
                .getResultList();
    }
    
    private List<Usuario> findByUser2(Integer userId){
        return em.createQuery("SELECT a.usuario1 FROM Amigos a WHERE a.usuario.id = :userId AND a.solicitudAceptada = TRUE")
                .setParameter("userId", userId)
                .getResultList();
    }
    
    public List<Usuario> findByUser(Integer userId){
        List<Usuario> lista = findByUser1(userId);
        List<Usuario> lista1 = findByUser2(userId);
        if (lista1.size() >= 1)
            lista.addAll(findByUser2(userId));
        return lista;
    }
    
    public List<Usuario> findPetitionsByUser(Integer userId){
        return em.createQuery("SELECT a.usuario FROM Amigos a WHERE a.usuario1.id = :userId AND a.solicitudAceptada = FALSE")
                .setParameter("userId", userId)
                .getResultList();
    }

    public Amigos findFriendByPair(Integer codigoAmigo, Integer userId) {
        try {
            return (Amigos) em.createNamedQuery("Amigos.findFriendByPair")
                    .setParameter("amigo1", codigoAmigo)
                    .setParameter("amigo2", userId)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    public Amigos findPetitionByPair(Integer codigoAmigo, Integer userId) {
        try {
            return (Amigos) em.createNamedQuery("Amigos.findPetitionByPair")
                    .setParameter("amigo1", codigoAmigo)
                    .setParameter("amigo2", userId)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    public boolean sendPetitionAvailable(Integer u1, Integer u2){
        Amigos a1 = findPetitionByPair(u2, u1);
        Amigos a2 = findFriendByPair(u2, u1);
        
        return a1 == null && a2 == null;
    }
}
