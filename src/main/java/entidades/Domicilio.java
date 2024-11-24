package entidades;

import lombok.*;

@ToString (exclude = "localidad")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Domicilio {
    private String calle;
    private Integer numero;
    private Integer codigoPostal;
    private Localidad localidad;
}

