package co.ucentral.CreditAplication.controllers;

import co.ucentral.CreditAplication.dtos.CustomerLoginDto;
import co.ucentral.CreditAplication.models.Cliente;
import co.ucentral.CreditAplication.models.User;
import co.ucentral.CreditAplication.models.dtos.ClienteCreateDto;
import co.ucentral.CreditAplication.models.dtos.SignUpDto;
import co.ucentral.CreditAplication.models.enums.UserRole;
import co.ucentral.CreditAplication.models.excetions.InvalidJwtException;
import co.ucentral.CreditAplication.services.AuthService;
import co.ucentral.CreditAplication.services.ClienteService;
import org.modelmapper.ModelMapper;
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
    private AuthService service;

    @Autowired
    ClienteService clientService;

    @Autowired
    private ModelMapper modelMapper;

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
    public ResponseEntity<Cliente> create(@RequestBody ClienteCreateDto clienteDto) {
        try {
            User user = service.signUp(new SignUpDto(clienteDto.getNumeroDeIdentificacion(), clienteDto.getPassword(), UserRole.USER));
            var cliente = modelMapper.map(clienteDto, Cliente.class);
            cliente.setUser(user);
            Cliente userCreated = clientService.save(cliente);
            return new ResponseEntity(userCreated, HttpStatus.CREATED);
        } catch (InvalidJwtException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<Cliente> delete(@RequestParam(value = "id") long id) {
        clientService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
