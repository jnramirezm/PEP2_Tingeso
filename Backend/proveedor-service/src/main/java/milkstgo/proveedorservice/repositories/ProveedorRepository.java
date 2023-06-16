package milkstgo.proveedorservice.repositories;

import milkstgo.proveedorservice.entities.ProveedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ProveedorRepository extends JpaRepository<ProveedorEntity, Integer> {

    @Query("select p from ProveedorEntity p")

    List<ProveedorEntity> findAllProveedores();
    @Query("Select e from ProveedorEntity e where e.codigo = :codigo ")
    ProveedorEntity findByCodigo(@Param("codigo") String codigo);

}
