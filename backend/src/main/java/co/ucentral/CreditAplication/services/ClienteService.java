package co.ucentral.CreditAplication.services;

import co.ucentral.CreditAplication.models.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.ucentral.CreditAplication.repositories.ClienteRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService implements Serializable {
    @Autowired
    ClienteRepository repository;

    public Cliente save(Cliente cliente) {
        return repository.save(cliente);
    }

    public List<Cliente> getAll() {
        return repository.findAll();
    }

    public Optional<Cliente> getById(long id) {
        return repository.findById(id);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }
}
