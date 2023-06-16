package milkstgo.pagoservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Laboratorio {
    private String proveedor;
    private Integer grasa;
    private Integer solido;
    private Date fecha;
}
