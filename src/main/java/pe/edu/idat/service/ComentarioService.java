package pe.edu.idat.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.idat.model.entity.ComentarioEntity;
import pe.edu.idat.repository.ComentarioRepository;

@Service
public class ComentarioService {
	@Autowired
	private ComentarioRepository repository;
	
	
	public List<ComentarioEntity> listarComentarios(){
		return 
				repository.findAll()
				.stream()
				.filter(l -> l.getEstado()==true)
				.collect(Collectors.toList());
	}
	
	
	public Optional<ComentarioEntity> encontrarPorId(Integer id){
		return repository.findById(id);
	}
	
	
	public ComentarioEntity crearComentario(ComentarioEntity us) {
		ComentarioEntity publi = null;
		try {
			publi = repository.save(us);
			System.out.println("comentario creado");
			
		} catch (Exception e) {
			System.out.println("hubo un error: " + e.getMessage());
		}
		
		return publi;		
	}
	
	
	public ComentarioEntity actualizarComentario(ComentarioEntity us) {
		ComentarioEntity com = null;
		try {

			com = repository.save(us);
			System.out.println("comentario actualizado");
			
		} catch (Exception e) {
			System.out.println("hubo un error: " + e.getMessage());
		}
		
		return com;		
	}
	
	public ComentarioEntity desactivarComentario(Integer id) {
	Optional<ComentarioEntity>us = Optional.empty();
		try {
			us = repository.findById(id);
			
			if(us.isPresent()) {
				us.get().setEstado(false);
				repository.save(us.get());
			}			
		} catch (Exception e) {
			System.out.println("hubo un error: " + e.getMessage());

		}		
		return us.get();
	}
}
