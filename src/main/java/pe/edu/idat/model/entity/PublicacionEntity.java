package pe.edu.idat.model.entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "publicacion")
public class PublicacionEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idpublicacion")
	private Integer idpublicacion;
	
	@ManyToOne
	@JoinColumn(name = "idusuario")
	private UsuarioEntity idUs;
	
	@Column(name = "titulo")
	private String titulo;
	
	@Column(name = "descripcion")
	private String descripcion; 
	
	@Column(name = "imagen")
	private String imagen;
	
	@Column(name = "estado")
	private Boolean estado;
	
	/*@OneToMany(mappedBy = "idPublicacion",  cascade = CascadeType.ALL)
	public List<ComentarioEntity> lstComentario;*/
}
