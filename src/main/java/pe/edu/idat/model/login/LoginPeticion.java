package pe.edu.idat.model.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginPeticion {
	private String usuario;
	private String password;

}
