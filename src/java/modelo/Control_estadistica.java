package modelo;

import conexion.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * * @author Luis Fernando Paxel
 */
public class Control_estadistica {

    public Control_estadistica() {
    }

    Conexion cn;

    //EDADES    18-28
    public int Intervaloa1() {
        int retorno = 0;
        try {
            cn = new Conexion();
            cn.openConexion();
            String query = "select count(*) as intervalo1 from clientes c  inner join registros r on  r.id_cliente= c.id_Cliente where TIMESTAMPDIFF(YEAR,c.Fecha_nacimiento,CURDATE()) >=  15\n"
                    + "and TIMESTAMPDIFF(YEAR,c.Fecha_nacimiento,CURDATE()) <=  25 and month(r.fecha_inicio) =  month(NOW());";
            ResultSet rs = cn.conexiondb.createStatement().executeQuery(query);
            while (rs.next()) {
                retorno = rs.getInt("intervalo1");
            }
            cn.closedConexion();
        } catch (SQLException ex) {
            System.out.println("Error en la func(Intervaloa1)");

        }
        return retorno;
    }

    //EDADES    29-38
    public int Intervaloa2() {
        int retorno = 0;
        try {
            cn = new Conexion();
            cn.openConexion();
            String query = "select count(*) as intervalo2 from clientes c  inner join registros r on  r.id_cliente= c.id_Cliente where TIMESTAMPDIFF(YEAR,c.Fecha_nacimiento,CURDATE()) >=  26\n"
                    + "and TIMESTAMPDIFF(YEAR,c.Fecha_nacimiento,CURDATE()) <=  35 and  month(r.fecha_inicio) =  month(NOW());";
            ResultSet rs = cn.conexiondb.createStatement().executeQuery(query);
            while (rs.next()) {
                retorno = rs.getInt("intervalo2");
            }
            cn.closedConexion();
        } catch (SQLException ex) {
            System.out.println("Error en la func(Intervaloa2)");

        }
        return retorno;
    }
    //EDADES    39-48

    public int Intervaloa3() {
        int retorno = 0;
        try {
            cn = new Conexion();
            cn.openConexion();
            String query = "select count(*) as intervalo3 from clientes c  inner join registros r on  r.id_cliente= c.id_Cliente where TIMESTAMPDIFF(YEAR,c.Fecha_nacimiento,CURDATE()) >=  36\n"
                    + "and TIMESTAMPDIFF(YEAR,c.Fecha_nacimiento,CURDATE()) <=  45 and month(r.fecha_inicio) =  month(NOW());";
            ResultSet rs = cn.conexiondb.createStatement().executeQuery(query);
            while (rs.next()) {
                retorno = rs.getInt("intervalo3");
            }
            cn.closedConexion();
        } catch (SQLException ex) {
            System.out.println("Error en la func(Intervaloa3)");

        }
        return retorno;
    }

    //EDADES    49-58
    public int Intervaloa4() {
        int retorno = 0;
        try {
            cn = new Conexion();
            cn.openConexion();
            String query = "select count(*) as intervalo4 from clientes c  inner join registros r on  r.id_cliente= c.id_Cliente where TIMESTAMPDIFF(YEAR,c.Fecha_nacimiento,CURDATE()) >=  46\n"
                    + "and TIMESTAMPDIFF(YEAR,c.Fecha_nacimiento,CURDATE()) <=  55 and month(r.fecha_inicio) =  month(NOW());";
            ResultSet rs = cn.conexiondb.createStatement().executeQuery(query);
            while (rs.next()) {
                retorno = rs.getInt("intervalo4");
            }
            cn.closedConexion();
        } catch (SQLException ex) {
            System.out.println("Error en la func(Intervaloa4)");

        }
        return retorno;
    }
    //EDADES   59-68

