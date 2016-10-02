package pe.edu.ulima.visortexto;

import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
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

            Document document = new Document();
            try {
                PdfWriter.getInstance(document, new FileOutputStream("doc.pdf"));
                Chunk chunk = new Chunk(titulo);
                Chapter chapter = new Chapter(new Paragraph(chunk), 1);
                chapter.setNumberDepth(0);
                chapter.add(new Paragraph(contenido));
                document.open();
                document.add(chapter);
                document.close();
            } catch (DocumentException ex) {
                Logger.getLogger(MostrarDocumentoServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            resp.setContentType("application/pdf");
            ByteArrayOutputStream baos = getByteArrayOutputStream("doc.pdf");

            baos.writeTo(resp.getOutputStream());
            resp.getOutputStream().flush();
        } else if (tipo.equals("html")) {
            PrintWriter out = resp.getWriter();
            out.print("<html>");
            out.print("<head>");
            out.print("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\"/>");
            out.print("</head>");
            out.print("<body class='container'>");
            out.print("<h1>" + titulo + "</h1>");
            out.print("<div class=\"panel panel-default\">");
            out.print("<div class=\"panel-body\">" + contenido + "</div>");
            out.print("</div?");
            out.print("</body>");
            out.print("</html>");
        }

    }

    private ByteArrayOutputStream getByteArrayOutputStream(String ruta) throws IOException {

        File file = new File(ruta);

        FileInputStream fis = new FileInputStream(file);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[256];
        try {
            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum); //no doubt here is 0
                //Writes len bytes from the specified byte array starting at offset off to this byte array output stream.
                System.out.println("read " + readNum + " bytes,");
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return bos;
    }

}
