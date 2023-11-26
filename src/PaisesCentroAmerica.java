
import java.io.*;
import java.util.List;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import com.thoughtworks.xstream.XStream;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;


public class PaisesCentroAmerica {
    public static void main(String[] args) throws ParserConfigurationException, TransformerException, IOException {
        // ESCRIBIR MEDIANTE DOM EN .XML
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation implementation = builder.getDOMImplementation();
        Document document = implementation.createDocument(null, "paises", null);
        document.setXmlVersion("1.0");

        String paises[] = new String[8];
        paises[0] = "Belice";
        paises[1] = "El Salvador";
        paises[2] = "Guatemala";
        paises[3] = "Honduras";
        paises[4] = "Nicaragua";
        paises[5] = "México";
        paises[6] = "Panamá";
        paises[7] = "Costa Rica";

        String presidente[] = new String[8];
        presidente[0] = "Froyla Tzalam";
        presidente[1] = "Nayib Bukele";
        presidente[2] = "Alejandro Giammattei";
        presidente[3] = "Xiomara Castro";
        presidente[4] = "Daniel Ortega";
        presidente[5] = "Andrés Manuel López Obrador";
        presidente[6] = "Laurentino Cortizo";
        presidente[7] = "Rodrigo Chaves";

        int PIB[] = new int[8];
        PIB[0] = 1987;
        PIB[1] = 74818;
        PIB[2] = 185473;
        PIB[3] = 85625;
        PIB[4] = 47770;
        PIB[5] = 2890685;
        PIB[6] = 128500;
        PIB[7] = 129950;

        double coeficienteGini[] = new double[8];
        coeficienteGini[0] = 53.3;
        coeficienteGini[1] = 38.8;
        coeficienteGini[2] = 48.3;
        coeficienteGini[3] = 48.2;
        coeficienteGini[4] = 46.2;
        coeficienteGini[5] = 45.4;
        coeficienteGini[6] = 50.9;
        coeficienteGini[7] = 47.2;

        for (int i = 0; i < 8; i++) {
            Element elemento = document.createElement("pais");
            document.getDocumentElement().appendChild(elemento);

            Element elemFinal = document.createElement("nombre");
            Text text = document.createTextNode(paises[i]);
            elemento.appendChild(elemFinal);
            elemFinal.appendChild(text);

            Element elemFinal2 = document.createElement("presidente");
            Text text2 = document.createTextNode(presidente[i]);
            elemento.appendChild(elemFinal2);
            elemFinal2.appendChild(text2);

            Element elemFinal3 = document.createElement("pib");
            Text text3 = document.createTextNode(String.valueOf(PIB[i]));
            elemento.appendChild(elemFinal3);
            elemFinal3.appendChild(text3);

            Element elemFinal4 = document.createElement("coeficientegini");
            Text text4 = document.createTextNode(String.valueOf(coeficienteGini[i]));
            elemento.appendChild(elemFinal4);
            elemFinal4.appendChild(text4);

        }

        // una vez hemos generado la estructura, crear la fuente XML a partir del
        // documento
        Source source = new DOMSource(document);
        // crear el resultado en el fichero xml
        Result result = new StreamResult(new File("paises.xml"));
        // obtener un TransformerFactory
        Transformer transformer = TransformerFactory.newInstance().newTransformer();

        // le damos formato y realizamos la transformación del documento fichero
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        transformer.transform(source, result);
        // a modo de comprobacion podemos mostrar el documento por pantalla
        // especificando como canal de salida la consola
        Result console = new StreamResult(System.out);
        transformer.transform(source, console);
        // ESCRIBIR MEDIANTE DOM EN .XML
        // -----------------------------------------------
        // ALMACENAR EN UN FICHERO BINARIO
        XStream xs = new XStream();

        try {


            xs.addPermission(com.thoughtworks.xstream.security.NoTypePermission.NONE);
            xs.addPermission(com.thoughtworks.xstream.security.NullPermission.NULL);
            xs.addPermission(com.thoughtworks.xstream.security.PrimitiveTypePermission.PRIMITIVES);


            xs.allowTypes(new Class[]{ListaPaises.class, Pais.class});
            xs.alias("Listapaises", ListaPaises.class);
            xs.addImplicitCollection(ListaPaises.class, "lista", Pais.class);
            xs.alias("pais", ListaPaises.class);


            FileReader fr = new FileReader("paises.xml");
            ListaPaises listaPaises = (ListaPaises) xs.fromXML(fr);


            List<Pais> ps = listaPaises.getListaPaises();


            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("paises.dat"));
            for (Pais  pais1 : ps) {
                oos.writeObject(pais1);
            }
            oos.close();


            for (Pais pais1 : ps) {
                System.out.println("Nombre: " + pais1.getNombre());
                System.out.println("Presidente: " + pais1.getNombre());
                System.out.println("PIB: " + pais1.getPib());
                System.out.println("Gini: " + pais1.getCoeficientegini());
                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}