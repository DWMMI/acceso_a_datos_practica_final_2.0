import java.io.Serializable;

// (1 Punto) Preparar una clase Pais, serializable, preparada para almacenar la
//información de los países. Deberán contener todos sus datos; se recomienda que
//contenga un constructor, sus respectivos getters y setters y que se sobreescriba el
//método toString() para que puedan imprimirse por pantalla dichos objetos (Eclipse
//puede hacer to*do eso automáticamente).
public class Pais implements Serializable {
    private String nombre;
    private String presidente;
    private int pib;
    private double coeficientegini;

    public Pais(String nombre, String presidente, int pib, double coeficientegini) {
        this.nombre = nombre;
        this.presidente = presidente;
        this.pib = pib;
        this.coeficientegini = coeficientegini;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPresidente() {
        return presidente;
    }

    public void setPresidente(String presidente) {
        this.presidente = presidente;
    }

    public int getPib() {
        return pib;
    }

    public void setPib(int Pib) {
        this.pib = pib;
    }

    public double getCoeficientegini() {
        return coeficientegini;
    }

    public void setCoeficientegini(double coeficientegini) {
        this.coeficientegini = coeficientegini;
    }

    @Override
    public String toString() {
        return "Pais{" +
                "nombre='" + nombre + '\'' +
                ", presidente='" + presidente + '\'' +
                ", pib=" + pib +
                ", coeficientegini=" + coeficientegini +
                '}';
    }
}
