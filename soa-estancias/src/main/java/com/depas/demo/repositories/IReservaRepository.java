package com.depas.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.depas.demo.models.ReservaModel;

import java.util.List;

@Repository
public interface IReservaRepository extends JpaRepository<ReservaModel, Long> {
    // Métodos de búsqueda personalizados
    @Query("SELECT r FROM ReservaModel r WHERE r.cliente.id = :clienteId")
    List<ReservaModel> findByClienteId(@Param("clienteId") Long clienteId);

    @Query("SELECT r FROM ReservaModel r WHERE r.propiedad.id_propiedad = :propiedadId")
    List<ReservaModel> findByPropiedadId_propiedad(@Param("propiedadId") Long propiedadId);

    @Query("SELECT r FROM ReservaModel r WHERE r.estado_reservacion = :estado")
    List<ReservaModel> findByEstado_reservacion(@Param("estado") ReservaModel.EstadoReservacion estado);
}
