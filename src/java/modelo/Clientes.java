package modelo;

import conexion.Conexion;
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

/* * @author Luis Fernando Paxel
 */
public class Clientes {

    private int genero, edad, idRegistro, idreg2;

    private String nombres, apellidos, dpi, correo, telefono, estatura, NombreE, apellidoE, TelefonoE, fecha_actualizacion, fecha_nacimiento;
    private String SelectGeneral = "select idPreguntaCliente,DescripcionPregunta from preguntascliente where id_tipo_pregunta = 1 and idPreguntaCliente";
    private String SelectEspecifica = "select idPreguntaCliente,DescripcionPregunta from preguntascliente where id_tipo_pregunta = 2";
    private String fechaR, observaciones;

    public Clientes() {

    }
    Conexion cn;

    public Clientes(String nombres, String apellidos, String dpi, String correo, String telefono, int genero, String estatura, String fecha_nacimiento, int edad, String NombreE, String apellidoE, String TelefonoE, int idRegistro) {
        this.genero = genero;
        this.edad = edad;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.dpi = dpi;
        this.correo = correo;
        this.telefono = telefono;
        this.estatura = estatura;
        this.fecha_nacimiento = fecha_nacimiento;
        this.NombreE = NombreE;
        this.apellidoE = apellidoE;
        this.TelefonoE = TelefonoE;
        this.idRegistro = idRegistro;

    }

