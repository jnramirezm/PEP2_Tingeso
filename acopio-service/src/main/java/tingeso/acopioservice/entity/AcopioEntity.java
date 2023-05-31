package tingeso.acopioservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "acopio")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AcopioEntity {

    @Id
    @SequenceGenerator(name = "acopio_sequence", sequenceName = "acopio_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "acopio_sequence")
    private Integer id_acopio;
    private Date fecha;
    private String turno;
    private Integer kls;
    private String proveedor;


}
