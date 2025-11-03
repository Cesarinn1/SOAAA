package com.depas.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.depas.demo.models.PagosModel;
import com.depas.demo.repositories.IPagosRepository;

@Service
public class PagosService {

    @Autowired
    private IPagosRepository pagosRepository;

    public List<PagosModel> getAllPagos() {
        return pagosRepository.findAll();
    }

    public Optional<PagosModel> getPagoById(Long id) {
        return pagosRepository.findById(id);
    }

    public PagosModel savePago(PagosModel pago) {
        return pagosRepository.save(pago);
    }

    public void deletePago(Long id) {
        pagosRepository.deleteById(id);
    }

    // Métodos adicionales
    public List<PagosModel> getPagosByReserva(Long reservaId) {
        return pagosRepository.findByReservaId_reservacion(reservaId);
    }

    public List<PagosModel> getPagosByEstado(PagosModel.EstadoPago estado) {
        return pagosRepository.findByEstado_pago(estado);
    }

    public List<PagosModel> getPagosByMetodo(PagosModel.MetodoPago metodo) {
        return pagosRepository.findByMetodo_pago(metodo);
    }
}