    public int getGenero() {
        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
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

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEstatura() {
        return estatura;
    }

    public void setEstatura(String estatura) {
        this.estatura = estatura;
    }

    public String getNombreE() {
        return NombreE;
    }

    public void setNombreE(String NombreE) {
        this.NombreE = NombreE;
    }

    public String getApellidoE() {
        return apellidoE;
    }

    public void setApellidoE(String apellidoE) {
        this.apellidoE = apellidoE;
    }

    public String getTelefonoE() {
        return TelefonoE;
    }

    public void setTelefonoE(String TelefonoE) {
        this.TelefonoE = TelefonoE;
    }

    public String getFecha_actualizacion() {
        return fecha_actualizacion;
    }

    public void setFecha_actualizacion(String fecha_actualizacion) {
        this.fecha_actualizacion = fecha_actualizacion;
    }

    public String getSelectGeneral() {
        return SelectGeneral;
    }

    public void setSelectGeneral(String SelectGeneral) {
        this.SelectGeneral = SelectGeneral;
    }

    public String getSelectEspecifica() {
        return SelectEspecifica;
    }

    public void setSelectEspecifica(String SelectEspecifica) {
        this.SelectEspecifica = SelectEspecifica;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public int getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(int idRegistro) {
        this.idRegistro = idRegistro;
    }

    public String getFechaR() {
        return fechaR;
    }

    public void setFechaR(String fechaR) {
        this.fechaR = fechaR;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getIdreg2() {
        return idreg2;
    }

    public void setIdreg2(int idreg2) {
        this.idreg2 = idreg2;
    }

    public int insertarCliente() {
        int retorno;
        {
            try {
                cn = new Conexion();
                cn.openConexion();
                PreparedStatement parametro;
                String query = "insert into clientes(Nombres, Apellidos, NoDPI, Correo_electronico, No_telefono, Genero, Estatura,Fecha_nacimiento, edad, Nombres_emergencia,\n"
                        + " Apellidos_emergencia,Telefono_emergencia)\n"
                        + " values(?,?,?,?, ?, ?, ?, ?, ?, ?, ?,?);";

                parametro = (PreparedStatement) cn.conexiondb.prepareStatement(query);
                parametro.setString(1, this.getNombres());
                parametro.setString(2, this.getApellidos());
                parametro.setString(3, this.getDpi());
                parametro.setString(4, this.getCorreo());
                parametro.setString(5, this.getTelefono());
                parametro.setInt(6, this.getGenero());
                parametro.setString(7, this.getEstatura());
                parametro.setString(8, this.getFecha_nacimiento());
                parametro.setInt(9, this.getEdad());
                parametro.setString(10, this.getNombreE());
                parametro.setString(11, this.getApellidoE());
                parametro.setString(12, this.getTelefonoE());
                retorno = parametro.executeUpdate();
                cn.closedConexion();
            } catch (SQLException ex) {
                System.out.println("Error insertarCliente " + ex);
                retorno = 0;
            }

            return retorno;
        }

    }

    public int buscar_idCliente(String dpi, String email) {
        int retorno = 0;
        Control_cliente control = new Control_cliente();
        String query = "select * from clientes where NoDPI= ? and Correo_electronico = ?;";
        int tr = 0;

        try {
            cn = new Conexion();
            cn.openConexion();
            PreparedStatement parametro;
            parametro = (PreparedStatement) cn.conexiondb.prepareStatement(query);
            parametro.setString(1, dpi);
            parametro.setString(2, email);
            ResultSet watch = parametro.executeQuery();
            while (watch.next()) {
                control.setDpi(watch.getString("NoDPI"));
                control.setEmail(watch.getString("Correo_electronico"));
                tr = watch.getInt("id_Cliente");
                retorno = 1;
            }
            //  this.setIdtraer(tr);
            cn.closedConexion();
        } catch (SQLException ex) {
            System.out.println("Error val control cliente " + ex);
            retorno = 0;
            tr = 0;
        }

        return tr;
    }

    // 
//20/09/2022
    public int buscar_duplicadoC(String dpi, String email) {
        int retorno = 0;
        String query = "select  NoDPI, Correo_electronico from clientes where NoDPI = ? and Correo_electronico = ?;";
        try {
            cn = new Conexion();
            cn.openConexion();
            PreparedStatement parametro;
            parametro = (PreparedStatement) cn.conexiondb.prepareStatement(query);
            parametro.setString(1, dpi);
            parametro.setString(2, email);
            ResultSet watch = parametro.executeQuery();
            while (watch.next()) {
                retorno = 1;
            }
            cn.closedConexion();
        } catch (SQLException ex) {
            System.out.println("Error cliente duplicado " + ex);
            retorno = 0;

        }

        return retorno;
    }

    public Clientes traerRegistro(int idCliente) {
        try {
            cn = new Conexion();
            cn.openConexion();
            String query = "select r.idregistros, c.Nombres, c.Apellidos,  c.Correo_electronico, TIMESTAMPDIFF(YEAR,c.Fecha_nacimiento,CURDATE()) AS edad, c.Estatura\n"
                    + "from registros as r inner join clientes as c on r.id_cliente = c.id_Cliente where c.id_Cliente=?;";

            PreparedStatement parametro;
            parametro = (PreparedStatement) cn.conexiondb.prepareStatement(query);
            parametro.setInt(1, idCliente);
            ResultSet consulta = parametro.executeQuery();
            while (consulta.next()) {
                Clientes clientes = new Clientes();
                clientes.setIdRegistro(consulta.getInt("r.idregistros"));
                clientes.setNombres(consulta.getString("c.Nombres"));
                clientes.setApellidos(consulta.getString("c.Apellidos"));
                clientes.setCorreo(consulta.getString("c.Correo_electronico"));
                clientes.setEdad(consulta.getInt("edad"));
                clientes.setEstatura(consulta.getString("c.Estatura"));
                return clientes;
            }
            cn.closedConexion();
        } catch (SQLException ex) {
            System.out.println("Error func(traerRegistro) " + ex);
        }
        return null;
    }

    public int ModificarCliente() {
        int retorno;
        try {
            cn = new Conexion();
            cn.openConexion();
            PreparedStatement parametro;
            ZonedDateTime tres = ZonedDateTime.now(ZoneId.of("America/New_York"));
            int hora = tres.getHour() - 1;
            String fechaActualizacion = tres.getYear() + "-" + tres.getMonthValue() + "-" + tres.getDayOfMonth() + " " + hora + ":" + tres.getMinute() + ":" + "00";

            String query = " update clientes set Nombres = ?, Apellidos = ?, NoDPI = ?, Correo_electronico =?, No_telefono = ?,\n"
                    + "Genero = ?, Estatura = ?, Fecha_nacimiento =  ?, edad =?, Nombres_emergencia = ?, Apellidos_emergencia = ?, Telefono_emergencia = ?,\n"
                    + " Fecha_actualizacion = ? where id_Cliente = ?;";
            parametro = (PreparedStatement) cn.conexiondb.prepareStatement(query);

            parametro.setString(1, this.getNombres());
            parametro.setString(2, this.getApellidos());
            parametro.setString(3, this.getDpi());
            parametro.setString(4, this.getCorreo());
            parametro.setString(5, this.getTelefono());
            parametro.setInt(6, this.getGenero());
            parametro.setString(7, this.getEstatura());
            parametro.setString(8, this.getFecha_nacimiento());
            parametro.setInt(9, this.getEdad());
            parametro.setString(10, this.getNombreE());
            parametro.setString(11, this.getApellidoE());
            parametro.setString(12, this.getTelefonoE());
            parametro.setString(13, fechaActualizacion);
            parametro.setInt(14, this.getIdRegistro());
            retorno = parametro.executeUpdate();
            cn.closedConexion();
        } catch (SQLException ex) {
            System.out.println("Error en la func(ModificarCliente) " + ex);
            retorno = 0;
        }
        return retorno;
    }

    public Clientes DatosCliente(int idCliente) {
        try {
            cn = new Conexion();
            cn.openConexion();
            String query = "select c.id_Cliente, c.Nombres, c.Apellidos, c.NoDPI, c.Correo_electronico, c.No_telefono, c.Genero,c.Estatura, c.Fecha_nacimiento, \n"
                    + "TIMESTAMPDIFF(YEAR,c.Fecha_nacimiento,CURDATE()) AS edad, c.Nombres_emergencia, c.Apellidos_emergencia, c.Telefono_emergencia, r.fecha_inicio, r.observacion_clinica, r.idregistros\n"
                    + "from clientes c  inner join registros r on  r.id_cliente= c.id_Cliente where c.id_Cliente =?;";
            PreparedStatement parametro;
            parametro = (PreparedStatement) cn.conexiondb.prepareStatement(query);
            parametro.setInt(1, idCliente);
            ResultSet consulta = parametro.executeQuery();
            while (consulta.next()) {
                Clientes clientes = new Clientes();
                clientes.setIdRegistro(consulta.getInt("c.id_Cliente"));
                clientes.setNombres(consulta.getString("c.Nombres"));
                clientes.setApellidos(consulta.getString("c.Apellidos"));
                clientes.setDpi(consulta.getString("c.NoDPI"));
                clientes.setCorreo(consulta.getString("c.Correo_electronico"));
                clientes.setTelefono(consulta.getString("c.No_telefono"));
                clientes.setGenero(consulta.getInt("c.Genero"));
                clientes.setEstatura(consulta.getString("c.Estatura"));
                clientes.setFecha_nacimiento(consulta.getString("c.Fecha_nacimiento"));
                clientes.setEdad(consulta.getInt("edad"));
                clientes.setNombreE(consulta.getString("c.Nombres_emergencia"));
                clientes.setApellidoE(consulta.getString("c.Apellidos_emergencia"));
                clientes.setTelefonoE(consulta.getString("c.Telefono_emergencia"));
                clientes.setFechaR(consulta.getString("r.fecha_inicio"));
                clientes.setObservaciones(consulta.getString("r.observacion_clinica"));
                clientes.setIdreg2(consulta.getInt("r.idregistros"));
                return clientes;
            }
            cn.closedConexion();
        } catch (SQLException ex) {
            System.out.println("Error func(traerRegistro) " + ex);
        }
        return null;
    }

    public ArrayList<Integer> TraerEstadistica() {
        ArrayList<Integer> CantidadPersona = new ArrayList<>();
        int hombres = 0, mujeres = 0, todo = 0;
        try {
            cn = new Conexion();
            cn.openConexion();
            String query = "select Genero from clientes;";

            ResultSet consulta = cn.conexiondb.createStatement().executeQuery(query);
            while (consulta.next()) {
                todo++;
                int genero = consulta.getInt("Genero");
                if (genero == 1) {
                    hombres++;
                } else {
                    mujeres++;
                }
            }
            CantidadPersona.add(hombres);
            CantidadPersona.add(mujeres);
            CantidadPersona.add(todo);
            return CantidadPersona;
        } catch (SQLException ex) {
            System.out.println("error func(TraerEstadistica) " + ex);
            return null;
        }

    }

    public int getidCl(String cui) {
        int retorno = 0;
        String query = "select id_Cliente from clientes where Correo_electronico = ?;";

        try {
            cn = new Conexion();
            cn.openConexion();
            PreparedStatement parametro;
            parametro = (PreparedStatement) cn.conexiondb.prepareStatement(query);
            parametro.setString(1, cui);
            ResultSet consulta = parametro.executeQuery();
            while (consulta.next()) {
                retorno = consulta.getInt("id_Cliente");

            }
            cn.closedConexion();
        } catch (SQLException ex) {
            System.out.println("Error func(getididC1) " + ex);
            retorno = 0;
        }
        return retorno;
    }

}
