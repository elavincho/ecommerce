/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.ecommerce.model;

import jakarta.persistence.*;
import java.util.List;

/**
 *
 * @author elavincho
 */
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String nombre;
    private String apellido;
    private String documento;
    private String username;
    private String email;
    private String provincia;
    private String localidad;
    private String direccion;
    private String altura;
    private String piso;
    private String depto;
    private String telefono;
    private String tipo;
    private String password;
    private String foto;

    @OneToMany(mappedBy = "usuario")
    private List<Producto> productos;

    @OneToMany(mappedBy = "usuario")
    private List<Orden> ordenes;

    public Usuario() {
    }

    public Usuario(Integer Id, String nombre, String apellido, String documento, String username, String email, String provincia, String localidad, String direccion, String altura, String piso, String depto, String telefono, String tipo, String password, String foto) {
        this.Id = Id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.username = username;
        this.email = email;
        this.provincia = provincia;
        this.localidad = localidad;
        this.direccion = direccion;
        this.altura = altura;
        this.piso = piso;
        this.depto = depto;
        this.telefono = telefono;
        this.tipo = tipo;
        this.password = password;
        this.foto = foto;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public List<Orden> getOrdenes() {
        return ordenes;
    }

    public void setOrdenes(List<Orden> ordenes) {
        this.ordenes = ordenes;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getDepto() {
        return depto;
    }

    public void setDepto(String depto) {
        this.depto = depto;
    }

    @Override
    public String toString() {
        return "Usuario{" + "Id=" + Id + ", nombre=" + nombre + ", apellido=" + apellido + ", documento=" + documento + ", username=" + username + ", email=" + email + ", provincia=" + provincia + ", localidad=" + localidad + ", direccion=" + direccion + ", altura=" + altura + ", piso=" + piso + ", depto=" + depto + ", telefono=" + telefono + ", tipo=" + tipo + ", password=" + password + ", foto=" + foto + '}';
    }
}
