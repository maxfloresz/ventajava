package appregistro.bodegaventas.controlador;

import appregistro.bodegaventas.modelo.Cargo;
import appregistro.bodegaventas.modelo.CargoDAO;
import appregistro.bodegaventas.modelo.VendedorDAO;
import appregistro.bodegaventas.vista.vistaCargo;
import appregistro.bodegaventas.vista.vistaVendedor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CargoControlador implements ActionListener {

    private vistaCargo vistacargo;
    private CargoDAO modelo;

    public CargoControlador(vistaCargo vistacargo, CargoDAO modelo) {
        this.vistacargo = vistacargo;
        this.modelo = modelo;
        this.vistacargo.setVisible(true);

        this.vistacargo.getBtnGuardarCargo().addActionListener(this);
        this.vistacargo.getBtnVolverCargo().addActionListener(this);
        
        try {
            actualizarTabla();
            limpiarCampos();
        } catch (Exception e) {
            
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vistacargo.getBtnGuardarCargo()) {
            String cargoNombre = vistacargo.getTxtNombreCargo().getText().toUpperCase();
            Cargo cargo = new Cargo(cargoNombre);
            try {
                modelo.registrarCargo(cargo);
                JOptionPane.showMessageDialog(vistacargo, "Se registro cargo");
                actualizarTabla();
                limpiarCampos();
            } catch (Exception ex) {
                System.out.println("Error el registrar" + ex.getMessage());
            }
        }
        if(e.getSource()== this.vistacargo.getBtnVolverCargo()){
            vistaVendedor vistavendedor= new vistaVendedor();
            VendedorDAO modelovendedor = new VendedorDAO();
            new VendedorControlador(vistavendedor, modelovendedor, modelo);
            vistacargo.setVisible(false);
        }
        
    }
    public void actualizarTabla(){
        DefaultTableModel modelotabla = new DefaultTableModel();
        vistacargo.getTablaCargos().setModel(modelotabla);
        List<Cargo> cargos = modelo.obtenerTodosLosCargos();
        modelotabla.addColumn("ID");
        modelotabla.addColumn("CARGO");
        
        for (Cargo cargo : cargos) {
            modelotabla.addRow(new Object[]{cargo.getId(), cargo.getCargo()});
        }
    }

    public void limpiarCampos(){
        vistacargo.getTxtNombreCargo().setText("");
        vistacargo.getTxtNombreCargo().requestFocus();
    }
}
