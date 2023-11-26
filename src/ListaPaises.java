
//Crear una clase ListaPaises, preparada para
//almacenar objetos de tipo Pais, y que pueda utilizarse por XStream.
import java.util.ArrayList;
import java.util.List;

public class ListaPaises {
    private List<Pais> lista = new ArrayList<Pais>();

    public ListaPaises() {
    }

    public void add(Pais a) {
        lista.add(a);
    }

    public List<Pais> getListaPaises() {
        return lista;
    }
}
