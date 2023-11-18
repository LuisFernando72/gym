package conexion;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/*@author Luis Fernando Paxel
 */
public class ConexionCorreo {

    private String correoDeOrigen;
    private String correoDeDestino;
    private String asunto;
    private String mensajeDeTexto;
    private String contraseña16Digitos;

    public ConexionCorreo() {
    }

    public ConexionCorreo(String origen, String destino, String asunto,
            String txt, String contra16Digitos) {
        this.correoDeOrigen = origen;
        this.correoDeDestino = destino;
        this.asunto = asunto;
        this.mensajeDeTexto = txt;
        this.contraseña16Digitos = contra16Digitos;

    }

    public void envioDeCorreos() {
        envioDeMensajes();
    }

    private void envioDeMensajes() {
        try {
            Properties p = new Properties();
            p.put("mail.smtp.host", "smtp.gmail.com");
            p.setProperty("mail.smtp.starttls.enable", "true");
            p.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            p.setProperty("mail.smtp.port", "587");
            p.setProperty("mail.smtp.user", correoDeOrigen);
            p.setProperty("mail.smtp.auth", "true");
            p.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");

            Session s = Session.getDefaultInstance(p);
            MimeMessage mensaje = new MimeMessage(s);
            mensaje.setFrom(new InternetAddress(correoDeOrigen));
            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(correoDeDestino));
            mensaje.setSubject(asunto);
            mensaje.setContent(mensajeDeTexto, "text/plain; charset=UTF-8");

            Transport t = s.getTransport("smtp");
            t.connect(correoDeOrigen, "myxhgdnuzisdzewc");
            t.sendMessage(mensaje, mensaje.getAllRecipients());
            t.close();
            //  JOptionPane.showMessageDialog(null, "Mensaje enviado");
        } catch (MessagingException e) {
            System.out.println("Error " + e);
        }
    }
}
