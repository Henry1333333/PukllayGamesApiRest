package pe.edu.idat.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublicacionResponse {
    private String titulo;
    private String descripcion;
    private String imagen;

	
}
