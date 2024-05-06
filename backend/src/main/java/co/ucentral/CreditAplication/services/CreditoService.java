package co.ucentral.CreditAplication.services;

import co.ucentral.CreditAplication.models.Credito;

import co.ucentral.CreditAplication.models.CreditoEstadoEnum;
import co.ucentral.CreditAplication.models.TipoCredito;
import co.ucentral.CreditAplication.models.dtos.CreditStatusChangeRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.ucentral.CreditAplication.repositories.CreditoRepository;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
public class CreditoService implements Serializable {
    @Autowired
    CreditoRepository repository;

    public Credito save(Credito credito) {
        if(!ObjectUtils.isEmpty(credito.getTipo())){
            credito.setPorcentajeInteres(calculateInterestRate(credito.getTipo()));
        }
        credito.setEsAprobado(CreditoEstadoEnum.PENDIENTE);
        return repository.save(credito);
    }
    public List<Credito> getAll() {
        return repository.findAll();
    }

    public List<Credito> getAllPending() {
        return repository.findByEsAprobadoEquals(CreditoEstadoEnum.PENDIENTE);
    }

    public void updateCreditState(CreditStatusChangeRequestDto creditStatusChangeRequestDto) {
        Optional<Credito> credito = getById(creditStatusChangeRequestDto.getId());
        if(credito.isPresent()) {
            CreditoEstadoEnum estado = creditStatusChangeRequestDto.getIsApproved() ? CreditoEstadoEnum.APPROVADO : CreditoEstadoEnum.RECHAZADO;
            credito.get().setEsAprobado(estado);
            this.repository.save(credito.get());
        }
    }

    public Optional<Credito> getById(long id) {
        return repository.findById(id);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }


    protected Double calculateInterestRate(TipoCredito tipoCredito) {
        switch (tipoCredito){
            case Vivienda -> {
                return 1.1;
            }
            case Estudio -> {
                return 0.9;
            }
            case Vehiculo -> {
                return 1.7;
            }
            case CompraDeCartera -> {
                return 0.8;
            }
            case LibreInversion -> {
                return 1.5;
            }
            default -> {
                return 0d;
            }
        }
    }
}
