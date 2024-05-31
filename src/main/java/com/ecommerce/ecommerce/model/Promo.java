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
@Table(name = "promos")
public class Promo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String promo;
    private String promo2;
    private String promo3;
    private String promo4;
    private String promo5;
    private String promo6;
    private String promo7;
    private String promo8;
    private String promo9;
    private String ubicacion;

    private String productoDestacado;
    private String productoDestacado2;
    private String productoDestacado3;

    public Promo() {
    }

    public Promo(Integer Id, String promo, String promo2, String promo3, String promo4, String promo5, String promo6, String promo7, String promo8, String promo9, String ubicacion, String productoDestacado, String productoDestacado2, String productoDestacado3) {
        this.Id = Id;
        this.promo = promo;
        this.promo2 = promo2;
        this.promo3 = promo3;
        this.promo4 = promo4;
        this.promo5 = promo5;
        this.promo6 = promo6;
        this.promo7 = promo7;
        this.promo8 = promo8;
        this.promo9 = promo9;
        this.ubicacion = ubicacion;
        this.productoDestacado = productoDestacado;
        this.productoDestacado2 = productoDestacado2;
        this.productoDestacado3 = productoDestacado3;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getPromo() {
        return promo;
    }

    public void setPromo(String promo) {
        this.promo = promo;
    }

    public String getPromo2() {
        return promo2;
    }

    public void setPromo2(String promo2) {
        this.promo2 = promo2;
    }

    public String getPromo3() {
        return promo3;
    }

    public void setPromo3(String promo3) {
        this.promo3 = promo3;
    }

    public String getPromo4() {
        return promo4;
    }

    public void setPromo4(String promo4) {
        this.promo4 = promo4;
    }

    public String getPromo5() {
        return promo5;
    }

    public void setPromo5(String promo5) {
        this.promo5 = promo5;
    }

    public String getPromo6() {
        return promo6;
    }

    public void setPromo6(String promo6) {
        this.promo6 = promo6;
    }

    public String getPromo7() {
        return promo7;
    }

    public void setPromo7(String promo7) {
        this.promo7 = promo7;
    }

    public String getPromo8() {
        return promo8;
    }

    public void setPromo8(String promo8) {
        this.promo8 = promo8;
    }

    public String getPromo9() {
        return promo9;
    }

    public void setPromo9(String promo9) {
        this.promo9 = promo9;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getProductoDestacado() {
        return productoDestacado;
    }

    public void setProductoDestacado(String productoDestacado) {
        this.productoDestacado = productoDestacado;
    }

    public String getProductoDestacado2() {
        return productoDestacado2;
    }

    public void setProductoDestacado2(String productoDestacado2) {
        this.productoDestacado2 = productoDestacado2;
    }

    public String getProductoDestacado3() {
        return productoDestacado3;
    }

    public void setProductoDestacado3(String productoDestacado3) {
        this.productoDestacado3 = productoDestacado3;
    }

}
