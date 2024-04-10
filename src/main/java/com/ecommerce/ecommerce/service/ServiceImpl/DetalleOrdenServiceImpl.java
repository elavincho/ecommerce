/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.ecommerce.service.ServiceImpl;

import com.ecommerce.ecommerce.model.DetalleOrden;
import com.ecommerce.ecommerce.rerpository.DetalleOrdenRepository;
import com.ecommerce.ecommerce.service.DetalleOrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author elavincho
 */

@Service
public class DetalleOrdenServiceImpl implements DetalleOrdenService {

    @Autowired
    private DetalleOrdenRepository detalleOrdenRepository;
    
    @Override
    public DetalleOrden save(DetalleOrden detalleOrden) {
    
        return detalleOrdenRepository.save(detalleOrden);
    }
    
}
