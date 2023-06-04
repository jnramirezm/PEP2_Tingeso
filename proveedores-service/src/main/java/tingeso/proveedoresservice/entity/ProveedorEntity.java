package tingeso.proveedoresservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "proveedor")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProveedorEntity {

    @Id
    @SequenceGenerator(name = "proveedor_sequence", sequenceName = "proveedor_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "proveedor_sequence")
    private Integer id;
    private String codigo;
    private String nombre;
    private String categoria;
    private String retencion;
}

