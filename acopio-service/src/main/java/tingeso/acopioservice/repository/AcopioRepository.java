package tingeso.acopioservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tingeso.acopioservice.entity.AcopioEntity;
import java.util.ArrayList;
import java.util.Date;

@Repository
public interface AcopioRepository extends JpaRepository<AcopioEntity, Integer> {

    @Query("Select a.kls from AcopioEntity a where a.proveedor = :proveedor")
    ArrayList<Double> findAllByProveedor(@Param("id") String proveedor );

    @Query(value = "SELECT DISTINCT ON (a.fecha) a.fecha FROM acopio as a WHERE a.proveedor = :proveedor", nativeQuery = true)
    ArrayList<Date> findAllDays(@Param("proveedor") String proveedor);

    @Query("Select Count(a) from AcopioEntity a where a.proveedor = :proveedor and a.turno = :turno")
    Integer findAllDaysWhereTurno(@Param("proveedor") String proveedor, @Param("turno") String turno);

    @Query("Select a from AcopioEntity a where a.proveedor = : proveedor")
    ArrayList<AcopioEntity> findByProveedor(@Param("proveedor") String proveedor);

}
