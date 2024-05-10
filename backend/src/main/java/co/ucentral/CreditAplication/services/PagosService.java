package co.ucentral.CreditAplication.services;

import co.ucentral.CreditAplication.models.Pagos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.ucentral.CreditAplication.repositories.PagosRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
public class PagosService implements Serializable {
    @Autowired
    PagosRepository repository;

    public Pagos save(Pagos pagos) {
        return repository.save(pagos);
    }

    public List<Pagos> getAll() {
        return repository.findAll();
    }

    public List<Pagos> getAllByCliente(long clienteId) {
        return repository.findAllByCreditoClienteId(clienteId);
    }

    public Optional<Pagos> getById(long id) {
        return repository.findById(id);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }
}
