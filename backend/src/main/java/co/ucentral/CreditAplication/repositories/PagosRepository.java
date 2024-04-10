package co.ucentral.CreditAplication.repositories;

import co.ucentral.CreditAplication.models.Pagos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PagosRepository extends JpaRepository<Pagos, Long>, JpaSpecificationExecutor<Pagos> {
}
