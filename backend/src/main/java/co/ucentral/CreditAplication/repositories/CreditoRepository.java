package co.ucentral.CreditAplication.repositories;

import co.ucentral.CreditAplication.models.Credito;
import co.ucentral.CreditAplication.models.CreditoEstadoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CreditoRepository extends JpaRepository<Credito, Long>, JpaSpecificationExecutor<Credito> {

    public List<Credito> findByEsAprobadoEquals(CreditoEstadoEnum esAprobado);
}
