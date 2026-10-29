package gt.edu.umg.ecotrackapi.dao;

import gt.edu.umg.ecotrackapi.config.Conexion;
import gt.edu.umg.ecotrackapi.model.Punto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PuntoDAO {

    // Método para obtener todos los puntos activos
    public List<Punto> listarActivos() {
        List<Punto> lista = new ArrayList<>();
        String sql = "SELECT id, nombre, ubicacion, latitud, longitud, responsable, activo FROM puntos_reciclaje WHERE activo = 1";

        // try-with-resources cierra automáticamente la conexión, el statement y el resultset
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Punto p = new Punto();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setUbicacion(rs.getString("ubicacion"));
                
                // Manejo de posibles nulos en las coordenadas
                double lat = rs.getDouble("latitud");
                p.setLatitud(rs.wasNull() ? null : lat);
                
                double lon = rs.getDouble("longitud");
                p.setLongitud(rs.wasNull() ? null : lon);
                
                p.setResponsable(rs.getString("responsable"));
                p.setActivo(rs.getBoolean("activo"));
                
                lista.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}