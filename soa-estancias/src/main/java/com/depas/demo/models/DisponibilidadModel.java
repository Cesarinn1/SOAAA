package com.depas.demo.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "disponibilidades")
public class DisponibilidadModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_disponibilidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_propiedad", nullable = true)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private PropiedadModel propiedad;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDateTime fecha_inicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDateTime fecha_fin;

    @Column(name = "disponible", nullable = false)
    private Boolean disponible;

    @Column(name = "precio_especial")
    private Double precio_especial;

    // Constructores
    public DisponibilidadModel() {
    }

    public DisponibilidadModel(PropiedadModel propiedad, LocalDateTime fecha_inicio, LocalDateTime fecha_fin,
                               Boolean disponible, Double precio_especial) {
        this.propiedad = propiedad;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.disponible = disponible;
        this.precio_especial = precio_especial;
    }

    public DisponibilidadModel(Long id_disponibilidad, PropiedadModel propiedad, LocalDateTime fecha_inicio,
                               LocalDateTime fecha_fin, Boolean disponible, Double precio_especial) {
        this.id_disponibilidad = id_disponibilidad;
        this.propiedad = propiedad;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.disponible = disponible;
        this.precio_especial = precio_especial;
    }

    public Long getId_disponibilidad() {
        return id_disponibilidad;
    }

    public void setId_disponibilidad(Long id_disponibilidad) {
        this.id_disponibilidad = id_disponibilidad;
    }

    public PropiedadModel getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(PropiedadModel propiedad) {
        this.propiedad = propiedad;
    }

    public LocalDateTime getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(LocalDateTime fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public LocalDateTime getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(LocalDateTime fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public Double getPrecio_especial() {
        return precio_especial;
    }

    public void setPrecio_especial(Double precio_especial) {
        this.precio_especial = precio_especial;
    }
}
