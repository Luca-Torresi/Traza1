package entidades;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@ToString
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pais {
    private String nombre;
    @Builder.Default
    private Set<Provincia> provincias = new HashSet<>();
}
