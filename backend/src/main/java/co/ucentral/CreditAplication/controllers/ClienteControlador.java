package co.ucentral.CreditAplication.controllers;

import co.ucentral.CreditAplication.dtos.CustomerLoginDto;
import co.ucentral.CreditAplication.models.Cliente;
import co.ucentral.CreditAplication.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("Cliente")
public class ClienteControlador {

    @Autowired
    ClienteService clientService;

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public ResponseEntity<Cliente> list() {
        List<Cliente> users = clientService.getAll();
        return new ResponseEntity(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/client", method = RequestMethod.GET)
    public ResponseEntity<Cliente> clientePorId(@RequestParam(value = "id") long id) {
        Optional<Cliente> clienteOptional = clientService.getById(id);
        if(clienteOptional.isPresent()) {
            return new ResponseEntity(clienteOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Cliente> create(@RequestBody Cliente cliente) {
        Cliente userCreated = clientService.save(cliente);
        return new ResponseEntity(userCreated, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<Cliente> delete(@RequestParam(value = "id") long id) {
        clientService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
