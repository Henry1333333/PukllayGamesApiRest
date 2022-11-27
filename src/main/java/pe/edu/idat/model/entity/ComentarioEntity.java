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
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comentario" )
public class ComentarioEntity implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idcomentario")
	private Integer idcomentario;
	
	@ManyToOne
	@JoinColumn(name = "idpublicacion")
	private PublicacionEntity idPublicacion;
	
	@ManyToOne
	@JoinColumn(name = "idusuario")
	private UsuarioEntity idUsuario;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "estado")
	private Boolean estado;
	
}
