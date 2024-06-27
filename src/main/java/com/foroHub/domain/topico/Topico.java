package com.foroHub.domain.topico;

import com.foroHub.domain.curso.Curso;
import com.foroHub.domain.respuesta.Respuesta;
import com.foroHub.domain.usario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String titulo;

    private String mensaje;

    private LocalDateTime fechaCreacion;

    private String status;

    @OneToMany(mappedBy = "topico")
    private List<Respuesta> respuestas;

    @ManyToOne
    @JoinColumn(name = "autor")
    private Usuario autor;

    @ManyToOne
    @JoinColumn(name = "curso")
    private Curso curso;

    public Topico(DataCrearTopico data) {
        this.titulo = data.titulo();
        this.mensaje = data.mensaje();
        this.fechaCreacion = LocalDateTime.now();
        this.status = "Pendiente";
    }

    public void actualizar(Topico topico) {
        this.titulo = topico.getTitulo();
        this.mensaje = topico.getMensaje();
        this.status = topico.getStatus();
        this.curso = topico.getCurso();
    }
}
