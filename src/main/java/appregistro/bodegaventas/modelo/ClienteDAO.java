package appregistro.bodegaventas.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public void registrarCliente(Cliente cliente) {
        Connection con = ConexionBD.getConexionBD();
        String query = "INSERT INTO clientes (nombre, apellido) VALUES (?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.executeUpdate();

            ConexionBD.closeConexion(con);
        } catch (Exception e) {
            System.out.println("Error al registrar cliente" + e.getMessage());
        }

    }

    public List<Cliente> obtenerTodosLosClientes(){
        List<Cliente> clientes = new ArrayList<>();
        Connection conn = ConexionBD.getConexionBD();
        String query = "SELECT * FROM clientes";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet resultado = ps.executeQuery();
            while (resultado.next()) {                
                Cliente cliente = new Cliente();
                cliente.setId(resultado.getInt("id"));
                cliente.setNombre(resultado.getString("nombre"));
                cliente.setApellido(resultado.getString("apellido"));
                clientes.add(cliente);
            }
        } catch (Exception e) {
            System.out.println("No se puede consultar la base de datos de clientes");
        }
       
        ConexionBD.closeConexion(conn);
        return clientes;
    }
    
}
