/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appregistro.bodegaventas.controlador;

import appregistro.bodegaventas.modelo.CargoDAO;
import appregistro.bodegaventas.modelo.ClienteDAO;
import appregistro.bodegaventas.modelo.VendedorDAO;
import appregistro.bodegaventas.vista.vistaCliente;
import appregistro.bodegaventas.vista.vistaMenu;
import appregistro.bodegaventas.vista.vistaVendedor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuControlador implements ActionListener {

    private vistaMenu vistamenu;

    public MenuControlador(vistaMenu vistamenu) {
        this.vistamenu = vistamenu;

        vistamenu.setVisible(true);
        this.vistamenu.getBtnClientesRegistro().addActionListener(this);
        this.vistamenu.getBtnVistaVendedor().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vistamenu.getBtnClientesRegistro()) {
            vistaCliente vista = new vistaCliente();
            ClienteDAO modelo = new ClienteDAO();
            ClienteControlador cliente = new ClienteControlador(vista, modelo);

            this.vistamenu.setVisible(false);
        }
        if (e.getSource() == this.vistamenu.getBtnVistaVendedor()) {
            iniciarVistaVendedor();
        }
    }

    private void iniciarVistaVendedor() {
        vistaVendedor vista = new vistaVendedor();
        VendedorDAO modelo = new VendedorDAO();
        CargoDAO modeloCargo = new CargoDAO();
        new VendedorControlador(vista, modelo, modeloCargo);
        this.vistamenu.setVisible(false);
                
    }

}
