/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.ecommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author elavincho
 */
@Entity
@Table(name = "empresa")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String razonSocial;
    private String direccion;
    private String localidad;
    private String provincia;
    private String pais;
    private String telefono;
    private String email;
    private String logo;

    private String condicionIVA;
    private String cuit;
    private String ingresosBrutos;
    private String inicioActividades;

    public Empresa() {
    }

    public Empresa(Integer Id, String razonSocial, String direccion, String localidad, String provincia, String pais, String telefono, String email, String logo, String condicionIVA, String cuit, String ingresosBrutos, String inicioActividades) {
        this.Id = Id;
        this.razonSocial = razonSocial;
        this.direccion = direccion;
        this.localidad = localidad;
        this.provincia = provincia;
        this.pais = pais;
        this.telefono = telefono;
        this.email = email;
        this.logo = logo;
        this.condicionIVA = condicionIVA;
        this.cuit = cuit;
        this.ingresosBrutos = ingresosBrutos;
        this.inicioActividades = inicioActividades;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCondicionIVA() {
        return condicionIVA;
    }

    public void setCondicionIVA(String condicionIVA) {
        this.condicionIVA = condicionIVA;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getIngresosBrutos() {
        return ingresosBrutos;
    }

    public void setIngresosBrutos(String ingresosBrutos) {
        this.ingresosBrutos = ingresosBrutos;
    }

    public String getInicioActividades() {
        return inicioActividades;
    }

    public void setInicioActividades(String inicioActividades) {
        this.inicioActividades = inicioActividades;
    }

    @Override
    public String toString() {
        return "Empresa{" + "Id=" + Id + ", razonSocial=" + razonSocial + ", direccion=" + direccion + ", localidad=" + localidad + ", provincia=" + provincia + ", pais=" + pais + ", telefono=" + telefono + ", email=" + email + ", logo=" + logo + ", condicionIVA=" + condicionIVA + ", cuit=" + cuit + ", ingresosBrutos=" + ingresosBrutos + ", inicioActividades=" + inicioActividades + '}';
    }

}
