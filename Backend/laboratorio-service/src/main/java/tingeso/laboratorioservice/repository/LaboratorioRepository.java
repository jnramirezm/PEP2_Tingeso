package tingeso.laboratorioservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tingeso.laboratorioservice.entity.LaboratorioEntity;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface LaboratorioRepository extends JpaRepository<LaboratorioEntity, Integer> {

    @Query(value = "Select l from LaboratorioEntity l where l.proveedor = :proveedor order by l.fecha desc")
    List<LaboratorioEntity> findByProveedor(@Param("proveedor") String proveedor);
}