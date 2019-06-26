/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitternt.bean;

import java.io.Serializable;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
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
@SessionScoped
public class recuperarPassBean implements Serializable {

    @EJB
    private UsuarioFacade usuarioFacade;
    private Usuario usuario;
    private String nombreUsuario;
    private String email;
    private int codigoRecuperacion;
    private Integer i;
    private String password;
    private String repassword;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    /**
     * Creates a new instance of recuperarPassBean
     */
    public recuperarPassBean() {
    }

    @PostConstruct
    private void init() {
        this.usuario = this.usuarioFacade.findByUserName(this.nombreUsuario);
        i = 0;
        if (this.usuario != null) {
            this.email = this.usuario.getEmail();
            codigoRecuperacion = (int) (Math.random() * (Math.pow(10, 8))) + 1;
        }
    }

    public String isCodigo() {
        if (i == codigoRecuperacion) {
            return "cambiarContraseña.jsf";
        } else {
            return "login.jsf";
        }
    }

    public String guardarContraseña(){
        if(password.equals(repassword)){
        usuario.setPassword(password);
        usuarioFacade.edit(usuario);
        }
        
        return "login.jsf";
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCodigoRecuperacion() {
        return codigoRecuperacion;
    }

    public void setCodigoRecuperacion(int codigoRecuperacion) {
        this.codigoRecuperacion = codigoRecuperacion;
    }

    public String enviarMail() {

        this.init();
        if (usuario == null) {
            return "recuperarPass";
        }

        final String mail = "olivetwitternt@gmail.com";
        final String pwd = "olivemaster69";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mail, pwd);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("TWITTERNT: Recupera tu contraseña.");
            message.setText("Hola " + nombreUsuario + ", su código de recuperación de contraseña es: " + codigoRecuperacion);
            Transport.send(message);

            return "codigoSeguridad";

        } catch (MessagingException ex) {
            return "recuperarPass";
        }

    }

}
