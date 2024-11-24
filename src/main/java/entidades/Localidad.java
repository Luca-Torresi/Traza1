package entidades;

import lombok.*;

@ToString
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Localidad {
    private String nombre;
    private Provincia provincia;
}
