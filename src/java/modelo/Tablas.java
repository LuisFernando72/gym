package modelo;

import conexion.Conexion;
import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/*
 * @author Luis Fernando Paxel
 */
public class Tablas {

    public Tablas() {
    }

    Conexion cn;

    public DefaultTableModel table_cliente() {
        DefaultTableModel tabla = new DefaultTableModel();
        try {
            cn = new Conexion();
            cn.openConexion();
            String query = "select c.id_Cliente, r.idregistros, c.Nombres, c.Apellidos, c.NoDPI, c.No_telefono, c.Correo_electronico,TIMESTAMPDIFF(YEAR,c.Fecha_nacimiento,CURDATE()) AS edad ,c.Estatura, r.fecha_inicio\n"
                    + " from registros as r inner join clientes as c on r.id_cliente = c.id_Cliente order by r.idregistros desc;";
            ResultSet consulta = cn.conexiondb.createStatement().executeQuery(query);
            String encabezado[] = {"idcliente", "idregistro", "nombres", "apellidos", "nodpi", "telefono", "correo", "edad", "Estatura",
                "fechainicio"};
            tabla.setColumnIdentifiers(encabezado);
            String datos[] = new String[10];
            while (consulta.next()) {

                datos[0] = consulta.getString("c.id_Cliente");
                datos[1] = consulta.getString("r.idregistros");
                datos[2] = consulta.getString("c.Nombres");
                datos[3] = consulta.getString("c.Apellidos");
                datos[4] = consulta.getString("c.NoDPI");
                datos[5] = consulta.getString("c.No_telefono");
                datos[6] = consulta.getString("c.Correo_electronico");
                datos[7] = consulta.getString("edad");
                datos[8] = consulta.getString("c.Estatura");
                datos[9] = consulta.getString("r.fecha_inicio");
                tabla.addRow(datos);
            }
            cn.closedConexion();
        } catch (SQLException ex) {
            System.out.println("Error func(table_cliente) " + ex);
        }
        return tabla;
    }

    public DefaultTableModel table_usuarios() {
        DefaultTableModel tabla = new DefaultTableModel();
        try {
            cn = new Conexion();
            cn.openConexion();
            String query = "select u.imagen_usuario, u.id_usuario, u.nombres, u.apellidos, u.CUI, u.correo_electronico,   r.nombre as rol, u.fecha_creacion,r.Id_rol from  \n"
                    + "usuarios as u inner join roles as r on u.id_rol = r.id_rol where r.Id_rol < 3;";
            ResultSet consulta = cn.conexiondb.createStatement().executeQuery(query);
            String encabezado[] = {"img", "iduser", "nombres", "apellidos", "cui", "Correo", "rol", "fecha", "RolId"};
            tabla.setColumnIdentifiers(encabezado);
            String datos[] = new String[9];
            while (consulta.next()) {

                datos[0] = consulta.getString("u.imagen_usuario");
                datos[1] = consulta.getString("u.id_usuario");
                datos[2] = consulta.getString("u.nombres");
                datos[3] = consulta.getString("u.apellidos");
                datos[4] = consulta.getString("u.CUI");
                datos[5] = consulta.getString("u.correo_electronico");
                datos[6] = consulta.getString("rol");
                datos[7] = consulta.getString("u.fecha_creacion");
                datos[8] = consulta.getString("r.Id_rol");
                tabla.addRow(datos);
            }
            cn.closedConexion();
        } catch (SQLException ex) {
            System.out.println("Error func(table_usuarios) " + ex);
        }
        return tabla;
    }

}
