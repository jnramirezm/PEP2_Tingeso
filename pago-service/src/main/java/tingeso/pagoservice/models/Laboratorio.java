package tingeso.pagoservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class Laboratorio {

    private Integer grasa;
    private Integer solido;
    private Date fecha;
    private String proveedor;

}