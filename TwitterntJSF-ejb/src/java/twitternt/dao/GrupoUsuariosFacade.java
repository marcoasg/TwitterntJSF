/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitternt.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import twitternt.entity.GrupoUsuarios;

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
    
}
