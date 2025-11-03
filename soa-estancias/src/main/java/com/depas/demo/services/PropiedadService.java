package com.depas.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.depas.demo.models.PropiedadModel;
import com.depas.demo.repositories.IPropiedadRepository;

@Service
public class PropiedadService {

    @Autowired
    private IPropiedadRepository propiedadRepository;

    public List<PropiedadModel> getAllPropiedades() {
        return propiedadRepository.findAll();
    }

    public Optional<PropiedadModel> getPropiedadById(Long id) {
        return propiedadRepository.findById(id);
    }

    public PropiedadModel savePropiedad(PropiedadModel propiedad) {
        return propiedadRepository.save(propiedad);
    } 
    
    public void deletePropiedad(Long id) {
        propiedadRepository.deleteById(id);
    }

    // ACTIVIDAD: Crea un metodo Recuperar propiedades por tipo
}
