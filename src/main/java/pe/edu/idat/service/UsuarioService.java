package pe.edu.idat.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.idat.model.entity.UsuarioEntity;
import pe.edu.idat.model.login.LoginPeticion;
import pe.edu.idat.repository.UsuarioRepository;



@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository repository;
	
	public List<UsuarioEntity> listarUsuarios(){
		return 
				repository.findAll()
				.stream()
				.filter(l -> l.getEstado()==true)
				.collect(Collectors.toList());
	}
	
	public Optional<UsuarioEntity> encontrarPorId(Integer id){
		return repository.findById(id);
	}
	
	public UsuarioEntity registrarUsuario(UsuarioEntity us) {
		UsuarioEntity user = null;
		try {
			//Aqui harmeos el registro
			user = repository.save(us);
			System.out.println("usuario registrado");
			
		} catch (Exception e) {
			System.out.println("hubo un error: " + e.getMessage());
		}
		
		return user;		
	}
	
	public UsuarioEntity actualizarAlumno(UsuarioEntity us) {
		UsuarioEntity user = null;
		try {
			//Aqui harmeos el registro del libro
			user = repository.save(us);
			System.out.println("usuario actualizado");
			
		} catch (Exception e) {
			System.out.println("hubo un error: " + e.getMessage());
		}
		
		return user;		
	}
	
	public UsuarioEntity eliminarUsuario(Integer id) {
		Optional<UsuarioEntity> user= Optional.empty();
		try {
			user=repository.findById(id);
			
			if(user.isPresent()) {
				repository.delete(user.get());
				System.out.println("usuario eliminado");
			}			
		}catch (Exception e) {
			System.err.println("hubo un error" + e.getMessage());
		}
		
		return user.get();
	}
	
	public UsuarioEntity desactivarUsuario(Integer id) {
	Optional<UsuarioEntity>user = Optional.empty();
		try {
			user = repository.findById(id);
			
			if(user.isPresent()) {
				user.get().setEstado(false);
				repository.save(user.get());
			}			
		} catch (Exception e) {
			System.out.println("hubo un error: " + e.getMessage());

		}		
		return user.get();
	}
	
	public UsuarioEntity encontrarCorreoPass(LoginPeticion log) {
		String mensaje = "";
		Optional<UsuarioEntity> user= Optional.empty();
		try {
			user = repository.findByNombreUsuarioAndContraseniaUsuario(log.getUsuario(), log.getPassword());
			if(user.isPresent()) {
				mensaje = "Se inicio sesion exitosamente";
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("hubo un error: " + e.getMessage());
			mensaje = "No se encontro al usuario";
		}
		return user.get();
		
		
	}


}
