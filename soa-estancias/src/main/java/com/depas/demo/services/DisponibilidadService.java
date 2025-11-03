package com.depas.demo.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.depas.demo.models.DisponibilidadModel;
import com.depas.demo.repositories.IDisponibilidadRepository;

@Service
public class DisponibilidadService {

    @Autowired
    private IDisponibilidadRepository disponibilidadRepository;

    public List<DisponibilidadModel> getAllDisponibilidades() {
        return disponibilidadRepository.findAll();
    }

    public Optional<DisponibilidadModel> getDisponibilidadById(Long id) {
        return disponibilidadRepository.findById(id);
    }

    public DisponibilidadModel saveDisponibilidad(DisponibilidadModel disponibilidad) {
        return disponibilidadRepository.save(disponibilidad);
    }

    public void deleteDisponibilidad(Long id) {
        disponibilidadRepository.deleteById(id);
    }

    // Métodos adicionales
    public List<DisponibilidadModel> getDisponibilidadesByPropiedad(Long propiedadId) {
        return disponibilidadRepository.findByPropiedadId_propiedad(propiedadId);
    }

    public List<DisponibilidadModel> getDisponibilidadesByEstado(Boolean disponible) {
        return disponibilidadRepository.findByDisponible(disponible);
    }

    public List<DisponibilidadModel> getDisponibilidadesByPropiedadAndEstado(Long propiedadId, Boolean disponible) {
        return disponibilidadRepository.findByPropiedadId_propiedadAndDisponible(propiedadId, disponible);
    }

    public List<DisponibilidadModel> getDisponibilidadesByFechas(LocalDateTime inicio, LocalDateTime fin) {
        return disponibilidadRepository.findByFecha_inicioBetween(inicio, fin);
    }
}
