package co.ucentral.CreditAplication.repositories;

import co.ucentral.CreditAplication.models.Credito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CreditoRepository extends JpaRepository<Credito, Long>, JpaSpecificationExecutor<Credito> {
}
