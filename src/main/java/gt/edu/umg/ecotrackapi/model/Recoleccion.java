package gt.edu.umg.ecotrackapi.model;

public class Recoleccion {
    private int id;
    private int puntoId;
    private String tipoMaterial;
    private double pesoKg;
    private Double latitud;
    private Double longitud;
    private String recolector;
    private String origen;

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getPuntoId() { return puntoId; }
    public void setPuntoId(int puntoId) { this.puntoId = puntoId; }

    public String getTipoMaterial() { return tipoMaterial; }
    public void setTipoMaterial(String tipoMaterial) { this.tipoMaterial = tipoMaterial; }

    public double getPesoKg() { return pesoKg; }
    public void setPesoKg(double pesoKg) { this.pesoKg = pesoKg; }

    public Double getLatitud() { return latitud; }
    public void setLatitud(Double latitud) { this.latitud = latitud; }

    public Double getLongitud() { return longitud; }
    public void setLongitud(Double longitud) { this.longitud = longitud; }

    public String getRecolector() { return recolector; }
    public void setRecolector(String recolector) { this.recolector = recolector; }

    public String getOrigen() { return origen; }
    public void setOrigen(String origen) { this.origen = origen; }
}