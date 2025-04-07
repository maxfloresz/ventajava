/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appregistro.bodegaventas.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoriaProductoDAO {
    
    public void registrarCategoriaProducto(CategoriaProducto categoriaProducto){
        Connection con = ConexionBD.getConexionBD();
        String query = "INSERT INTO categoria_producto(categoria) VALUES (?)";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, categoriaProducto.getCategoria());
            ps.executeUpdate();
            
            ConexionBD.closeConexion(con);
            
        } catch (Exception e) {
            System.out.println("Error al guardar Categoria"+ e.getMessage());
        }
    }
    
    public List<CategoriaProducto> obtenerTodosLasCategoriasdeProductos(){
        List<CategoriaProducto> categorias = new ArrayList<>();
        Connection con = ConexionBD.getConexionBD();
        String query = "SELECT * FROM categoria_producto";
        try {
            
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet resultado = ps.executeQuery();
            while (resultado.next()) {                
                CategoriaProducto categoria = new CategoriaProducto();
                categoria.setId(resultado.getInt("id"));
                categoria.setCategoria(resultado.getString("categoria"));
                categorias.add(categoria);
            }
            
        } catch (Exception e) {
            System.out.println("No se pudo consultar a la base de datos \n" +e.getMessage());
        }
        
        ConexionBD.closeConexion(con);
        return categorias;
    }
}
