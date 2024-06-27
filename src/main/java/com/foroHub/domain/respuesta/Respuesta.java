package com.foroHub.domain.respuesta;

import com.foroHub.domain.topico.Topico;
import com.foroHub.domain.usario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "respuestas")
@Entity(name = "Respuesta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String mensaje;
    private LocalDateTime fechaCreacion;
    private Boolean solucion;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "topico")
    private Topico topico;

    @ManyToOne
    @JoinColumn(name = "autor")
    private Usuario autor;


    public Respuesta(DataRegistrarRespuesta data, Topico topico, Usuario autor) {
        this.mensaje = data.mensaje();
        this.fechaCreacion = LocalDateTime.now();
        this.solucion = false;
        this.topico = topico;
        this.autor = autor;
    }
}
