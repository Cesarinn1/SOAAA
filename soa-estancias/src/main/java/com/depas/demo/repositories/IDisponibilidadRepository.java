package com.depas.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.depas.demo.models.DisponibilidadModel;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IDisponibilidadRepository extends JpaRepository<DisponibilidadModel, Long> {
    // Métodos de búsqueda personalizados
    @Query("SELECT d FROM DisponibilidadModel d WHERE d.propiedad.id_propiedad = :propiedadId")
    List<DisponibilidadModel> findByPropiedadId_propiedad(@Param("propiedadId") Long propiedadId);

    List<DisponibilidadModel> findByDisponible(Boolean disponible);

    @Query("SELECT d FROM DisponibilidadModel d WHERE d.propiedad.id_propiedad = :propiedadId AND d.disponible = :disponible")
    List<DisponibilidadModel> findByPropiedadId_propiedadAndDisponible(@Param("propiedadId") Long propiedadId, @Param("disponible") Boolean disponible);

    @Query("SELECT d FROM DisponibilidadModel d WHERE d.fecha_inicio BETWEEN :inicio AND :fin")
    List<DisponibilidadModel> findByFecha_inicioBetween(@Param("inicio") LocalDateTime inicio, @Param("fin") LocalDateTime fin);
}
