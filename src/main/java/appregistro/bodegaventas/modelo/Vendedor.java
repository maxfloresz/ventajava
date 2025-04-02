package appregistro.bodegaventas.modelo;

public class Vendedor {

    private int id;
    private String nombre;
    private String codigo_vendedor;
    private int cargo_id;
    private String cargo;

    public Vendedor() {
    }

    public Vendedor(String nombre, String codigo_vendedor, int cargo_id, String cargo) {
        this.nombre = nombre;
        this.codigo_vendedor = codigo_vendedor;
        this.cargo_id = cargo_id;
        this.cargo = cargo;
    }
    public Vendedor(String nombre, String codigo_vendedor, int cargo_id) {
        this.nombre = nombre;
        this.codigo_vendedor = codigo_vendedor;
        this.cargo_id = cargo_id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo_vendedor() {
        return codigo_vendedor;
    }

    public void setCodigo_vendedor(String codigo_vendedor) {
        this.codigo_vendedor = codigo_vendedor;
    }

    public int getCargo_id() {
        return cargo_id;
    }

    public void setCargo_id(int cargo_id) {
        this.cargo_id = cargo_id;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

}
