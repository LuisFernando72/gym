package modelo;

import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/* * @author Luis Fernando Paxel
 */
public class Registros {

    private int idRegistro, idCliente;
    private String observacion_clinica, fecha_inicio;

    public Registros() {
    }
    Conexion cn;

    public Registros(int idCliente, String fecha_inicio) {
        this.idCliente = idCliente;
        this.fecha_inicio = fecha_inicio;
    }

    public int getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(int idRegistro) {
        this.idRegistro = idRegistro;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getObservacion_clinica() {
        return observacion_clinica;
    }

    public void setObservacion_clinica(String observacion_clinica) {
        this.observacion_clinica = observacion_clinica;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public int InsertarRegistro() {
        int retorno;
        {
            try {
                ZonedDateTime tres = ZonedDateTime.now(ZoneId.of("America/New_York"));
                int hora = tres.getHour() - 1;
                String fechaRegistro = tres.getYear() + "-" + tres.getMonthValue() + "-" + tres.getDayOfMonth() + " " + hora + ":" + tres.getMinute() + ":" + "00";

                cn = new Conexion();
                cn.openConexion();
                PreparedStatement parametro;
                String query = "insert into registros(id_cliente,fecha_inicio)values(?, ?);";
                parametro = (PreparedStatement) cn.conexiondb.prepareStatement(query);

                parametro.setInt(1, this.getIdCliente());
                parametro.setString(2, fechaRegistro);
                retorno = parametro.executeUpdate();
                cn.closedConexion();

            } catch (SQLException ex) {
                System.out.println("Error func(InsertarRegistro) " + ex);
                retorno = 0;
            }
            return retorno;
        }

    }

    public int buscar_idRegistro(int id_cliente) {
        String query = "select idregistros from registros where id_cliente = ?;";
        int retorno = 0;

        try {
            cn = new Conexion();
            cn.openConexion();
            PreparedStatement parametro;
            parametro = (PreparedStatement) cn.conexiondb.prepareStatement(query);
            parametro.setInt(1, id_cliente);
            ResultSet watch = parametro.executeQuery();

            while (watch.next()) {
                retorno = watch.getInt("idregistros");
            }

            cn.closedConexion();
        } catch (SQLException ex) {
            System.out.println("Error func(buscar_idRegistro) " + ex);
            retorno = 0;
        }

        return retorno;
    }

    //DIAS DE LA SEMANA INICIO LUNES
    public int RegistrosLunes() {
        int retorno = 0;
        try {
            cn = new Conexion();
            cn.openConexion();
            String query = "SELECT count(idregistros) as lunes, fecha_inicio from registros\n"
                    + "WHERE DAYOFWEEK(fecha_inicio)=2;";
            ResultSet rs = cn.conexiondb.createStatement().executeQuery(query);
            while (rs.next()) {
                retorno = rs.getInt("lunes");
            }

            cn.closedConexion();
        } catch (SQLException ex) {
            System.out.println("Error en la func(RegistrosLunes)");

        }
        return retorno;
    }

    //REGISTROS MARTES
    public int RegistrosMartes() {
        int retorno = 0;
        try {
            cn = new Conexion();
            cn.openConexion();
            String query = "SELECT count(idregistros) as martes, fecha_inicio from registros\n"
                    + "WHERE DAYOFWEEK(fecha_inicio)=3;";
            ResultSet rs = cn.conexiondb.createStatement().executeQuery(query);
            while (rs.next()) {
                retorno = rs.getInt("martes");
            }

            cn.closedConexion();
        } catch (SQLException ex) {
            System.out.println("Error en la func(RegistrosMartes)");

        }
        return retorno;
    }

    //REGISTROS MIERCOLES
    public int RegistrosMiercoles() {
        int retorno = 0;
        try {
            cn = new Conexion();
            cn.openConexion();
            String query = "SELECT count(idregistros) as miercoles, fecha_inicio from registros\n"
                    + "WHERE DAYOFWEEK(fecha_inicio)=4;";
            ResultSet rs = cn.conexiondb.createStatement().executeQuery(query);
            while (rs.next()) {
                retorno = rs.getInt("miercoles");
            }

            cn.closedConexion();
        } catch (SQLException ex) {
            System.out.println("Error en la func(RegistrosMiercoles)");

        }
        return retorno;
    }
//REGISTROS JUEVES

    public int RegistrosJueves() {
        int retorno = 0;
        try {
            cn = new Conexion();
            cn.openConexion();
            String query = "SELECT count(idregistros) as jueves, fecha_inicio from registros\n"
                    + "WHERE DAYOFWEEK(fecha_inicio)=5;";
            ResultSet rs = cn.conexiondb.createStatement().executeQuery(query);
            while (rs.next()) {
                retorno = rs.getInt("jueves");
            }

            cn.closedConexion();
        } catch (SQLException ex) {
            System.out.println("Error en la func(RegistrosJueves)");

        }
        return retorno;
    }

    //REGISTROS VIERNES
    public int RegistrosViernes() {
        int retorno = 0;
        try {
            cn = new Conexion();
            cn.openConexion();
            String query = "SELECT count(idregistros) as viernes, fecha_inicio from registros\n"
                    + "WHERE DAYOFWEEK(fecha_inicio)=6;";
            ResultSet rs = cn.conexiondb.createStatement().executeQuery(query);
            while (rs.next()) {
                retorno = rs.getInt("viernes");
            }

            cn.closedConexion();
        } catch (SQLException ex) {
            System.out.println("Error en la func(viernes)");

        }
        return retorno;
    }
//REGISTROS SABADO

    public int RegistrosSabado() {
        int retorno = 0;
        try {
            cn = new Conexion();
            cn.openConexion();
            String query = "SELECT count(idregistros) as sabado, fecha_inicio from registros\n"
                    + "WHERE DAYOFWEEK(fecha_inicio)=7;";
            ResultSet rs = cn.conexiondb.createStatement().executeQuery(query);
            while (rs.next()) {
                retorno = rs.getInt("sabado");
            }

            cn.closedConexion();
        } catch (SQLException ex) {
            System.out.println("Error en la func(RegistrosSabado)");

        }
        return retorno;
    }

    //REGISTROS DOMINGO
    public int RegistrosDomingo() {
        int retorno = 0;
        try {
            cn = new Conexion();
            cn.openConexion();
            String query = "SELECT count(idregistros) as domingo, fecha_inicio from registros\n"
                    + "WHERE DAYOFWEEK(fecha_inicio)=1;";
            ResultSet rs = cn.conexiondb.createStatement().executeQuery(query);
            while (rs.next()) {
                retorno = rs.getInt("domingo");
            }

            cn.closedConexion();
        } catch (SQLException ex) {
            System.out.println("Error en la func(RegistrosDomingo)");

        }
        return retorno;
    }

    public ArrayList<Integer> RegistrosPorDia() {
        ArrayList<Integer> RegistroDia = new ArrayList<>();
        try {
            int lunes = RegistrosLunes();
            RegistroDia.add(lunes);
            int martes = RegistrosMartes();
            RegistroDia.add(martes);
            int miercoles = RegistrosMiercoles();
            RegistroDia.add(miercoles);
            int jueves = RegistrosJueves();
            RegistroDia.add(jueves);
            int viernes = RegistrosViernes();
            RegistroDia.add(viernes);
            int sabado = RegistrosSabado();
            RegistroDia.add(sabado);
            int domingo = RegistrosDomingo();
            RegistroDia.add(domingo);
            return RegistroDia;
        } catch (Exception ex) {
            System.out.println("error func(TraerEstadistica) " + ex);
            return null;
        }
    }
}
