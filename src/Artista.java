public class Artista {

    private int id;
    private String nombre;
    private String generoMusical;
    private String paisOrigen;

    //Vamos a necesitar un constructor con ID y otro sin ID
    //El constructor sin ID es para insertar en oracle y generar la ID automaticamente
    public Artista(String nombre, String generoMusical, String paisOrigen) {
        this.nombre = nombre;
        this.generoMusical = generoMusical;
        this.paisOrigen = paisOrigen;
    }

    //el constructor con ID es para leerlo en la base de datos
    public Artista(int id, String nombre, String generoMusical, String paisOrigen) {
        this.id = id;
        this.nombre = nombre;
        this.generoMusical = generoMusical;
        this.paisOrigen = paisOrigen;
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

    public String getGeneroMusical() {
        return generoMusical;
    }

    public void setGeneroMusical(String generoMusical) {
        this.generoMusical = generoMusical;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    @Override
    public String toString() {
        return "Artista [id=" + id + ", nombre=" + nombre + ", genero=" + generoMusical + ", pais=" + paisOrigen + "]";
    }
}