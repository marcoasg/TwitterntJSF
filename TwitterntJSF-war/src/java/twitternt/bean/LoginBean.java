/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitternt.bean;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author Trigi
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    Integer userId;
    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }

    public Integer getUserId() {
        return 1;
    }
    
}
