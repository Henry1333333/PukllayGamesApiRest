package pe.edu.idat.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import pe.edu.idat.model.entity.UsuarioEntity;
import pe.edu.idat.model.login.LoginPeticion;


@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer>{
	
	@Query(value = "select * from usuario u where u.usuario = ?1 and u.password = ?2", nativeQuery = true)
	Optional<UsuarioEntity> findByNombreUsuarioAndContraseniaUsuario(@Param("usuario") String usuario, @Param("password") String password);
	

	
	

}
