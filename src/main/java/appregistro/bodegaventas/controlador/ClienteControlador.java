
package appregistro.bodegaventas.controlador;

import appregistro.bodegaventas.modelo.Cliente;
import appregistro.bodegaventas.modelo.ClienteDAO;
import appregistro.bodegaventas.vista.vistaCliente;
import appregistro.bodegaventas.vista.vistaMenu;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ClienteControlador implements ActionListener{
    private vistaCliente vistacliente;
    private ClienteDAO clientemodelo;

    public ClienteControlador(vistaCliente vistacliente, ClienteDAO clientemodelo) {
        this.vistacliente = vistacliente;
        this.clientemodelo = clientemodelo;
        this.vistacliente.setVisible(true);
        
        this.vistacliente.getBtnGuardarCliente().addActionListener(this);
        this.vistacliente.getBtnVolverCliente().addActionListener(this);
        
        try {
            this.actualizarTabla();
            this.limpiarCampos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vistacliente, "Error al cargar tabla");
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == this.vistacliente.getBtnGuardarCliente()){
            String nombre = vistacliente.getTxtNombreCliente().getText().toUpperCase();
            String apellido = vistacliente.getTxtApellidoCliente().getText().toUpperCase();
            System.out.println(nombre);
            System.out.println(apellido);
            Cliente cliente = new Cliente(nombre, apellido);
            
            try {
                clientemodelo.registrarCliente(cliente);
                JOptionPane.showMessageDialog(vistacliente, "Se guardo cliente");
                actualizarTabla();
                limpiarCampos();
            } catch (Exception ex) {
                System.out.println("No se puedo guardar cliente" + ex.getMessage());
            }
            
        }
        
        if(e.getSource() == this.vistacliente.getBtnVolverCliente()){
            vistaMenu vistamenu = new vistaMenu();
            MenuControlador controlador = new MenuControlador(vistamenu);
            vistacliente.setVisible(false);
        }
    }
    
    public void actualizarTabla (){
        DefaultTableModel modelotabla = new DefaultTableModel();
        vistacliente.getTableCliente().setModel(modelotabla);
        List<Cliente> clientes = clientemodelo.obtenerTodosLosClientes();
        modelotabla.addColumn("ID");
        modelotabla.addColumn("NOMBRE");
        modelotabla.addColumn("APELLIDO");
        
        for(Cliente cliente : clientes){
            modelotabla.addRow(new Object[]{cliente.getId(), cliente.getNombre(), cliente.getApellido()});
        }
        
    }
    
    public void limpiarCampos(){
        vistacliente.getTxtNombreCliente().setText("");
        vistacliente.getTxtApellidoCliente().setText("");
        vistacliente.getTxtNombreCliente().requestFocus();
    }
    
}
