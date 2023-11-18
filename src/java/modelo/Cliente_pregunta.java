/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Luis Fernando Paxel
 */
public class Cliente_pregunta {

    private int idPregunta, idRegistro;
    private String respuesta;

    public Cliente_pregunta() {
    }

    public Cliente_pregunta(int idPregunta, String respuesta, int idRegistro) {
        this.idPregunta = idPregunta;
        this.idRegistro = idRegistro;
        this.respuesta = respuesta;

    }

    Conexion cn;

    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public int getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(int idRegistro) {
        this.idRegistro = idRegistro;
    }

    public int insertar_clienteHPre(ArrayList<Integer> id_Pregunta, ArrayList<String> Respuesta, int idReg) {
        int retorno = 0;
        {
            try {
                cn = new Conexion();
                cn.openConexion();
                for (int i = 0; i < id_Pregunta.size(); i++) {

                    PreparedStatement parametro;
                    String query = "insert into historial_clientesclinico(Id_pregunta, Respuesta, id_registro)values(?,?,?);";
                    parametro = (PreparedStatement) cn.conexiondb.prepareStatement(query);
                    parametro.setInt(1, id_Pregunta.get(i));
                    parametro.setString(2, Respuesta.get(i));
                    parametro.setInt(3, idReg);
                    retorno = parametro.executeUpdate();
                }
                cn.closedConexion();

            } catch (SQLException ex) {
                System.out.println("Error  al insertar historial Cliente Pregunta " + ex);
                retorno = 0;
            }
            return retorno;
        }

    }

    public DefaultTableModel PreguntasTable(int id, String simbolo) {
        DefaultTableModel tabla = new DefaultTableModel();
        try {
            cn = new Conexion();
            cn.openConexion();
            String query = "select p.DescripcionPregunta, h.Respuesta, pr.idTipo_pregunta from historial_clientesclinico h inner join  preguntascliente \n"
                    + "p on p.idPreguntaCliente = h.Id_pregunta inner join tipo_preguntas pr on p.id_tipo_pregunta = pr.idTipo_pregunta  \n"
                    + " where h.id_registro =? and pr.idTipo_pregunta" + simbolo + "1;";
            PreparedStatement parametro;
            parametro = (PreparedStatement) cn.conexiondb.prepareStatement(query);
            parametro.setInt(1, id);
            //parametro.setString(2, simbolo);
            ResultSet consulta = parametro.executeQuery();
            String encabezado[] = {"pregunta", "respuesta", "idTipo"};
            tabla.setColumnIdentifiers(encabezado);
            String datos[] = new String[3];
            while (consulta.next()) {
                datos[0] = consulta.getString("DescripcionPregunta");
                datos[1] = consulta.getString("Respuesta");
                datos[2] = consulta.getString("idTipo_pregunta");
                tabla.addRow(datos);
            }
            cn.closedConexion();
        } catch (SQLException ex) {
            System.out.println("Error func(PreguntasTable) " + ex);
        }
        return tabla;
    }

}
