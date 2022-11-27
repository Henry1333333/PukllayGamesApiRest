package pe.edu.idat.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.idat.model.entity.PublicacionEntity;

@Repository
public interface PublicacionRepository extends JpaRepository<PublicacionEntity, Integer>{
	
	/*public void saveImage(MultipartFile file);
	
	public Resource loadImage(String name);
	
	public Stream<Path> listImage();*/

}
