package pe.edu.idat.mapper;

import pe.edu.idat.model.entity.UsuarioEntity;

import pe.edu.idat.model.response.UsuarioResponse;

public class UsuarioMapper {
	public static UsuarioResponse usuarioMap(UsuarioEntity usuarioMapeado) {
		return UsuarioResponse.builder().nombre(usuarioMapeado.getNomUsuario())
				.correo(usuarioMapeado.getCorreo())
				.telefono(usuarioMapeado.getTelefeno())
				.password(usuarioMapeado.getPassword()).build();
	}
}