    public int Intervaloa5() {
        int retorno = 0;
        try {
            cn = new Conexion();
            cn.openConexion();
            String query = "select count(*) as intervalo5 from clientes c  inner join registros r on  r.id_cliente= c.id_Cliente where TIMESTAMPDIFF(YEAR,c.Fecha_nacimiento,CURDATE()) >=  56\n"
                    + "and TIMESTAMPDIFF(YEAR,c.Fecha_nacimiento,CURDATE()) <= 65  and month(r.fecha_inicio) =  month(NOW());";
            ResultSet rs = cn.conexiondb.createStatement().executeQuery(query);
            while (rs.next()) {
                retorno = rs.getInt("intervalo5");
            }
            cn.closedConexion();
        } catch (SQLException ex) {
            System.out.println("Error en la func(Intervaloa5)");

        }
        return retorno;
    }

    //EDADES   +69
    public int Intervaloa6() {
        int retorno = 0;
        try {
            cn = new Conexion();
            cn.openConexion();
            String query = "select count(*) as intervalo6 from clientes c  inner join registros r on  r.id_cliente= c.id_Cliente where TIMESTAMPDIFF(YEAR,c.Fecha_nacimiento,CURDATE()) >=  66\n"
                    + "and month(r.fecha_inicio) =  month(NOW());";
            ResultSet rs = cn.conexiondb.createStatement().executeQuery(query);
            while (rs.next()) {
                retorno = rs.getInt("intervalo6");
            }
            cn.closedConexion();
        } catch (SQLException ex) {
            System.out.println("Error en la func(Intervaloa6)");

        }
        return retorno;
    }

    //SEGUNDO INTERVALO
    //EDADES    18-28
    public int Intervalob1() {
        int retorno = 0;
        try {
            cn = new Conexion();
            cn.openConexion();
            String query = "select count(*) as intervalo1 from clientes c  inner join registros r on  r.id_cliente= c.id_Cliente where TIMESTAMPDIFF(YEAR,c.Fecha_nacimiento,CURDATE()) >=  15\n"
                    + "and TIMESTAMPDIFF(YEAR,c.Fecha_nacimiento,CURDATE()) <=  25 and month(r.fecha_inicio) =  month(NOW()) -1;";
            ResultSet rs = cn.conexiondb.createStatement().executeQuery(query);
            while (rs.next()) {
                retorno = rs.getInt("intervalo1");
            }
            cn.closedConexion();
        } catch (SQLException ex) {
            System.out.println("Error en la func(Intervalob1)");

        }
        return retorno;
    }

    //EDADES    29-38
    public int Intervalob2() {
        int retorno = 0;
        try {
            cn = new Conexion();
            cn.openConexion();
            String query = "select count(*) as intervalo2 from clientes c  inner join registros r on  r.id_cliente= c.id_Cliente where TIMESTAMPDIFF(YEAR,c.Fecha_nacimiento,CURDATE()) >=  26\n"
                    + "and TIMESTAMPDIFF(YEAR,c.Fecha_nacimiento,CURDATE()) <=  35 and month(r.fecha_inicio) =  month(NOW()) -1;";
            ResultSet rs = cn.conexiondb.createStatement().executeQuery(query);
            while (rs.next()) {
                retorno = rs.getInt("intervalo2");
            }
            cn.closedConexion();
        } catch (SQLException ex) {
            System.out.println("Error en la func(Intervalob2)");

        }
        return retorno;
    }
    //EDADES    39-48

    public int Intervalob3() {
        int retorno = 0;
        try {
            cn = new Conexion();
            cn.openConexion();
            String query = "select count(*) as intervalo3 from clientes c  inner join registros r on  r.id_cliente= c.id_Cliente where TIMESTAMPDIFF(YEAR,c.Fecha_nacimiento,CURDATE()) >=  36\n"
                    + "and TIMESTAMPDIFF(YEAR,c.Fecha_nacimiento,CURDATE()) <=  45 and month(r.fecha_inicio) =  month(NOW()) -1;";
            ResultSet rs = cn.conexiondb.createStatement().executeQuery(query);
            while (rs.next()) {
                retorno = rs.getInt("intervalo3");
            }
            cn.closedConexion();
        } catch (SQLException ex) {
            System.out.println("Error en la func(Intervalob3)");

        }
        return retorno;
    }

