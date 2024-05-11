package co.ucentral.CreditAplication.controllers;
import co.ucentral.CreditAplication.models.Pagos;
import co.ucentral.CreditAplication.services.PagosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("Pagos")
public class PagosControlador {

    @Autowired
    PagosService paymentsService;

    @RequestMapping(value = "/payments", method = RequestMethod.GET)
    public ResponseEntity<Pagos> list() {
        List<Pagos> users = paymentsService.getAll();
        return new ResponseEntity(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/payment", method = RequestMethod.GET)
    public ResponseEntity<Pagos> PagosPorId(@RequestParam(value = "id") long id) {
        Optional<Pagos> PagosOptional = paymentsService.getById(id);
        if(PagosOptional.isPresent()) {
            return new ResponseEntity(PagosOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/payment-by-client", method = RequestMethod.GET)
    public ResponseEntity<List<Pagos>> PagosPorClientId(@RequestParam(value = "id") String id) {
        List<Pagos> pagos = paymentsService.getAllByCliente(id);
        return new ResponseEntity(pagos, HttpStatus.OK);
    }


    @RequestMapping(value = "/payments", method = RequestMethod.POST)
    public ResponseEntity<Pagos> create(@RequestBody Pagos Pagos) {
        Pagos userCreated = paymentsService.save(Pagos);
        return new ResponseEntity(userCreated, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/payments", method = RequestMethod.DELETE)
    public ResponseEntity<Pagos> delete(@RequestParam(value = "id") long id) {
        paymentsService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}

