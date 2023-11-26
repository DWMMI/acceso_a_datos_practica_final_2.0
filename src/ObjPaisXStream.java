import com.thoughtworks.xstream.XStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class ObjPaisXStream {
    public static void main(String[] args) {
        // Crear una instancia de la clase XStream
        XStream xs = new XStream();

        // Permitir cualquier tipo procedente del mismo paquete
        // xs.allowTypesByWildcard(new String[]{"src.*"});

        // Relacionar las etiquetas con la clase que se corresponden
        xs.alias("paises", List.class);
        xs.alias("pais", Pais.class);

// Permitir cualquier tipo procedente del mismo paquete
       // xs.allowTypesByWildcard(new String[]{"com.your.package.*"});

// Permitir la clase específica
        xs.allowTypes(new Class[]{Pais.class, ListaPaises.class});

// Establecer un canal al fichero XML y volcar el contenido en una lista de objetos de tipo Pais
        try {
            FileInputStream fis = new FileInputStream("paises.xml");
            List<Pais> paises = (List<Pais>) xs.fromXML(fis);
            for (Pais pais : paises) {
                System.out.println(pais.getNombre() + " - Presidente: " + pais.getPresidente());
                // Agrega cualquier otra lógica que necesites
            }
            // Resto del código...
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("No se pudo encontrar el archivo paises.xml. Verifica la ubicación del archivo.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Se produjo un error durante la lectura del archivo.");
        }
    }
}
