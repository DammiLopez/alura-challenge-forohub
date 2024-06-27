package com.foroHub.domain.respuesta;

import com.foroHub.domain.topico.Topico;
import com.foroHub.domain.topico.TopicoRepository;
import com.foroHub.domain.usario.Usuario;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RespuestaService {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private RespuestaRepository respuestaRepository;

    public DatosRespuestaCreada crearRespuesta(Integer id, DataRegistrarRespuesta data) {
        Usuario user = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        if (topicoOptional.isEmpty()) {
            throw new EntityNotFoundException("El topico no existe");
        }
        Topico topico = topicoOptional.get();
        Respuesta respuesta= new Respuesta(data, topico, user);
        respuestaRepository.save(respuesta);
        return  new DatosRespuestaCreada(
                respuesta.getId(),
                respuesta.getFechaCreacion(),
                respuesta.getAutor().getUsername(),
                respuesta.getTopico().getTitulo()
        );

    }

    public void eliminarRespuesta(Integer id, Integer idTopico) {
        var autor = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Respuesta> respuestaOptional = respuestaRepository.findById(id);
        if (respuestaOptional.isEmpty()) {
            throw new EntityNotFoundException("La respuesta no existe");
        }
        if (!respuestaOptional.get().getAutor().equals(autor) || !respuestaOptional.get().getTopico().getId().equals(idTopico)) {
            throw new RuntimeException("No eres el autor de la respuesta");
        }
        respuestaRepository.delete(respuestaOptional.get());
    }


//    public Page<?> obtenerRespuestasPorTopico(Pageable pagination) {
//        return null;
//    }
}

