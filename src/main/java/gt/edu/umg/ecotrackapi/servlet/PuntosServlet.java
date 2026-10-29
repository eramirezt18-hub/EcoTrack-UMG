package gt.edu.umg.ecotrackapi.servlet;

import com.google.gson.Gson;
import gt.edu.umg.ecotrackapi.dao.PuntoDAO;
import gt.edu.umg.ecotrackapi.model.Punto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "PuntosServlet", urlPatterns = {"/api/puntos"})
public class PuntosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Configurar la respuesta como JSON y permitir CORS (útil para pruebas locales)
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");

        PuntoDAO dao = new PuntoDAO();
        List<Punto> puntos = dao.listarActivos();

        // Convertir la lista de Java a JSON usando Gson
        Gson gson = new Gson();
        String json = gson.toJson(puntos);

        // Enviar el JSON al cliente (navegador o app Android)
        try (PrintWriter out = response.getWriter()) {
            out.print(json);
            out.flush();
        }
    }
}