package pe.edu.idat.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.idat.mapper.ComentarioMapper;
import pe.edu.idat.model.entity.ComentarioEntity;
import pe.edu.idat.model.response.ComentarioResponse;
import pe.edu.idat.service.ComentarioService;

@RestController
@RequestMapping("/api/comentario")
public class ComentarioController {
	
	@Autowired
	private ComentarioService service;
	
	@GetMapping("/listar")
	public List<ComentarioResponse> listar(){
		List<ComentarioResponse> lista = service.listarComentarios()
				.stream()
				.map(ComentarioMapper::comentarioMap)
				.collect(Collectors.toList());
		
		return lista;
	}
	
	@GetMapping("/encontrar/{id}")
	@ResponseBody
	public ResponseEntity<ComentarioEntity> encontrarPorId(@PathVariable Integer id) {
		Optional<ComentarioEntity> encontrado = service.encontrarPorId(id);
		
		if (encontrado.isPresent()) {
			return new ResponseEntity<ComentarioEntity>(encontrado.get(),HttpStatus.OK);
		}else {
			return new ResponseEntity<ComentarioEntity>(new ComentarioEntity(),HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/registrar")
	@ResponseBody
	public ResponseEntity <ComentarioEntity> registrarNuevaPublicacion(@RequestBody ComentarioEntity com){
		ComentarioEntity comentarioCreado = service.crearComentario(com);
		
		if(comentarioCreado != null) {
			return new ResponseEntity <ComentarioEntity>(comentarioCreado,
					HttpStatus.CREATED);
		}
		return new ResponseEntity<ComentarioEntity>(comentarioCreado, HttpStatus.BAD_REQUEST);
	}
	
	
	@PutMapping("/actualizar")
	@ResponseBody
	public ResponseEntity <ComentarioEntity> actualizarPublicacion(@RequestBody ComentarioEntity pub){
		ComentarioEntity comentarioRegistrado = service.actualizarComentario(pub);
		
		if(comentarioRegistrado != null) {
			return new ResponseEntity <ComentarioEntity>(comentarioRegistrado,
					HttpStatus.OK);
		}
		return new ResponseEntity<ComentarioEntity>(comentarioRegistrado, HttpStatus.BAD_REQUEST);
		
	}
	
	@DeleteMapping("/desactivar/{id}")
	@ResponseBody
	public ResponseEntity<ComentarioEntity>  desactivarUsuario(@PathVariable Integer id) {
		ComentarioEntity comentarioDesactivado = service.desactivarComentario(id);
		
		if(comentarioDesactivado != null) {
			return new ResponseEntity <ComentarioEntity>(comentarioDesactivado,
					HttpStatus.OK);
		}
		return new ResponseEntity<ComentarioEntity>(comentarioDesactivado, HttpStatus.NOT_FOUND);
		
	}
}
