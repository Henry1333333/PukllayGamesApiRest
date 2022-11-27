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

import pe.edu.idat.mapper.UsuarioMapper;
import pe.edu.idat.model.entity.UsuarioEntity;
import pe.edu.idat.model.login.LoginPeticion;
import pe.edu.idat.model.response.UsuarioResponse;
import pe.edu.idat.service.UsuarioService;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
	@Autowired
	private UsuarioService service;
	
	//Metodo get
	@GetMapping("/listar")
	public List<UsuarioResponse> listar(){
		List<UsuarioResponse> lista =service.listarUsuarios()
				.stream()
				.map(UsuarioMapper::usuarioMap)
				.collect(Collectors.toList());
		
		return lista;
	}
	
	@GetMapping("/encontrar/{id}")
	@ResponseBody
	public ResponseEntity<UsuarioEntity> encontrarPorId(@PathVariable Integer id) {
		Optional<UsuarioEntity> encontrado = service.encontrarPorId(id);
		
		if (encontrado.isPresent()) {
			return new ResponseEntity<UsuarioEntity>(encontrado.get(),HttpStatus.OK);
		}else {
			return new ResponseEntity<UsuarioEntity>(new UsuarioEntity(),HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/login")
	@ResponseBody
	public ResponseEntity<LoginPeticion> login(@RequestBody LoginPeticion log){
		UsuarioEntity login= service.encontrarCorreoPass(log);
		if(login != null) {
			return ResponseEntity.ok(log);
		}
		return ResponseEntity.badRequest().body(null);
	}
	
	@PostMapping("/registrar")
	@ResponseBody
	public ResponseEntity <UsuarioEntity> registrarNuevoUsuario(@RequestBody UsuarioEntity user){
		UsuarioEntity usuarioRegistrado = service.registrarUsuario(user);
		
		if(usuarioRegistrado != null) {
			return new ResponseEntity <UsuarioEntity>(usuarioRegistrado,
					HttpStatus.CREATED);
		}
		return new ResponseEntity<UsuarioEntity>(usuarioRegistrado, HttpStatus.BAD_REQUEST);
	}	
	
	@PutMapping("/actualizar")
	@ResponseBody
	public ResponseEntity <UsuarioEntity> actualizarUsuario(@RequestBody UsuarioEntity cli){
		UsuarioEntity usuarioRegistrado = service.actualizarAlumno(cli);
		
		if(usuarioRegistrado != null) {
			return new ResponseEntity <UsuarioEntity>(usuarioRegistrado,
					HttpStatus.OK);
		}
		return new ResponseEntity<UsuarioEntity>(usuarioRegistrado, HttpStatus.BAD_REQUEST);
		
	}
	
	@DeleteMapping("/eliminar/{id}")
	@ResponseBody
	public ResponseEntity<UsuarioEntity> eliminarUsuario(@PathVariable Integer id) {
		UsuarioEntity Desactivado = service.eliminarUsuario(id);
		
		
		if(Desactivado != null) {
			return new ResponseEntity<UsuarioEntity>(Desactivado,
					HttpStatus.OK);
		}
		
		return new ResponseEntity<UsuarioEntity>(Desactivado,
				HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/desactivar/{id}")
	@ResponseBody
	public ResponseEntity<UsuarioEntity>  desactivarUsuario(@PathVariable Integer id) {
		UsuarioEntity usuarioDesactivado = service.desactivarUsuario(id);
		
		if(usuarioDesactivado != null) {
			return new ResponseEntity <UsuarioEntity>(usuarioDesactivado,
					HttpStatus.OK);
		}
		return new ResponseEntity<UsuarioEntity>(usuarioDesactivado, HttpStatus.NOT_FOUND);
		
	}
}
