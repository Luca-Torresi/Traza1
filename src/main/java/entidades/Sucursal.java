package entidades;

import java.time.LocalTime;
import lombok.*;

@ToString (exclude = "empresa")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Sucursal {
    private String nombre;
    private LocalTime horarioApertura;
    private LocalTime horarioCierre;
    private boolean esCasaMatriz;
    private Empresa empresa;
    private Domicilio domicilio;
}
