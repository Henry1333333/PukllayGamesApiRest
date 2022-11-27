package pe.edu.idat.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import pe.edu.idat.model.entity.PublicacionEntity;
import pe.edu.idat.repository.PublicacionRepository;

@Service
public class PublicacionService {
	@Autowired
	private PublicacionRepository repository;
	
	private final Path rootFolder = Paths.get("uploads");
	
	public List<PublicacionEntity> listarPublicaciones(){
		return 
				repository.findAll()
				.stream()
				.filter(l -> l.getEstado()==true)
				.collect(Collectors.toList());
	}
	
	public Optional<PublicacionEntity> encontrarPorId(Integer id){
		return repository.findById(id);
	}
	
	
	public PublicacionEntity crearPublicacion(PublicacionEntity us) {

		PublicacionEntity publi = null;
		try {
			publi = repository.save(us);
			System.out.println("publicacion creada");
			
		} catch (Exception e) {
			System.out.println("hubo un error: " + e.getMessage());
		}
		
		return publi;		
	}
	
	public PublicacionEntity actualizarPublicacion(PublicacionEntity us) {

		PublicacionEntity pub = null;
		try {

			pub = repository.save(us);
			System.out.println("usuario actualizado");
			
		} catch (Exception e) {
			System.out.println("hubo un error: " + e.getMessage());
		}
		
		return pub;		
	}
	
	public PublicacionEntity desactivarPublicacion(Integer id) {
	Optional<PublicacionEntity>us = Optional.empty();
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
	
	

	public String saveImage(MultipartFile file) throws Exception {
		Files.copy(file.getInputStream(), this.rootFolder.resolve(file.getOriginalFilename()));
		
		return this.rootFolder.resolve(file.getOriginalFilename()).toAbsolutePath().toString();
	}
	
	
	public PublicacionEntity convertir (String publicacion) {
		PublicacionEntity pubJson = new PublicacionEntity();
		
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			pubJson = objectMapper.readValue(publicacion, PublicacionEntity.class);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return pubJson;
	}


	public Resource load(String name) throws Exception {
		Path file = rootFolder.resolve(name);
		Resource resource = new UrlResource(file.toUri());
		return resource;
	}


	/*public void save(List<MultipartFile> files) throws Exception {
		for (MultipartFile file : files) {
			this.save(file);
		}
	}*/


	public Stream<Path> loadAll() throws Exception {
		return Files.walk(rootFolder, 1).filter(path -> !path.equals(rootFolder)).map(rootFolder::relativize);
	}
	

}
