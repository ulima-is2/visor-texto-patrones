
package pe.edu.ulima.visortexto;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class GestorRenderizado {
    private static GestorRenderizado singleton = null;
    
    public static GestorRenderizado getInstance(){
        if (singleton == null){
            singleton = new GestorRenderizado();
        }
        return singleton;
    }
    
    private GestorRenderizado(){}
    
    public ByteArrayOutputStream renderizar(
            String titulo, String contenido, 
            String tipo) throws IOException{
        
        ModoVisualizacionFactory factory = 
                ModoVisualizacionFactory.getInstance();
        ModoVisualizacionAdapter adapter = 
                factory.obtenerAdapter(tipo);
        ByteArrayOutputStream baos = 
                adapter.renderizar(titulo, contenido);
        return baos;
    }
}