    //EDADES    49-58
    public int Intervalob4() {
        int retorno = 0;
        try {
            cn = new Conexion();
            cn.openConexion();
            String query = "select count(*) as intervalo4 from clientes c  inner join registros r on  r.id_cliente= c.id_Cliente where TIMESTAMPDIFF(YEAR,c.Fecha_nacimiento,CURDATE()) >=  46\n"
                    + "and TIMESTAMPDIFF(YEAR,c.Fecha_nacimiento,CURDATE()) <=  55 and month(r.fecha_inicio) =  month(NOW()) -1;";
            ResultSet rs = cn.conexiondb.createStatement().executeQuery(query);
            while (rs.next()) {
                retorno = rs.getInt("intervalo4");
            }
            cn.closedConexion();
        } catch (SQLException ex) {
            System.out.println("Error en la func(Intervalob4)");

        }
        return retorno;
    }
    //EDADES   59-68

    public int Intervalob5() {
        int retorno = 0;
        try {
            cn = new Conexion();
            cn.openConexion();
            String query = "select count(*) as intervalo5 from clientes c  inner join registros r on  r.id_cliente= c.id_Cliente where TIMESTAMPDIFF(YEAR,c.Fecha_nacimiento,CURDATE()) >=  56\n"
                    + "and TIMESTAMPDIFF(YEAR,c.Fecha_nacimiento,CURDATE()) <= 65 and month(r.fecha_inicio) =  month(NOW()) -1;";
            ResultSet rs = cn.conexiondb.createStatement().executeQuery(query);
            while (rs.next()) {
                retorno = rs.getInt("intervalo5");
            }
            cn.closedConexion();
        } catch (SQLException ex) {
            System.out.println("Error en la func(Intervalob5)");

        }
        return retorno;
    }

    //EDADES   +69
    public int Intervalob6() {
        int retorno = 0;
        try {
            cn = new Conexion();
            cn.openConexion();
            String query = "select count(*) as intervalo6 from clientes c  inner join registros r on  r.id_cliente= c.id_Cliente where TIMESTAMPDIFF(YEAR,c.Fecha_nacimiento,CURDATE()) >=  66\n"
                    + "and month(r.fecha_inicio) =  month(NOW()) - 1;";
            ResultSet rs = cn.conexiondb.createStatement().executeQuery(query);
            while (rs.next()) {
                retorno = rs.getInt("intervalo6");
            }
            cn.closedConexion();
        } catch (SQLException ex) {
            System.out.println("Error en la func(Intervalob6)");

        }
        return retorno;
    }

    public ArrayList<Integer> RegistroPorSemanaEdad1() {
        ArrayList<Integer> RegistroSemana = new ArrayList<>();
        try {
            int intervaloA1 = Intervaloa1();
            RegistroSemana.add(intervaloA1);
            int intervaloA2 = Intervaloa2();
            RegistroSemana.add(intervaloA2);
            int intervaloA3 = Intervaloa3();
            RegistroSemana.add(intervaloA3);
            int intervaloA4 = Intervaloa4();
            RegistroSemana.add(intervaloA4);
            int intervaloA5 = Intervaloa5();
            RegistroSemana.add(intervaloA5);
            int intervaloA6 = Intervaloa6();
            RegistroSemana.add(intervaloA6);
            return RegistroSemana;
        } catch (Exception ex) {
            System.out.println("error func(RegistroSemanaEdad1) " + ex);
            return null;
        }
    }

    public ArrayList<Integer> RegistroPorSemanaEdad2() {
        ArrayList<Integer> RegistroSemana = new ArrayList<>();
        try {
            int intervaloB1 = Intervalob1();
            RegistroSemana.add(intervaloB1);
            int intervaloB2 = Intervalob2();
            RegistroSemana.add(intervaloB2);
            int intervaloB3 = Intervalob3();
            RegistroSemana.add(intervaloB3);
            int intervaloB4 = Intervalob4();
            RegistroSemana.add(intervaloB4);
            int intervaloB5 = Intervalob5();
            RegistroSemana.add(intervaloB5);
            int intervaloB6 = Intervalob6();
            RegistroSemana.add(intervaloB6);
            return RegistroSemana;

        } catch (Exception ex) {
            System.out.println("error func(RegistroSemanaEdad2) " + ex);
            return null;
        }
    }

}
