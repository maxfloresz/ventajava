/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appregistro.bodegaventas.modelo;

/**
 *
 * @author Max
 */
public class CategoriaProducto {
    private int id;
    private String categoria;

    public CategoriaProducto( String categoria) {
        this.categoria = categoria;
    }

    public CategoriaProducto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    
    
}
