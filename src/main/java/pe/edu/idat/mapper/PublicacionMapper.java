package pe.edu.idat.mapper;

import pe.edu.idat.model.entity.PublicacionEntity;
import pe.edu.idat.model.response.PublicacionResponse;

public class PublicacionMapper {
	public static PublicacionResponse publicacionMap(PublicacionEntity publicacionMapeado) {
		return PublicacionResponse.builder()
				.titulo(publicacionMapeado.getTitulo())
				.descripcion(publicacionMapeado.getDescripcion())
				.imagen(publicacionMapeado.getImagen())
				.build();
	}

}
