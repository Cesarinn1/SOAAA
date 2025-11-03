package com.depas.demo.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "reservaciones")
public class ReservaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_reservacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = true)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private ClienteModel cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_propiedad", nullable = true)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private PropiedadModel propiedad;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fecha_inicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fecha_fin;

    @Column(name = "numero_huespedes", nullable = false)
    private Integer numero_huespedes;

    @Column(name = "monto_total", nullable = false)
    private Double monto_total;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_reservacion", nullable = false)
    private EstadoReservacion estado_reservacion;

    @Column(name = "fecha_reservacion", nullable = false)
    private LocalDateTime fecha_reservacion;

    @Column(name = "fecha_cancelacion")
    private LocalDateTime fecha_cancelacion;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime created_at;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updated_at;

    public enum EstadoReservacion {
        PENDIENTE,
        CONFIRMADA,
        CANCELADA,
        COMPLETADA
    }

    // Callbacks JPA para manejo automático de timestamps
    @PrePersist
    public void onPrePersist() {
        if (this.created_at == null) {
            this.created_at = LocalDateTime.now();
        }
        if (this.fecha_reservacion == null) {
            this.fecha_reservacion = LocalDateTime.now();
        }
        this.updated_at = LocalDateTime.now();
    }

    @PreUpdate
    public void onPreUpdate() {
        this.updated_at = LocalDateTime.now();
    }

    // Constructores
    public ReservaModel() {
    }

    public ReservaModel(ClienteModel cliente, PropiedadModel propiedad, LocalDate fecha_inicio, LocalDate fecha_fin,
                        Integer numero_huespedes, Double monto_total, EstadoReservacion estado_reservacion) {
        this.cliente = cliente;
        this.propiedad = propiedad;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.numero_huespedes = numero_huespedes;
        this.monto_total = monto_total;
        this.estado_reservacion = estado_reservacion;
    }

    public ReservaModel(Long id_reservacion, ClienteModel cliente, PropiedadModel propiedad, LocalDate fecha_inicio,
                        LocalDate fecha_fin, Integer numero_huespedes, Double monto_total,
                        EstadoReservacion estado_reservacion, LocalDateTime fecha_reservacion,
                        LocalDateTime fecha_cancelacion, LocalDateTime created_at, LocalDateTime updated_at) {
        this.id_reservacion = id_reservacion;
        this.cliente = cliente;
        this.propiedad = propiedad;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.numero_huespedes = numero_huespedes;
        this.monto_total = monto_total;
        this.estado_reservacion = estado_reservacion;
        this.fecha_reservacion = fecha_reservacion;
        this.fecha_cancelacion = fecha_cancelacion;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    // Getters y Setters
    public Long getId_reservacion() {
        return id_reservacion;
    }

    public void setId_reservacion(Long id_reservacion) {
        this.id_reservacion = id_reservacion;
    }

    public ClienteModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }

    public PropiedadModel getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(PropiedadModel propiedad) {
        this.propiedad = propiedad;
    }

    public LocalDate getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(LocalDate fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public LocalDate getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(LocalDate fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public Integer getNumero_huespedes() {
        return numero_huespedes;
    }

    public void setNumero_huespedes(Integer numero_huespedes) {
        this.numero_huespedes = numero_huespedes;
    }

    public Double getMonto_total() {
        return monto_total;
    }

    public void setMonto_total(Double monto_total) {
        this.monto_total = monto_total;
    }

    public EstadoReservacion getEstado_reservacion() {
        return estado_reservacion;
    }

    public void setEstado_reservacion(EstadoReservacion estado_reservacion) {
        this.estado_reservacion = estado_reservacion;
    }

    public LocalDateTime getFecha_reservacion() {
        return fecha_reservacion;
    }

    public void setFecha_reservacion(LocalDateTime fecha_reservacion) {
        this.fecha_reservacion = fecha_reservacion;
    }

    public LocalDateTime getFecha_cancelacion() {
        return fecha_cancelacion;
    }

    public void setFecha_cancelacion(LocalDateTime fecha_cancelacion) {
        this.fecha_cancelacion = fecha_cancelacion;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }
}
