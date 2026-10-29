package gt.edu.umg.ecotrackapi.servlet;

import com.google.gson.Gson;
import gt.edu.umg.ecotrackapi.dao.RecoleccionDAO;
import gt.edu.umg.ecotrackapi.model.Recoleccion;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "RecoleccionesServlet", urlPatterns = {"/api/recolecciones"})
public class RecoleccionesServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");

        Gson gson = new Gson();
        PrintWriter out = response.getWriter();

        try {
            // 1. Leer el JSON que viene desde el celular (o Postman)
            BufferedReader reader = request.getReader();
            Recoleccion nuevaRec = gson.fromJson(reader, Recoleccion.class);

            // 2. Validar datos mínimos
            if (nuevaRec == null || nuevaRec.getPuntoId() <= 0 || nuevaRec.getPesoKg() <= 0) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print("{\"error\": \"Datos inválidos. Revisa el punto_id y el peso.\"}");
                return;
            }

            // 3. Guardar en Base de Datos
            RecoleccionDAO dao = new RecoleccionDAO();
            boolean exito = dao.guardar(nuevaRec);

            if (exito) {
                response.setStatus(HttpServletResponse.SC_CREATED); // 201 Created
                out.print("{\"mensaje\": \"Recolección guardada exitosamente\"}");
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                out.print("{\"error\": \"No se pudo guardar en la base de datos\"}");
            }

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"error\": \"Error procesando el JSON: " + e.getMessage() + "\"}");
        } finally {
            out.flush();
        }
    }
}