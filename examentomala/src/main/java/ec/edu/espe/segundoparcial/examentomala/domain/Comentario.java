package ec.edu.espe.segundoparcial.examentomala.domain;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class Comentario {
    private Integer calificacion; 
    private String comentario; 
    private String usuario; 
    
    @Field("fecha_comentario")
    private LocalDateTime fechaComentario; 


}
