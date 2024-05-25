/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.ecommerce.model;

import jakarta.persistence.*;

/**
 *
 * @author elavincho
 */
@Entity
@Table(name = "detalles")
public class DetalleOrden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String nombre;
    private int cantidad;
    private double precio;
    private String precioFormateado;
    private double total;
    private String totalFormateado;

    @ManyToOne
    private Orden orden;

    @ManyToOne
    private Producto producto;

    public DetalleOrden() {
    }

    public DetalleOrden(Integer Id, String nombre, int cantidad, double precio, String precioFormateado, double total, String totalFormateado, Orden orden, Producto producto) {
        this.Id = Id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.precioFormateado = precioFormateado;
        this.total = total;
        this.totalFormateado = totalFormateado;
        this.orden = orden;
        this.producto = producto;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getPrecioFormateado() {
        return precioFormateado;
    }

    public void setPrecioFormateado(String precioFormateado) {
        this.precioFormateado = precioFormateado;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getTotalFormateado() {
        return totalFormateado;
    }

    public void setTotalFormateado(String totalFormateado) {
        this.totalFormateado = totalFormateado;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        return "DetalleOrden{" + "Id=" + Id + ", nombre=" + nombre + ", cantidad=" + cantidad + ", precio=" + precio + ", precioFormateado=" + precioFormateado + ", total=" + total + ", totalFormateado=" + totalFormateado + ", orden=" + orden + ", producto=" + producto + '}';
    }

}
