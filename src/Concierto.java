import java.util.Date;
public class Concierto {

    private int id;
    private Artista artista;
    private Date fecha;
    private String lugar;
    private double precioEntrada;

    //Vamos a necesitar un constructor con ID y otro sin ID
    //El constructor sin ID es para insertar en oracle y generar la ID automaticamente
    public Concierto(Artista artista, Date fecha, String lugar, double precioEntrada) {
        this.artista = artista;
        this.fecha = fecha;
        this.lugar = lugar;
        this.precioEntrada = precioEntrada;
    }

    //el constructor con ID es para leerlo en la base de datos
    public Concierto(int id, Artista artista, Date fecha, String lugar, double precioEntrada) {
        this.id = id;
        this.artista = artista;
        this.fecha = fecha;
        this.lugar = lugar;
        this.precioEntrada = precioEntrada;
    }

    //getter setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public double getPrecioEntrada() {
        return precioEntrada;
    }

    public void setPrecioEntrada(double precioEntrada) {
        this.precioEntrada = precioEntrada;
    }

    @Override
    public String toString() {
        return "Concierto [id=" + id + ", artista=" + artista + ", fecha=" + fecha + ", lugar=" + lugar + ", precio=" + precioEntrada + "]";
    }
}