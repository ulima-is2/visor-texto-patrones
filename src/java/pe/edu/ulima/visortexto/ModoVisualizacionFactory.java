
package pe.edu.ulima.visortexto;

public class ModoVisualizacionFactory {
    public ModoVisualizacionAdapter obtenerAdapter(String tipo){
        if (tipo.equals("pdf")){
            return new PDFAdapter();
        }else if (tipo.equals("html")){
            return new HTMLAdapter();
        }else{
            return null;
        }
    }
}
