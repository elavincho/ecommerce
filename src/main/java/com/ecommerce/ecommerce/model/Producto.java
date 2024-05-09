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
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String nombre;
    private String descripcion;
    private String imagen;
    private String imagen2;
    private String imagen3;
    private int precio;
    private int cantidad;
    private String categoria;
    private String ubicacion;
    private String dato1;
    private String datoTecnico1;
    private String dato2;
    private String datoTecnico2;
    private String dato3;
    private String datoTecnico3;
    private String dato4;
    private String datoTecnico4;
    private String dato5;
    private String datoTecnico5;
    private String dato6;
    private String datoTecnico6;
    private String dato7;
    private String datoTecnico7;
    private String dato8;
    private String datoTecnico8;
    private String dato9;
    private String datoTecnico9;

    @ManyToOne
    private Usuario usuario;

    public Producto() {
    }

    public Producto(Integer Id, String nombre, String descripcion, String imagen, String imagen2, String imagen3, int precio, int cantidad, String categoria, String ubicacion, String dato1, String datoTecnico1, String dato2, String datoTecnico2, String dato3, String datoTecnico3, String dato4, String datoTecnico4, String dato5, String datoTecnico5, String dato6, String datoTecnico6, String dato7, String datoTecnico7, String dato8, String datoTecnico8, String dato9, String datoTecnico9) {
        this.Id = Id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.imagen2 = imagen2;
        this.imagen3 = imagen3;
        this.precio = precio;
        this.cantidad = cantidad;
        this.categoria = categoria;
        this.ubicacion = ubicacion;
        this.dato1 = dato1;
        this.datoTecnico1 = datoTecnico1;
        this.dato2 = dato2;
        this.datoTecnico2 = datoTecnico2;
        this.dato3 = dato3;
        this.datoTecnico3 = datoTecnico3;
        this.dato4 = dato4;
        this.datoTecnico4 = datoTecnico4;
        this.dato5 = dato5;
        this.datoTecnico5 = datoTecnico5;
        this.dato6 = dato6;
        this.datoTecnico6 = datoTecnico6;
        this.dato7 = dato7;
        this.datoTecnico7 = datoTecnico7;
        this.dato8 = dato8;
        this.datoTecnico8 = datoTecnico8;
        this.dato9 = dato9;
        this.datoTecnico9 = datoTecnico9;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getImagen2() {
        return imagen2;
    }

    public void setImagen2(String imagen2) {
        this.imagen2 = imagen2;
    }

    public String getImagen3() {
        return imagen3;
    }

    public void setImagen3(String imagen3) {
        this.imagen3 = imagen3;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDato1() {
        return dato1;
    }

    public void setDato1(String dato1) {
        this.dato1 = dato1;
    }

    public String getDatoTecnico1() {
        return datoTecnico1;
    }

    public void setDatoTecnico1(String datoTecnico1) {
        this.datoTecnico1 = datoTecnico1;
    }

    public String getDato2() {
        return dato2;
    }

    public void setDato2(String dato2) {
        this.dato2 = dato2;
    }

    public String getDatoTecnico2() {
        return datoTecnico2;
    }

    public void setDatoTecnico2(String datoTecnico2) {
        this.datoTecnico2 = datoTecnico2;
    }

    public String getDato3() {
        return dato3;
    }

    public void setDato3(String dato3) {
        this.dato3 = dato3;
    }

    public String getDatoTecnico3() {
        return datoTecnico3;
    }

    public void setDatoTecnico3(String datoTecnico3) {
        this.datoTecnico3 = datoTecnico3;
    }

    public String getDato4() {
        return dato4;
    }

    public void setDato4(String dato4) {
        this.dato4 = dato4;
    }

    public String getDatoTecnico4() {
        return datoTecnico4;
    }

    public void setDatoTecnico4(String datoTecnico4) {
        this.datoTecnico4 = datoTecnico4;
    }

    public String getDato5() {
        return dato5;
    }

    public void setDato5(String dato5) {
        this.dato5 = dato5;
    }

    public String getDatoTecnico5() {
        return datoTecnico5;
    }

    public void setDatoTecnico5(String datoTecnico5) {
        this.datoTecnico5 = datoTecnico5;
    }

    public String getDato6() {
        return dato6;
    }

    public void setDato6(String dato6) {
        this.dato6 = dato6;
    }

    public String getDatoTecnico6() {
        return datoTecnico6;
    }

    public void setDatoTecnico6(String datoTecnico6) {
        this.datoTecnico6 = datoTecnico6;
    }

    public String getDato7() {
        return dato7;
    }

    public void setDato7(String dato7) {
        this.dato7 = dato7;
    }

    public String getDatoTecnico7() {
        return datoTecnico7;
    }

    public void setDatoTecnico7(String datoTecnico7) {
        this.datoTecnico7 = datoTecnico7;
    }

    public String getDato8() {
        return dato8;
    }

    public void setDato8(String dato8) {
        this.dato8 = dato8;
    }

    public String getDatoTecnico8() {
        return datoTecnico8;
    }

    public void setDatoTecnico8(String datoTecnico8) {
        this.datoTecnico8 = datoTecnico8;
    }

    public String getDato9() {
        return dato9;
    }

    public void setDato9(String dato9) {
        this.dato9 = dato9;
    }

    public String getDatoTecnico9() {
        return datoTecnico9;
    }

    public void setDatoTecnico9(String datoTecnico9) {
        this.datoTecnico9 = datoTecnico9;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Producto{" + "Id=" + Id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", imagen=" + imagen + ", imagen2=" + imagen2 + ", imagen3=" + imagen3 + ", precio=" + precio + ", cantidad=" + cantidad + ", categoria=" + categoria + ", ubicacion=" + ubicacion + ", dato1=" + dato1 + ", datoTecnico1=" + datoTecnico1 + ", dato2=" + dato2 + ", datoTecnico2=" + datoTecnico2 + ", dato3=" + dato3 + ", datoTecnico3=" + datoTecnico3 + ", dato4=" + dato4 + ", datoTecnico4=" + datoTecnico4 + ", dato5=" + dato5 + ", datoTecnico5=" + datoTecnico5 + ", dato6=" + dato6 + ", datoTecnico6=" + datoTecnico6 + ", dato7=" + dato7 + ", datoTecnico7=" + datoTecnico7 + ", dato8=" + dato8 + ", datoTecnico8=" + datoTecnico8 + ", dato9=" + dato9 + ", datoTecnico9=" + datoTecnico9 + '}';
    }

}
