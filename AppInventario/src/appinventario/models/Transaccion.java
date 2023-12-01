/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appinventario.models;
import java.util.Date;
/**
 *
 * @author Juan
 */
import java.util.Date;

public class Transaccion {
    private int id;
    private int idSuministro;
    private String nombreSuministro; // Nombre del suministro
    private int cantidad;
    private Date fecha;
    private TipoTransaccion tipo; // Enum para tipo de transacción: ENTRADA, SALIDA

    public Transaccion(int id, int idSuministro, String nombreSuministro, int cantidad, Date fecha, TipoTransaccion tipo) {
        this.id = id;
        this.idSuministro = idSuministro;
        this.nombreSuministro = nombreSuministro;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.tipo = tipo;
    }

    // Getters y Setters

    public int getId() {
        return id;
    }

    public int getIdSuministro() {
        return idSuministro;
    }

    public String getNombreSuministro() {
        return nombreSuministro;
    }

    public int getCantidad() {
        return cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public TipoTransaccion getTipo() {
        return tipo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdSuministro(int idSuministro) {
        this.idSuministro = idSuministro;
    }

    public void setNombreSuministro(String nombreSuministro) {
        this.nombreSuministro = nombreSuministro;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setTipo(TipoTransaccion tipo) {
        this.tipo = tipo;
    }
    
   
    // Método toString para mostrar la información de la transacción
    @Override
    public String toString() {
        return "Transaccion{" +
                "id=" + id +
                ", idSuministro=" + idSuministro +
                ", nombreSuministro='" + nombreSuministro + '\'' +
                ", cantidad=" + cantidad +
                ", fecha=" + fecha +
                ", tipo=" + tipo +
                '}';
    }
}

enum TipoTransaccion {
    ENTRADA, SALIDA
}
