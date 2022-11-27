package pe.edu.idat.mapper;

import pe.edu.idat.model.entity.ComentarioEntity;
import pe.edu.idat.model.response.ComentarioResponse;

public class ComentarioMapper {
	public static ComentarioResponse comentarioMap(ComentarioEntity comentarioMapeado) {
		return ComentarioResponse.builder().idComentario(comentarioMapeado.getIdcomentario())
				.descipcion(comentarioMapeado.getDescripcion())
				.build();
	}

}
