package tingeso.proveedoresservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tingeso.proveedoresservice.entity.ProveedorEntity;

@Repository
public interface ProveedorRepository extends JpaRepository<ProveedorEntity, Integer> {
    @Query("Select e from ProveedorEntity e where e.codigo = :codigo ")
    ProveedorEntity findByCodigo(@Param("codigo") String codigo);
}

