
package appregistro.bodegaventas.controlador;

import appregistro.bodegaventas.modelo.Cargo;
import appregistro.bodegaventas.modelo.CargoDAO;
import appregistro.bodegaventas.modelo.Vendedor;
import appregistro.bodegaventas.modelo.VendedorDAO;
import appregistro.bodegaventas.vista.vistaCargo;
import appregistro.bodegaventas.vista.vistaMenu;
import appregistro.bodegaventas.vista.vistaVendedor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class VendedorControlador implements ActionListener{
    private vistaVendedor vista;
    private VendedorDAO modelo;
    private CargoDAO cargomodelo;

    public VendedorControlador(vistaVendedor vista, VendedorDAO modelo, CargoDAO cargomodelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.cargomodelo = cargomodelo;
        vista.setVisible(true);
        
        this.vista.getBtnGuardarVendedor().addActionListener(this);
        this.vista.getBtnAgregarCargo().addActionListener(this);
        this.vista.getBtnVolverVendedor().addActionListener(this);
        
        try {
            cargarCargos();
            cargarTablaVendedores();
        } catch (Exception e) {
        }
        
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == vista.getBtnGuardarVendedor()){
            String nombre = vista.getTxtNombreVendedor().getText().toUpperCase();
            String codigoVendedor = randonCodigo();
            int cargo_id = obtenerIdCargoSeleccionado();
            Vendedor vendedor = new Vendedor(nombre, codigoVendedor, cargo_id);
            try {
                modelo.registrarVendedor(vendedor);
                cargarTablaVendedores();
                JOptionPane.showMessageDialog(vista, "Se registro correctamente");
            } catch (Exception ex) {
                System.out.println("No se guardo datos" + ex.getMessage());
            }
            System.out.println(obtenerIdCargoSeleccionado());
        }
        
        if(e.getSource()== vista.getBtnAgregarCargo()){
            cargarVistaCargo();
            cargarCargos();
        }
        if(e.getSource()== vista.getBtnVolverVendedor()){
            cargarVistaMenu();
        }
        
    }
    
    public void cargarTablaVendedores(){
        DefaultTableModel modelotabla = new DefaultTableModel();
        vista.getTablaVendedor().setModel(modelotabla);
        List<Vendedor> vendedores = modelo.obtenerTodosLosVendedores();
        modelotabla.addColumn("ID");
        modelotabla.addColumn("NOMBRE VENDEDOR");
        modelotabla.addColumn("CODIGO VENDEDOR");
        modelotabla.addColumn("CARGO");
        for(Vendedor vendedor : vendedores){
            modelotabla.addRow(new Object[]{vendedor.getId(), vendedor.getNombre(), vendedor.getCodigo_vendedor(), vendedor.getCargo()});
        }
    }
    
    public void cargarCargos(){
        List<Cargo> cargos = cargomodelo.obtenerTodosLosCargos();
        JComboBox<String> cbxCargo = vista.getCbxCargoVendedor();
        if(cargos != null){
            for (Cargo cargo : cargos){
                cbxCargo.addItem(cargo.getCargo());
            }
        }
    }
     public int obtenerIdCargoSeleccionado() {
        JComboBox<String> cbxCargo = vista.getCbxCargoVendedor();
        String nombreCargoSeleccionado = cbxCargo.getItemAt(cbxCargo.getSelectedIndex());

        if (nombreCargoSeleccionado != null) {
            List<Cargo> cargos = cargomodelo.obtenerTodosLosCargos();
            if (cargos != null) {
                for (Cargo cargo : cargos) {
                    if (cargo.getCargo().equals(nombreCargoSeleccionado)) {
                        return cargo.getId(); // Devuelve el ID del cargo encontrado
                    }
                }
            }
        }
        return -1; // Devuelve -1 si no se encuentra el cargo o hay un error
    }
    
     public static String randonCodigo(){
         String Caracteres_permitidos = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
         Random random = new Random();
         StringBuilder codigo = new StringBuilder(8);
         for (int i = 0; i < 8; i++) {
             int indice = random.nextInt(Caracteres_permitidos.length());
             codigo.append(Caracteres_permitidos.charAt(indice));
         }
         return codigo.toString();
     }
     
     public void cargarVistaCargo(){
         vistaCargo vista = new vistaCargo();
         CargoDAO modelo = new CargoDAO();
          new CargoControlador(vista, modelo);
         vista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //se peude cerrar la ventana sin finalizar todo el proceso
         this.vista.setVisible(false);
     }
     public void cargarVistaMenu(){
         vistaMenu vista = new vistaMenu();
         new MenuControlador(vista);
         this.vista.setVisible(false);
     }
     
     
}
