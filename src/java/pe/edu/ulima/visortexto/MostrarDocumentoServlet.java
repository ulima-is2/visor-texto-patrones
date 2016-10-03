package pe.edu.ulima.visortexto;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MostrarDocumentoServlet", urlPatterns = {"/mostrar"})
public class MostrarDocumentoServlet extends HttpServlet {

    @Override
    protected void doPost(
            HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException {

        String titulo = req.getParameter("titulo");
        String contenido = req.getParameter("contenido");
        String tipo = req.getParameter("tipo");

        if (tipo.equals("pdf")) {
            resp.setContentType("application/pdf");
            
            ModoVisualizacionAdapter adapter = new PDFAdapter();            
            ByteArrayOutputStream baos = 
                    adapter.renderizar(titulo, contenido);
            baos.writeTo(resp.getOutputStream());
            
            resp.getOutputStream().flush();
        } else if (tipo.equals("html")) {
            ModoVisualizacionAdapter adapter = new HTMLAdapter();
            ByteArrayOutputStream baos = 
                    adapter.renderizar(titulo, contenido);
            baos.writeTo(resp.getOutputStream());
            resp.getOutputStream().flush();
        }

    }

    

}
