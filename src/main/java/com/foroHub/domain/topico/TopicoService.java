package com.foroHub.domain.topico;

import com.foroHub.domain.curso.Curso;
import com.foroHub.domain.curso.CursoRepository;
import com.foroHub.domain.usario.Usuario;
import com.foroHub.domain.usario.UsuarioRepository;
import io.micrometer.observation.ObservationFilter;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicoService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private TopicoRepository topicoRepository;

    public DataRespuestaTopico crearTopico(DataCrearTopico data) {

        System.out.println("id request -> " + data.id());

        Optional<Usuario> usuario = usuarioRepository.findById(data.id());
        System.out.println("-----------" + usuario.get().getUsername() + "-----------");
        Optional<Curso> curso = cursoRepository.findByNombre(data.nombreCurso());

        if (usuario.isEmpty()) {
            throw new RuntimeException("El usuario no existe");
        }
        if (curso.isEmpty()) {
            throw new RuntimeException("El curso no existe");
        }

        Topico topico = new Topico(data);
        topico.setAutor(usuario.get());
        topico.setCurso(curso.get());
        return new DataRespuestaTopico(
                topicoRepository.save(topico));
    }


    public Page<Topico> obtenerTodosLosTopicosPorNombreYAnio(Pageable pageable, String nombre, int anio) {
        return topicoRepository.findAllByNameAndYear(pageable, nombre, anio);
    }

    public Page<Topico> obtenerTodosLosTopicosPorFecha(Pageable pagination) {
        return topicoRepository.findAllByfechaCreacionOrderByIdAsc(pagination);
    }

    public DataRespuestaTopico obtenerTopicoPorId(Integer id) {
        Optional<Topico> topico =  topicoRepository.findById(id);
        if (topico.isEmpty()) {
            throw new EntityNotFoundException("El topico no existe");
        }
        return new DataRespuestaTopico(topico.get());
    }

    public Page<Topico> obtenerTodosLosTopicos(Pageable pagination) {
        return topicoRepository.findAll(pagination);
    }

    public DataRespuestaTopico actualizarTopico(Integer id, DataActualizarTopico datos) {

        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        if (topicoOptional.isEmpty()) {
            throw new RuntimeException("El topico no existe");
        }

        Topico topico = topicoOptional.get();

        if (datos.titulo().length() > 3 ) {
            topico.setTitulo(datos.titulo());
        }
        if (datos.mensaje().length() > 3 ) {
            topico.setMensaje(datos.mensaje());
        }
        if(datos.nombreCurso() != null) {
            Optional<Curso> cursoOptional = cursoRepository.findByNombre(datos.nombreCurso());
            if (cursoOptional.isEmpty()) {
                throw new RuntimeException("El curso no existe");
            }
            topico.setCurso(cursoOptional.get());
        }
        if (datos.status() != null) {
            topico.setStatus(datos.status());
        }
        topico.actualizar(topico);

        return new DataRespuestaTopico(topico);
    }

    public void eliminarTopicos(Integer id) {
        topicoRepository.deleteById(id);
    }
}


