package milkstgo.acopioservice.repositories;

import milkstgo.acopioservice.entities.AcopioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public interface AcopioRepository extends JpaRepository<AcopioEntity, Integer> {

    @Query("Select a.kls from AcopioEntity a where a.proveedor = :proveedor")
    List<Double> findAllByProveedor(@Param("proveedor") String proveedor );

    @Query(value = "SELECT DISTINCT ON (a.fecha) a.fecha FROM acopio as a WHERE a.proveedor = :proveedor", nativeQuery = true)
    List<Date> findAllDays(@Param("proveedor") String proveedor);

    @Query("Select Count(a) from AcopioEntity a where a.proveedor = :proveedor and a.turno = :turno")
    Integer findAllDaysWhereTurno(@Param("proveedor") String proveedor, @Param("turno") String turno);

    @Query("Select a from AcopioEntity a where a.proveedor = : proveedor")
    List<AcopioEntity> findByProveedor(@Param("proveedor") String proveedor);

}
