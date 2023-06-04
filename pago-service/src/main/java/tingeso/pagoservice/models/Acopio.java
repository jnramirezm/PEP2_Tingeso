package tingeso.pagoservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Acopio {

    private Date fecha;
    private String turno;
    private Integer kls;
    private String proveedor;
}
