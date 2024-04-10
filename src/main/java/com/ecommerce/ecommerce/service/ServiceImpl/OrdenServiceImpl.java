/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.ecommerce.service.ServiceImpl;

import com.ecommerce.ecommerce.model.Orden;
import com.ecommerce.ecommerce.model.Usuario;
import com.ecommerce.ecommerce.rerpository.OrdenRepository;
import com.ecommerce.ecommerce.service.OrdenService;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author elavincho
 */
@Service
public class OrdenServiceImpl implements OrdenService {

    @Autowired
    private OrdenRepository ordenRepository;

    @Override
    public Orden save(Orden orden) {

        return ordenRepository.save(orden);
    }

    @Override
    public List<Orden> findAll() {

        return ordenRepository.findAll();
    }

    @Override
    public String generarNumeroOrden() {

        int numero = 0;
        String numeroConcatenado = "";

        List<Orden> ordenes = findAll();
        List<Integer> numeros = new ArrayList<Integer>();

        ordenes.stream().forEach(o -> numeros.add(Integer.parseInt(o.getNumero())));

        if (ordenes.isEmpty()) {
            numero = 1;
        } else {
            numero = numeros.stream().max(Integer::compare).get();
            numero++;
        }

        if (numero < 10) {
            numeroConcatenado = "000000000" + String.valueOf(numero);
        } else if (numero < 100) {
            numeroConcatenado = "00000000" + String.valueOf(numero);
        } else if (numero < 1000) {
            numeroConcatenado = "0000000" + String.valueOf(numero);
        } else if (numero < 10000) {
            numeroConcatenado = "000000" + String.valueOf(numero);
        } else if (numero < 100000) {
            numeroConcatenado = "00000" + String.valueOf(numero);
        } else if (numero < 1000000) {
            numeroConcatenado = "0000" + String.valueOf(numero);
        } else if (numero < 10000000) {
            numeroConcatenado = "000" + String.valueOf(numero);
        }

        return numeroConcatenado;
    }

    @Override
    public List<Orden> findByUsuario(Usuario usuario) {

        return ordenRepository.findByUsuario(usuario);
    }

    @Override
    public Optional<Orden> findById(Integer id) {

        return ordenRepository.findById(id);
    }

}
