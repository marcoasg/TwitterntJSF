/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitternt.bean;

import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import twitternt.entity.Usuario;

/**
 *
 * @author David-PC
 */
@Named(value = "indexBean")
@Dependent
public class IndexBean {

            @Inject
        protected LoginBean loginBean;
            
        protected Usuario usuario;

        
        
    public IndexBean() {

    }
    
}
