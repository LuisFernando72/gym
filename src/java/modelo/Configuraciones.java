package modelo;

import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/* @author Luis Fernando Paxel
 */
public class Configuraciones {

    private int idConf;
    private String facebook, tiktok, instagram, frase, autor;

    public Configuraciones() {
    }

    public Configuraciones(int idConf, String facebook, String tiktok, String instagram, String frase, String autor) {
        this.idConf = idConf;
        this.facebook = facebook;
        this.tiktok = tiktok;
        this.instagram = instagram;
        this.frase = frase;
        this.autor = autor;
    }

    public int getIdConf() {
        return idConf;
    }

    public void setIdConf(int idConf) {
        this.idConf = idConf;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

      public String getTiktok() {
        return tiktok;
    }

    public void setTiktok(String tiktok) {
        this.tiktok = tiktok;
    }
    

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getFrase() {
        return frase;
    }

    public void setFrase(String frase) {
        this.frase = frase;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    Conexion cn;

    public Configuraciones traerConf() {
        try {
            cn = new Conexion();
            cn.openConexion();
            String query = "select * from configuraciones;";

            ResultSet consulta = cn.conexiondb.createStatement().executeQuery(query);
            while (consulta.next()) {
                Configuraciones cf = new Configuraciones();

                cf.setIdConf(consulta.getInt("idConfiguracion"));
                cf.setFacebook(consulta.getString("facebook"));
                cf.setTiktok(consulta.getString("twitter"));
                cf.setInstagram(consulta.getString("Instagram"));
                cf.setFrase(consulta.getString("Frase"));
                cf.setAutor(consulta.getString("autor"));

                return cf;
            }
            cn.closedConexion();
        } catch (SQLException ex) {
            System.out.println("Error func(traerConf) " + ex);
        }
        return null;
    }

    public int ActualizarRedes(String f, String t, String i) {
        int retorno;
        try {
            cn = new Conexion();
            cn.openConexion();
            String query = "update configuraciones set facebook =?, twitter = ?, Instagram =? where idConfiguracion = 1;";
            PreparedStatement parametro;
            parametro = (PreparedStatement) cn.conexiondb.prepareStatement(query);
            parametro.setString(1, f);
            parametro.setString(2, t);
            parametro.setString(3, i);
            retorno = parametro.executeUpdate();
            cn.closedConexion();

        } catch (SQLException ex) {
            System.out.println("Error en la func (ActualizarRedes) " + ex);
            retorno = 0;
        }
        return retorno;
    }

    public int ActualizarFrase(String frase, String autor) {
        int retorno;
        try {
            cn = new Conexion();
            cn.openConexion();
            String query = "update configuraciones set Frase = ?, autor = ? where idConfiguracion = 1;";
            PreparedStatement parametro;
            parametro = (PreparedStatement) cn.conexiondb.prepareStatement(query);
            parametro.setString(1, frase);
            parametro.setString(2, autor);
            retorno = parametro.executeUpdate();
            cn.closedConexion();

        } catch (SQLException ex) {
            System.out.println("Error en la func (ActualizarRedes) " + ex);
            retorno = 0;
        }
        return retorno;
    }

    public int Modulo(int id) {
        int retorno = 0;
        try {
            cn = new Conexion();
            cn.openConexion();
            String query = "select * from modulos where idRol = ?;";
            PreparedStatement parametro;
            parametro = (PreparedStatement) cn.conexiondb.prepareStatement(query);
            parametro.setInt(1, id);
            ResultSet rs = parametro.executeQuery();
            while (rs.next()) {
                rs.getString("nombre");
                rs.getString("link");
                rs.getString("classDiv");
                rs.getString("Icono");
                rs.getInt("idRol");
                retorno = 1;
            }
            cn.closedConexion();
        } catch (SQLException ex) {
            System.out.println("error func(Modulo) " + ex);
            return 0;
        }
        return retorno;
    }

  
    
}
