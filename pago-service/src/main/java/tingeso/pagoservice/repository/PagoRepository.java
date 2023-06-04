package tingeso.pagoservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tingeso.pagoservice.entity.PagoEntity;
import java.util.ArrayList;
import java.util.Date;

@Repository
public interface PagoRepository extends JpaRepository<PagoEntity, Integer> {

    @Query(value = "Select p.quincena from pago as p order by p.quincena desc limit 1", nativeQuery = true)
    Date findLastQuincena();

    @Query("Select p from PagoEntity p where p.proveedor = :proveedor order by p.quincena desc")
    ArrayList<PagoEntity> findByProveedor(@Param("proveedor") String proveedor);
}
