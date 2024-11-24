package entidades;

import java.util.Set;
import java.util.HashSet;
import lombok.*;

@ToString (exclude = "sucursales")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Empresa {
    private Long id;
    private String nombre;
    private String razonSocial;
    private Integer cuit;
    private String logo;
    @Builder.Default
    private Set<Sucursal> sucursales = new HashSet<>();
}
