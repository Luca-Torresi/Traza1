package entidades;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@ToString
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Provincia {
    private String nombre;
    private Pais pais;
    @Builder.Default
    private Set<Localidad> localidades = new HashSet<>();
}
