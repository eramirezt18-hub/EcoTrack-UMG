package gt.edu.umg.ecotrackapi.dao;

import gt.edu.umg.ecotrackapi.config.Conexion;
import gt.edu.umg.ecotrackapi.model.Recoleccion;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class RecoleccionDAO {

    public boolean guardar(Recoleccion rec) {
        String sql = "INSERT INTO recolecciones (punto_id, tipo_material, peso_kg, latitud, longitud, recolector, origen) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, rec.getPuntoId());
            ps.setString(2, rec.getTipoMaterial());
            ps.setDouble(3, rec.getPesoKg());
            
            // Si las coordenadas vienen nulas, se guarda NULL en SQL
            if (rec.getLatitud() != null) {
                ps.setDouble(4, rec.getLatitud());
            } else {
                ps.setNull(4, java.sql.Types.DECIMAL);
            }
            
            if (rec.getLongitud() != null) {
                ps.setDouble(5, rec.getLongitud());
            } else {
                ps.setNull(5, java.sql.Types.DECIMAL);
            }
            
            ps.setString(6, rec.getRecolector());
            // Si el origen viene nulo desde Android, le ponemos 'movil' por defecto
            ps.setString(7, rec.getOrigen() != null ? rec.getOrigen() : "movil");
            
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0; // Retorna true si se guardó correctamente
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}