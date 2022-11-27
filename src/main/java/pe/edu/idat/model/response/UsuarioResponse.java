package pe.edu.idat.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponse {
	private String nombre;
	private String correo;
	private String telefono;
	private String password;
	

}
