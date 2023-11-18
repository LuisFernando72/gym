package modelo;

import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Luis Fernando Paxel
 */
public class Condicion_cliente {

    private int idRegistro, SaludGeneral, condicion_fisica, nivel_nutricional, nivel_estres;

    public Condicion_cliente() {
    }

    public Condicion_cliente(int idRegistro, int SaludGeneral, int condicion_fisica, int nivel_nutricional, int nivel_estres) {
        this.idRegistro = idRegistro;
        this.SaludGeneral = SaludGeneral;
        this.condicion_fisica = condicion_fisica;
        this.nivel_nutricional = nivel_nutricional;
        this.nivel_estres = nivel_estres;
    }

    Conexion cn;

    public int getSaludGeneral() {
        return SaludGeneral;
    }

    public void setSaludGeneral(int SaludGeneral) {
        this.SaludGeneral = SaludGeneral;
    }

    public int getCondicion_fisica() {
        return condicion_fisica;
    }

    public void setCondicion_fisica(int condicion_fisica) {
        this.condicion_fisica = condicion_fisica;
    }

    public int getNivel_nutricional() {
        return nivel_nutricional;
    }

    public void setNivel_nutricional(int nivel_nutricional) {
        this.nivel_nutricional = nivel_nutricional;
    }

    public int getNivel_estres() {
        return nivel_estres;
    }

    public void setNivel_estres(int nivel_estres) {
        this.nivel_estres = nivel_estres;
    }

    public int getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(int idRegistro) {
        this.idRegistro = idRegistro;
    }

    public int insertarCondiciones_E(ArrayList<Integer> nivel, int id_registro) {
        int retorno;

        {
            try {
                cn = new Conexion();
                cn.openConexion();

                PreparedStatement parametro;
                String query = "insert into condiciones_energia(idRegistro, SaludGeneral, Condicion_fisica, Nivel_Nutricional, Nivel_Estres)values(?,?,?,?,?);";

                parametro = (PreparedStatement) cn.conexiondb.prepareStatement(query);
                parametro.setInt(1, id_registro);
                parametro.setInt(2, nivel.get(0));
                parametro.setInt(3, nivel.get(1));
                parametro.setInt(4, nivel.get(2));
                parametro.setInt(5, nivel.get(3));
                retorno = parametro.executeUpdate();

                cn.closedConexion();

            } catch (SQLException ex) {
                System.out.println("Error insertar Condiciones Energia " + ex);
                retorno = 0;
            }
            return retorno;
        }

    }

    public Condicion_cliente CondicionesE(int idRegistro) {
        try {
            cn = new Conexion();
            cn.openConexion();
            String query = "select * from condiciones_energia where idRegistro =?";
            PreparedStatement parametro;
            parametro = (PreparedStatement) cn.conexiondb.prepareStatement(query);
            parametro.setInt(1, idRegistro);

            ResultSet rs = parametro.executeQuery();
            while (rs.next()) {
                Condicion_cliente cc = new Condicion_cliente();
                cc.setSaludGeneral(rs.getInt("SaludGeneral"));
                cc.setCondicion_fisica(rs.getInt("Condicion_fisica"));
                cc.setNivel_nutricional(rs.getInt("Nivel_Nutricional"));
                cc.setNivel_estres(rs.getInt("Nivel_Estres"));
                return cc;
            }
            cn.closedConexion();
        } catch (SQLException ex) {
            System.out.println("Error func(CondicionesE) " + ex);
        }
        return null;
    }

}
