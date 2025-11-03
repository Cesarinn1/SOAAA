package com.depas.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.depas.demo.models.ReseñaModel;

import java.util.List;

@Repository
public interface IReseñaRepository extends JpaRepository<ReseñaModel, Long> {
    // Métodos de búsqueda personalizados
    @Query("SELECT r FROM ReseñaModel r WHERE r.propiedad.id_propiedad = :propiedadId")
    List<ReseñaModel> findByPropiedadId_propiedad(@Param("propiedadId") Long propiedadId);

    @Query("SELECT r FROM ReseñaModel r WHERE r.cliente.id = :clienteId")
    List<ReseñaModel> findByClienteId(@Param("clienteId") Long clienteId);

    List<ReseñaModel> findByCalificacion(Integer calificacion);
}
