package pe.edu.idat.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import pe.edu.idat.mapper.PublicacionMapper;
import pe.edu.idat.model.entity.PublicacionEntity;
import pe.edu.idat.model.response.PublicacionResponse;
import pe.edu.idat.service.PublicacionService;

@RestController
@RequestMapping("/api/publicacion")
public class PublicacionController {

	@Autowired
	private PublicacionService service;

	@GetMapping("/listar")
	public List<PublicacionResponse> listar() {
		List<PublicacionResponse> lista =service.listarPublicaciones()
				.stream()
				.map(PublicacionMapper::publicacionMap)
				.collect(Collectors.toList());
		
		return lista;
		
	}

	@GetMapping("/encontrar/{id}")
	@ResponseBody
	public ResponseEntity<PublicacionEntity> encontrarPorId(@PathVariable Integer id) {
		Optional<PublicacionEntity> encontrado = service.encontrarPorId(id);

		if (encontrado.isPresent()) {
			return new ResponseEntity<PublicacionEntity>(encontrado.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<PublicacionEntity>(new PublicacionEntity(), HttpStatus.NOT_FOUND);
		}
	}

	
	
	@PostMapping(value = "/registrar", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
	@ResponseBody
	public ResponseEntity<PublicacionEntity> registrarNuevaPublicacion(@RequestPart("publicacion") String publicacion, @RequestPart("file") MultipartFile imagen) {
		PublicacionEntity publicac = null;
		String ruta = "";
		try {
			ruta = service.saveImage(imagen);
			publicac = service.convertir(publicacion);
			publicac.setImagen(ruta);
			service.crearPublicacion(publicac);

				return new ResponseEntity<PublicacionEntity>(publicac, HttpStatus.CREATED);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<PublicacionEntity>(publicac, HttpStatus.BAD_REQUEST);
	}

	
	
	@PutMapping("/actualizar")
	@ResponseBody
	public ResponseEntity<PublicacionEntity> actualizarPublicacion(@RequestBody PublicacionEntity pub) {
		PublicacionEntity publicacionRegistrado = service.actualizarPublicacion(pub);

		if (publicacionRegistrado != null) {
			return new ResponseEntity<PublicacionEntity>(publicacionRegistrado, HttpStatus.OK);
		}
		return new ResponseEntity<PublicacionEntity>(publicacionRegistrado, HttpStatus.BAD_REQUEST);

	}

	@DeleteMapping("/desactivar/{id}")
	@ResponseBody
	public ResponseEntity<PublicacionEntity> desactivarUsuario(@PathVariable Integer id) {
		PublicacionEntity publicacionDesactivado = service.desactivarPublicacion(id);

		if (publicacionDesactivado != null) {
			return new ResponseEntity<PublicacionEntity>(publicacionDesactivado, HttpStatus.OK);
		}
		return new ResponseEntity<PublicacionEntity>(publicacionDesactivado, HttpStatus.NOT_FOUND);

	}

}
