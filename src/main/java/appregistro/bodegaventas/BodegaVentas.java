/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package appregistro.bodegaventas;

import appregistro.bodegaventas.controlador.CargoControlador;
import appregistro.bodegaventas.controlador.MenuControlador;
import appregistro.bodegaventas.modelo.CargoDAO;
import appregistro.bodegaventas.vista.vistaCargo;
import appregistro.bodegaventas.vista.vistaMenu;

/**
 *
 * @author Max
 */
public class BodegaVentas {

    public static void main(String[] args) {
        
        vistaMenu vista = new vistaMenu();
        MenuControlador controller = new MenuControlador(vista);
        
//            vistaCargo vista = new vistaCargo();
//            CargoDAO modelo = new CargoDAO();
//            CargoControlador controller = new CargoControlador(vista,modelo);
//            vista.setVisible(true);
    }
}
