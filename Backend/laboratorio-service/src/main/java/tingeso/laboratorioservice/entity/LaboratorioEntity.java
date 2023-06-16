package tingeso.laboratorioservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "laboratorio")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LaboratorioEntity {

    @Id
    @SequenceGenerator(name = "laboratorio_sequence", sequenceName = "laboratorio_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "laboratorio_sequence")
    private Integer id_laboratorio;
    private Integer grasa;
    private Integer solido;
    private Date fecha;
    private String proveedor;
}