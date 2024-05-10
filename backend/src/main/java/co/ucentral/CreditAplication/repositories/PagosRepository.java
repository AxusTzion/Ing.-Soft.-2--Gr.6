package co.ucentral.CreditAplication.repositories;

import co.ucentral.CreditAplication.models.Pagos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PagosRepository extends JpaRepository<Pagos, Long>, JpaSpecificationExecutor<Pagos> {
    public List<Pagos> findAllByCreditoClienteId(long creditoClienteId);
}
