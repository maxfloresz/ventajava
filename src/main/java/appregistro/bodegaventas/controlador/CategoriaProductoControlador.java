/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appregistro.bodegaventas.controlador;

import appregistro.bodegaventas.modelo.CategoriaProducto;
import appregistro.bodegaventas.modelo.CategoriaProductoDAO;
import appregistro.bodegaventas.vista.vistaCategoriaProducto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Max
 */
public class CategoriaProductoControlador implements ActionListener{
    
    private vistaCategoriaProducto vistacategoria;
    private CategoriaProductoDAO modelocategoria;

    public CategoriaProductoControlador(vistaCategoriaProducto vistacategoria, CategoriaProductoDAO modelocategoria) {
        this.vistacategoria = vistacategoria;
        this.modelocategoria = modelocategoria;
        this.vistacategoria.setVisible(true);
        
        this.vistacategoria.getBtnGuardarCategoriaProVista().addActionListener(this);
        
        try {
            actualizarTabla();
            limpiarCampo();
        } catch (Exception e) {
        }
    }
    
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == vistacategoria.getBtnGuardarCategoriaProVista()){
            String nameCategoria = vistacategoria.getTxtCategoriaProductoVista().getText().toUpperCase();
            CategoriaProducto categoria = new CategoriaProducto(nameCategoria);
            try {
                modelocategoria.registrarCategoriaProducto(categoria);
                JOptionPane.showMessageDialog(vistacategoria, "Registro Exitoso");
                actualizarTabla();
                limpiarCampo();
            } catch (Exception ex) {
                System.out.println("No se pudo guardar datos");
            }
        }
    }
    
    public void actualizarTabla(){
        DefaultTableModel modeloTabla = new DefaultTableModel();
        vistacategoria.getTablaCategoriaProductos().setModel(modeloTabla);
        List<CategoriaProducto> categorias = modelocategoria.obtenerTodosLasCategoriasdeProductos();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("CATEGORIA PRODUCTO");
        
        for (CategoriaProducto categoria : categorias) {
            modeloTabla.addRow(new Object[]{categoria.getId(), categoria.getCategoria()});
        }
    }
    
    public void limpiarCampo(){
        vistacategoria.getTxtCategoriaProductoVista().setText("");
        vistacategoria.getTxtCategoriaProductoVista().requestFocus();
    }
    
    
    
}
