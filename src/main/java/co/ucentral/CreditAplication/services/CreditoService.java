package co.ucentral.CreditAplication.services;

import co.ucentral.CreditAplication.models.Credito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.ucentral.CreditAplication.repositories.CreditoRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
public class CreditoService implements Serializable {
    @Autowired
    CreditoRepository repository;

    public Credito save(Credito credito) {
        return repository.save(credito);
    }

    public List<Credito> getAll() {
        return repository.findAll();
    }

    public Optional<Credito> getById(long id) {
        return repository.findById(id);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }
}
