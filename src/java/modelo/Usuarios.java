package modelo;

import conexion.Conexion;
import java.awt.HeadlessException;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * * @author Luis Fernando Paxel
 */
public class Usuarios {

    private int id_usuario, idRol, estado;
    private String nombres, apellidos, cui, correo, password, imagen, fecha_creacion;

    public Usuarios() {
    }

    public Usuarios(int id_usuario, String nombres, String apellidos, String cui, String correo, String password, int estado, String imagen, String fecha_creacion, int idRol) {
        this.id_usuario = id_usuario;
        this.idRol = idRol;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.cui = cui;
        this.correo = correo;
        this.password = password;
        this.estado = estado;
        this.imagen = imagen;
        this.fecha_creacion = fecha_creacion;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCui() {
        return cui;
    }

    public void setCui(String cui) {
        this.cui = cui;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    Conexion cn;

    public Usuarios validar(String correo, String passwd) {

        Usuarios usuario = new Usuarios();
        String query = "select * from usuarios where correo_electronico = ? and contrasenia = ?;";
        try {
            ResultSet rs;
            cn = new Conexion();
            cn.openConexion();
       
            PreparedStatement parametro;
            parametro = (PreparedStatement) cn.conexiondb.prepareStatement(query);
            parametro.setString(1, correo);
            parametro.setString(2, passwd);
            rs = parametro.executeQuery();
            while (rs.next()) {
                usuario.setId_usuario(rs.getInt("id_usuario"));
                usuario.setNombres(rs.getString("nombres"));
                usuario.setApellidos(rs.getString("apellidos"));
                usuario.setCui(rs.getString("CUI"));
                usuario.setCorreo(rs.getString("correo_electronico"));

                usuario.setFecha_creacion(rs.getString("fecha_creacion"));
                usuario.setIdRol(rs.getInt("id_rol"));
            }

        } catch (SQLException ex) {
            System.out.println("Error func(valida) " + ex);
        }

        return usuario;

    }

    public Usuarios DatosUsuario(int idUser) {
        try {
            cn = new Conexion();
            cn.openConexion();
            String query = "select * from usuarios where id_usuario =?;";
            PreparedStatement parametro;
            parametro = (PreparedStatement) cn.conexiondb.prepareStatement(query);
            parametro.setInt(1, idUser);
            ResultSet consulta = parametro.executeQuery();
            while (consulta.next()) {
                Usuarios usuario = new Usuarios();
                usuario.setId_usuario(consulta.getInt("id_usuario"));
                usuario.setNombres(consulta.getString("nombres"));
                usuario.setApellidos(consulta.getString("apellidos"));
                usuario.setCui(consulta.getString("CUI"));
                usuario.setCorreo(consulta.getString("correo_electronico"));
                usuario.setImagen(consulta.getString("imagen_usuario"));
                usuario.setFecha_creacion(consulta.getString("fecha_creacion"));
                usuario.setIdRol(consulta.getInt("id_rol"));
                return usuario;
            }
            cn.closedConexion();
        } catch (SQLException ex) {
            System.out.println("error func(DatosUsuario) " + ex);
        }
        return null;
    }

    public int CambiarFotoPerfil(String foto, int id) {
        int retorno;
        {
            try {
                cn = new Conexion();
                cn.openConexion();
                PreparedStatement parametro;

                String query = "update usuarios set imagen_usuario=? where id_usuario =?;";
                parametro = (PreparedStatement) cn.conexiondb.prepareStatement(query);
                parametro.setString(1, foto);
                parametro.setInt(2, id);
                retorno = parametro.executeUpdate();
                cn.closedConexion();

            } catch (SQLException ex) {
                System.out.println("error func(CambiarFotoPerfil) " + ex);
                retorno = 0;
            }
            return retorno;
        }

    }

    public int EliminarFotoServidor(String path, String name) {
        int retorno = 0;
        File archivo = new File(path + name);
        if (!"ImgUser.png".equals(name)) {
            archivo.delete();
            retorno = 1;
        }
        return retorno;

    }

    public int ModificarPass(String pass, int idUser) {
        int retorno;
        {
            try {
                cn = new Conexion();
                cn.openConexion();
                PreparedStatement parametro;
                String query = "update usuarios set contrasenia =? where id_usuario =?;";
                parametro = (PreparedStatement) cn.conexiondb.prepareStatement(query);
                parametro.setString(1, pass);
                parametro.setInt(2, idUser);
                retorno = parametro.executeUpdate();
                cn.closedConexion();
            } catch (SQLException ex) {
                System.out.println("error en la func(ModificarPass) " + ex);
                retorno = 0;
            }
            return retorno;
        }
    }

    public String VerificarPass(int id) {
        String retorno = null;
        try {
            cn = new Conexion();
            cn.openConexion();
            PreparedStatement parametro;
            String query = "select contrasenia from usuarios where id_usuario = ?;";
            parametro = (PreparedStatement) cn.conexiondb.prepareStatement(query);
            parametro.setInt(1, id);
            ResultSet rs = parametro.executeQuery();

            while (rs.next()) {
                retorno = rs.getString("contrasenia");
            }

            cn.closedConexion();
        } catch (SQLException ex) {
            System.out.println("Error en la func(VerificarPass)");

        }
        return retorno;
    }

    public DefaultTableModel EntrenadoresTable() {
        DefaultTableModel tabla = new DefaultTableModel();

        try {
            cn = new Conexion();
            cn.openConexion();
            String query = "select nombres, apellidos, imagen_usuario from usuarios where id_rol = 2;";
            ResultSet rs = cn.conexiondb.createStatement().executeQuery(query);
            String encabezado[] = {"nombres", "apellidos", "imagen"};
            tabla.setColumnIdentifiers(encabezado);
            String datos[] = new String[3];
            while (rs.next()) {
                datos[0] = rs.getString("nombres");
                datos[1] = rs.getString("apellidos");
                datos[2] = rs.getString("imagen_usuario");
                tabla.addRow(datos);
            }

            cn.closedConexion();

        } catch (SQLException ex) {
            System.out.println("Error en la func EntrenadoresTable " + ex);
        }
        return tabla;
    }

    public Usuarios Entrenadores() {
        try {
            cn = new Conexion();
            cn.openConexion();
            String query = "select nombres, apellidos, imagen_usuario from usuarios where id_rol = 2;";

            ResultSet consulta = cn.conexiondb.createStatement().executeQuery(query);
            while (consulta.next()) {
                Usuarios usuario = new Usuarios();
                usuario.setNombres(consulta.getString("nombres"));
                usuario.setApellidos(consulta.getString("apellidos"));
                usuario.setImagen(consulta.getString("imagen_usuario"));
                return usuario;
            }
            cn.closedConexion();
        } catch (SQLException ex) {
            System.out.println("Error func(traerRegistro) " + ex);
        }
        return null;
    }

    public int RegistrarUsuario() {
        int retorno;
        {
            try {
                ZonedDateTime tres = ZonedDateTime.now(ZoneId.of("America/New_York"));
                int hora = tres.getHour() - 1;
                String fechaCreacion = tres.getYear() + "-" + tres.getMonthValue() + "-" + tres.getDayOfMonth() + " " + hora + ":" + tres.getMinute() + ":" + "00";

                cn = new Conexion();
                cn.openConexion();
                String query = "insert into usuarios(nombres,apellidos, CUI, correo_electronico, contrasenia, estado, imagen_usuario, fecha_creacion, id_rol)\n"
                        + "values(?,?,?,?,?,?,?,?,?);";
                PreparedStatement parametro;
                parametro = (PreparedStatement) cn.conexiondb.prepareStatement(query);
                parametro.setString(1, this.getNombres());
                parametro.setString(2, this.getApellidos());
                parametro.setString(3, this.getCui());
                parametro.setString(4, this.getCorreo());
                parametro.setString(5, this.getPassword());
                parametro.setInt(6, this.getEstado());
                parametro.setString(7, this.getImagen());
                parametro.setString(8, fechaCreacion);
                parametro.setInt(9, this.getIdRol());
                retorno = parametro.executeUpdate();
                cn.closedConexion();
            } catch (SQLException ex) {
                System.out.println("Error en la func(RegistrarUsuario) " + ex);
                retorno = 0;
            }
            return retorno;
        }
    }

    public String generateRandomPassword(int len) {
        return RandomStringUtils.randomAlphanumeric(len) + RandomStringUtils.randomAscii(len);
    }

    public int AsignarPuesto(int idRol, int idU) {
        int retorno;
        try {
            cn = new Conexion();
            cn.openConexion();
            PreparedStatement parametro;
            String query = "update usuarios set id_rol = ? where id_usuario = ?;";
            parametro = (PreparedStatement) cn.conexiondb.prepareStatement(query);
            parametro.setInt(1, idRol);
            parametro.setInt(2, idU);
            retorno = parametro.executeUpdate();
            cn.closedConexion();

        } catch (SQLException ex) {
            System.out.println("Error en la fun(AsignarPuesto) " + ex);
            retorno = 0;
        }
        return retorno;
    }

    public ArrayList<Integer> getUsuario(String correo, String dpi) {
        ArrayList<Integer> Datos = new ArrayList<>();
        String query = "select  c.id_Cliente, r.idregistros from clientes c inner join registros r on \n"
                + "r.id_cliente = c.id_Cliente and c.Correo_electronico =? and c.NoDPI= ?;";
        int idCliente = 0, idregistro = 0;
        String correoU = "";

        try {
            cn = new Conexion();
            cn.openConexion();
            PreparedStatement parametro;
            parametro = (PreparedStatement) cn.conexiondb.prepareStatement(query);
            parametro.setString(1, correo);
            parametro.setString(2, dpi);

            ResultSet watch = parametro.executeQuery();
            while (watch.next()) {
                idCliente = watch.getInt("c.id_Cliente");

                idregistro = watch.getInt("r.idregistros");
            }
            Datos.add(idCliente);
            Datos.add(idregistro);
            cn.closedConexion();
            return Datos;

        } catch (SQLException ex) {
            System.out.println("Error func(getIdControlCliente) " + ex);
            return null;
        }

    }

    public int eliminarUsuario(int idUser) {
        int retorno;

        {
            try {
                PreparedStatement parametro;
                String query = "delete from usuarios where id_usuario = ?;";
                cn = new Conexion();
                cn.openConexion();
                parametro = (PreparedStatement) cn.conexiondb.prepareStatement(query);
                parametro.setInt(1, idUser);
                retorno = parametro.executeUpdate();
                cn.closedConexion();
            } catch (HeadlessException | SQLException ex) {
                System.out.println("Error func(eliminarUsuario) " + ex.getMessage());
                retorno = 0;
            }
            return retorno;
        }
    }

}
