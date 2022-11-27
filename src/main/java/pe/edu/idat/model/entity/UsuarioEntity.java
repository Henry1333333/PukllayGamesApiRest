package pe.edu.idat.model.entity;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "usuario")
public class UsuarioEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idusuario")
	private Integer idusuario;
	
	@Column(name = "usuario")
	private String nomUsuario;
	
	@Column(name = "correo")
	private String correo;
		
	@Column(name = "telefono")
	private String telefeno;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "estado")
	private Boolean estado;
	

}
