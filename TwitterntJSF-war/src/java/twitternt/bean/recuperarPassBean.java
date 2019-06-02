/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitternt.bean;

import java.util.Properties;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;

import javax.mail.internet.MimeMessage;
import twitternt.dao.UsuarioFacade;
import twitternt.entity.Usuario;

/**
 *
 * @author JOSE
 */
@Named(value = "recuperarPassBean")
@RequestScoped
public class recuperarPassBean {

    @EJB
    private UsuarioFacade usuarioFacade;
    private Usuario usuario; 
    private String nombreUsuario;
    private String email;
    private Random random;
    private int codigoRecuperacion;

    /**
     * Creates a new instance of recuperarPassBean
     */
    public recuperarPassBean() {
    }
    
     @PostConstruct
    private void init(){
      this.usuario = this.usuarioFacade.findByUserName(this.nombreUsuario);
       if(this.usuario != null){
        this.email = this.usuario.getEmail();
        this.random = new Random();
        this.codigoRecuperacion=random.nextInt(8);
    }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String enviarMail(){
        
        this.init();
        if(usuario == null){
            return "recuperarPass";
        }
        
        final String mail="oliveTwitternt@gmail.com";
        final String pwd="olivemaster69";
        
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gamil.com");
        props.put("mail.smtp.port", "587");
        
   
        Session session = Session.getInstance(props, 
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication(){
                    return new PasswordAuthentication(mail, pwd);
                }
                });
        
        try{
        
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("aa@aa.com"));
            message.setRecipient(Message.RecipientType.TO, InternetAddress.parse(email)[0]);
            message.setSubject("Recupera tu contraseña!!!");
            message.setText(usuario + "el numero de recuperacion de contraseña" + codigoRecuperacion);
            Transport.send(message);
            
            return "nuevaPass";
            
        } catch (MessagingException ex){
            throw new RuntimeException(ex);
        }
      /*  finally {return "recuperarPass";} */

        
    }
    
    
}
    
    
    

