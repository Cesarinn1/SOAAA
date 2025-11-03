package com.depas.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.depas.demo.models.PagosModel;

import java.util.List;

@Repository
public interface IPagosRepository extends JpaRepository<PagosModel, Long> {
    // Métodos de búsqueda personalizados
    @Query("SELECT p FROM PagosModel p WHERE p.reserva.id_reservacion = :reservaId")
    List<PagosModel> findByReservaId_reservacion(@Param("reservaId") Long reservaId);

    @Query("SELECT p FROM PagosModel p WHERE p.estado_pago = :estado")
    List<PagosModel> findByEstado_pago(@Param("estado") PagosModel.EstadoPago estado);

    @Query("SELECT p FROM PagosModel p WHERE p.metodo_pago = :metodo")
    List<PagosModel> findByMetodo_pago(@Param("metodo") PagosModel.MetodoPago metodo);
}
