package com.depas.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.depas.demo.models.ReservaModel;
import com.depas.demo.repositories.IReservaRepository;

@Service
public class ReservaService {

    @Autowired
    private IReservaRepository reservaRepository;

    public List<ReservaModel> getAllReservas() {
        return reservaRepository.findAll();
    }

    public Optional<ReservaModel> getReservaById(Long id) {
        return reservaRepository.findById(id);
    }

    public ReservaModel saveReserva(ReservaModel reserva) {
        return reservaRepository.save(reserva);
    }

    public void deleteReserva(Long id) {
        reservaRepository.deleteById(id);
    }

    // Métodos adicionales
    public List<ReservaModel> getReservasByCliente(Long clienteId) {
        return reservaRepository.findByClienteId(clienteId);
    }

    public List<ReservaModel> getReservasByPropiedad(Long propiedadId) {
        return reservaRepository.findByPropiedadId_propiedad(propiedadId);
    }

    public List<ReservaModel> getReservasByEstado(ReservaModel.EstadoReservacion estado) {
        return reservaRepository.findByEstado_reservacion(estado);
    }
}
