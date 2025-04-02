
package appregistro.bodegaventas.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class VendedorDAO {
    
    public void registrarVendedor(Vendedor vendedor){
        Connection con = ConexionBD.getConexionBD();
        String query = "INSERT INTO vendedores (nombre, codigo_vendedor, cargo_id) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, vendedor.getNombre());
            ps.setString(2, vendedor.getCodigo_vendedor());
            ps.setInt(3, vendedor.getCargo_id());
            ps.executeUpdate();
            
            ConexionBD.closeConexion(con);
        } catch (Exception e) {
            System.out.println("Error al guardar vendedor" + e.getMessage());
        }
    }
    
    
    public List<Vendedor> obtenerTodosLosVendedores(){
        List<Vendedor> vendedores = new ArrayList<>();
        Connection con = ConexionBD.getConexionBD();
        String query = "SELECT v.id, v.nombre, v.codigo_vendedor, v.cargo_id, c.cargo\n" +
                        "FROM vendedores as v INNER JOIN cargos as c\n" +
                        "WHERE c.id = v.cargo_id";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet resultado = ps.executeQuery();
            while (resultado.next()) {                
                Vendedor vendedor = new Vendedor();
                vendedor.setId(resultado.getInt("id"));
                vendedor.setNombre(resultado.getString("nombre"));
                vendedor.setCodigo_vendedor(resultado.getString("codigo_vendedor"));
                vendedor.setCargo_id(resultado.getInt("cargo_id"));
                vendedor.setCargo(resultado.getString("cargo"));
                vendedores.add(vendedor);
                
            }
        } catch (Exception e) {
            System.out.println("No se puedo obtener datos de vendedores" + e.getMessage());
        }
        
        ConexionBD.closeConexion(con);
        return vendedores;
    }
}
