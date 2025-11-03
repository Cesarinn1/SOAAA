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
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "reseñas")
public class ReseñaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_reseña;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_propiedad", nullable = true)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private PropiedadModel propiedad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = true)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private ClienteModel cliente;

    @Column(name = "calificacion", nullable = false)
    private Integer calificacion; // Generalmente de 1 a 5

    @Column(name = "comentario", length = 1000)
    private String comentario;

    @Column(name = "fecha_reseña", nullable = false)
    private LocalDateTime fecha_reseña;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime created_at;

    @PrePersist
    public void onPrePersist() {
        if (this.created_at == null) {
            this.created_at = LocalDateTime.now();
        }
        if (this.fecha_reseña == null) {
            this.fecha_reseña = LocalDateTime.now();
        }
    }

    // Constructores
    public ReseñaModel() {
    }

    public ReseñaModel(PropiedadModel propiedad, ClienteModel cliente, Integer calificacion, String comentario) {
        this.propiedad = propiedad;
        this.cliente = cliente;
        this.calificacion = calificacion;
        this.comentario = comentario;
    }

    public ReseñaModel(Long id_reseña, PropiedadModel propiedad, ClienteModel cliente, Integer calificacion,
                       String comentario, LocalDateTime fecha_reseña, LocalDateTime created_at) {
        this.id_reseña = id_reseña;
        this.propiedad = propiedad;
        this.cliente = cliente;
        this.calificacion = calificacion;
        this.comentario = comentario;
        this.fecha_reseña = fecha_reseña;
        this.created_at = created_at;
    }

    // Getters y Setters
    public Long getId_reseña() {
        return id_reseña;
    }

    public void setId_reseña(Long id_reseña) {
        this.id_reseña = id_reseña;
    }

    public PropiedadModel getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(PropiedadModel propiedad) {
        this.propiedad = propiedad;
    }

    public ClienteModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDateTime getFecha_reseña() {
        return fecha_reseña;
    }

    public void setFecha_reseña(LocalDateTime fecha_reseña) {
        this.fecha_reseña = fecha_reseña;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }
}
