package com.depas.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.depas.demo.models.PropiedadModel;

@Repository
public interface IPropiedadRepository extends JpaRepository<PropiedadModel, Long> {
    //aqui no lleva codigo
    
}
