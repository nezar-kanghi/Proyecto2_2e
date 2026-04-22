import java.util.Date;
public class Entrada {

    private int id;
    private Concierto concierto;
    private String comprador;
    private int cantidad;
    private Date fechaCompra;

    //Vamos a necesitar un constructor con ID y otro sin ID
    //El constructor sin ID es para insertar en oracle y generar la ID automaticamente
    public Entrada(Concierto concierto, String comprador, int cantidad, Date fechaCompra) {
        this.concierto = concierto;
        this.comprador = comprador;
        this.cantidad = cantidad;
        this.fechaCompra = fechaCompra;
    }

    //el constructor con ID es para leerlo en la base de datos
    public Entrada(int id, Concierto concierto, String comprador, int cantidad, Date fechaCompra) {
        this.id = id;
        this.concierto = concierto;
        this.comprador = comprador;
        this.cantidad = cantidad;
        this.fechaCompra = fechaCompra;
    }

    //getter y setter


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Concierto getConcierto() {
        return concierto;
    }

    public void setConcierto(Concierto concierto) {
        this.concierto = concierto;
    }

    public String getComprador() {
        return comprador;
    }

    public void setComprador(String comprador) {
        this.comprador = comprador;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    @Override
    public String toString() {
        return "Entrada [id=" + id + ", concierto=" + concierto + ", comprador=" + comprador + ", cantidad=" + cantidad + ", fechaCompra=" + fechaCompra + "]";
    }
}