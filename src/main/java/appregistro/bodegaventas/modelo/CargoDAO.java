
package appregistro.bodegaventas.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class CargoDAO {
    
    public void registrarCargo(Cargo cargo){
        Connection conn = ConexionBD.getConexionBD();
        String query = "INSERT INTO cargos (cargo) VALUES (?)";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, cargo.getCargo());
            ps.executeUpdate();
            
            ConexionBD.closeConexion(conn);
        } catch (Exception e) {
            System.out.println("Error al registrar cargo" + e.getMessage());
        }
    }
    
    public List<Cargo> obtenerTodosLosCargos(){
        List<Cargo> cargos = new ArrayList<>();
        Connection conexion = ConexionBD.getConexionBD();
        String query = "SELECT * FROM cargos";
        try {
            PreparedStatement ps = conexion.prepareStatement(query);
            ResultSet resultado = ps.executeQuery();
            while (resultado.next()) {                
                Cargo cargo = new Cargo();
                cargo.setId(resultado.getInt("id"));
                cargo.setCargo(resultado.getString("cargo"));
                cargos.add(cargo);
            }
        } catch (Exception e) {
            System.out.println("Consulta Select error" + e.getMessage());
        }
        ConexionBD.closeConexion(conexion);
        return cargos;
    }
    
}
