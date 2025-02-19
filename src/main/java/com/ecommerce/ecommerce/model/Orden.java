package com.ecommerce.ecommerce.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

/**
 *
 * @author elavincho
 */
@Entity
@Table(name = "ordenes")
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String numero;
    private Date fechaCreacion;
    private String fechaCreacionFormateada;
    private Date fechaRecibida;
    private String fechaRecibidaFormateada;
    private String recibidoPor;
    private String dni;
    private String entregadoPor;

    private double total;
    private String totalFormateada;

    @ManyToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "orden")
    private List<DetalleOrden> detalle;

    public Orden() {
    }

    public Orden(Integer Id, String numero, Date fechaCreacion, String fechaCreacionFormateada, Date fechaRecibida, String fechaRecibidaFormateada, String recibidoPor, String dni, String entregadoPor, double total, String totalFormateada, Usuario usuario, List<DetalleOrden> detalle) {
        this.Id = Id;
        this.numero = numero;
        this.fechaCreacion = fechaCreacion;
        this.fechaCreacionFormateada = fechaCreacionFormateada;
        this.fechaRecibida = fechaRecibida;
        this.fechaRecibidaFormateada = fechaRecibidaFormateada;
        this.recibidoPor = recibidoPor;
        this.dni = dni;
        this.entregadoPor = entregadoPor;
        this.total = total;
        this.totalFormateada = totalFormateada;
        this.usuario = usuario;
        this.detalle = detalle;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaCreacionFormateada() {
        return fechaCreacionFormateada;
    }

    public void setFechaCreacionFormateada(String fechaCreacionFormateada) {
        this.fechaCreacionFormateada = fechaCreacionFormateada;
    }

    public Date getFechaRecibida() {
        return fechaRecibida;
    }

    public void setFechaRecibida(Date fechaRecibida) {
        this.fechaRecibida = fechaRecibida;
    }

    public String getFechaRecibidaFormateada() {
        return fechaRecibidaFormateada;
    }

    public void setFechaRecibidaFormateada(String fechaRecibidaFormateada) {
        this.fechaRecibidaFormateada = fechaRecibidaFormateada;
    }

    public String getRecibidoPor() {
        return recibidoPor;
    }

    public void setRecibidoPor(String recibidoPor) {
        this.recibidoPor = recibidoPor;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEntregadoPor() {
        return entregadoPor;
    }

    public void setEntregadoPor(String entregadoPor) {
        this.entregadoPor = entregadoPor;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getTotalFormateada() {
        return totalFormateada;
    }

    public void setTotalFormateada(String totalFormateada) {
        this.totalFormateada = totalFormateada;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<DetalleOrden> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<DetalleOrden> detalle) {
        this.detalle = detalle;
    }

    @Override
    public String toString() {
        return "Orden{" + "Id=" + Id + ", numero=" + numero + ", fechaCreacion=" + fechaCreacion + ", fechaCreacionFormateada=" + fechaCreacionFormateada + ", fechaRecibida=" + fechaRecibida + ", fechaRecibidaFormateada=" + fechaRecibidaFormateada + ", recibidoPor=" + recibidoPor + ", dni=" + dni + ", entregadoPor=" + entregadoPor + ", total=" + total + ", totalFormateada=" + totalFormateada + ", detalle=" + detalle + '}';
    }

}
