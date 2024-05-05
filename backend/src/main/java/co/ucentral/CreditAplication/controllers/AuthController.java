package co.ucentral.CreditAplication.controllers;

import co.ucentral.CreditAplication.dtos.CustomerLoginDto;
import co.ucentral.CreditAplication.models.Cliente;
import co.ucentral.CreditAplication.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class AuthController {

    @Autowired
    ClienteService clientService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Cliente> login(@RequestBody CustomerLoginDto login) {
        Cliente user = clientService.login(login);
        if(user == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(user, HttpStatus.CREATED);
    }
}
