/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.ecommerce.service.ServiceImpl;

import com.ecommerce.ecommerce.model.Empresa;
import com.ecommerce.ecommerce.rerpository.EmpresaRepository;
import com.ecommerce.ecommerce.service.EmpresaService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author elavincho
 */
@Service
public class EmpresaServiceImpl implements EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Override
    public Empresa save(Empresa empresa) {

        return empresaRepository.save(empresa);
    }

    @Override
    public void update(Empresa empresa) {

        empresaRepository.save(empresa);
    }

    @Override
    public List<Empresa> findAll() {
        return empresaRepository.findAll();
    }

    @Override
    public Optional<Empresa> findById(Integer id) {
       
        return empresaRepository.findById(id);
    }

}
