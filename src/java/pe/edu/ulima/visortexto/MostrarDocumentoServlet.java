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
        /*
        1. Recibe parametros del request
        2. Realiza alguna tarea con los parametros
        3. Devuelve una respuesta
        */

        String titulo = req.getParameter("titulo");
        String contenido = req.getParameter("contenido");
        String tipo = req.getParameter("tipo");
        
        GestorRenderizado gestor = new GestorRenderizado();
        ByteArrayOutputStream baos = 
                gestor.renderizar(titulo, contenido, tipo);
        
        baos.writeTo(resp.getOutputStream());
        resp.getOutputStream().flush();
    }
}
