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
import jakarta.persistence.Table;

@Entity
@Table(name = "pagos")
public class PagosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pago;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reservacion", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private ReservaModel reserva;

    @Column(name = "monto", nullable = false)
    private Double monto;

    @Enumerated(EnumType.STRING)
    @Column(name = "metodo_pago", nullable = false)
    private MetodoPago metodo_pago;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_pago", nullable = false)
    private EstadoPago estado_pago;

    @Column(name = "fecha_pago", nullable = false)
    private LocalDate fecha_pago;

    @Column(name = "referencia_pago", nullable = false, length = 100)
    private String referenciaPago;

    @Column(name = "detalles_pago", length = 500)
    private String detalles_pago;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime created_at;

    public enum MetodoPago {
        TARJETA_CREDITO,
        TARJETA_DEBITO,
        EFECTIVO,
        TRANSFERENCIA,
        PAYPAL
    }

    public enum EstadoPago {
        PENDIENTE,
        COMPLETADO,
        FALLIDO,
        REEMBOLSADO
    }

    @PrePersist
    public void onPrePersist() {
        if (this.created_at == null) {
            this.created_at = LocalDateTime.now();
        }
        if (this.fecha_pago == null) {
            this.fecha_pago = LocalDate.now();
        }
    }

    // Constructores
    public PagosModel() {
    }

    public PagosModel(ReservaModel reserva, Double monto, MetodoPago metodo_pago, EstadoPago estado_pago,
                      String referenciaPago, String detalles_pago) {
        this.reserva = reserva;
        this.monto = monto;
        this.metodo_pago = metodo_pago;
        this.estado_pago = estado_pago;
        this.referenciaPago = referenciaPago;
        this.detalles_pago = detalles_pago;
    }

    public PagosModel(Long id_pago, ReservaModel reserva, Double monto, MetodoPago metodo_pago,
                      EstadoPago estado_pago, LocalDate fecha_pago, String referenciaPago,
                      String detalles_pago, LocalDateTime created_at) {
        this.id_pago = id_pago;
        this.reserva = reserva;
        this.monto = monto;
        this.metodo_pago = metodo_pago;
        this.estado_pago = estado_pago;
        this.fecha_pago = fecha_pago;
        this.referenciaPago = referenciaPago;
        this.detalles_pago = detalles_pago;
        this.created_at = created_at;
    }

    // Getters y Setters
    public Long getId_pago() {
        return id_pago;
    }

    public void setId_pago(Long id_pago) {
        this.id_pago = id_pago;
    }

    public ReservaModel getReserva() {
        return reserva;
    }

    public void setReserva(ReservaModel reserva) {
        this.reserva = reserva;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public MetodoPago getMetodo_pago() {
        return metodo_pago;
    }

    public void setMetodo_pago(MetodoPago metodo_pago) {
        this.metodo_pago = metodo_pago;
    }

    public EstadoPago getEstado_pago() {
        return estado_pago;
    }

    public void setEstado_pago(EstadoPago estado_pago) {
        this.estado_pago = estado_pago;
    }

    public LocalDate getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(LocalDate fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    public String getReferenciaPago() {
        return referenciaPago;
    }

    public void setReferenciaPago(String referenciaPago) {
        this.referenciaPago = referenciaPago;
    }

    public String getDetalles_pago() {
        return detalles_pago;
    }

    public void setDetalles_pago(String detalles_pago) {
        this.detalles_pago = detalles_pago;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }
}
