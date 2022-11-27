package pe.edu.idat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.idat.model.entity.ComentarioEntity;

@Repository
public interface ComentarioRepository extends JpaRepository<ComentarioEntity, Integer>{
	

}
