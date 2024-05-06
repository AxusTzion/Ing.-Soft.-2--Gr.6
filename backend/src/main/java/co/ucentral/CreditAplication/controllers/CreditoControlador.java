package co.ucentral.CreditAplication.controllers;

import co.ucentral.CreditAplication.models.Credito;
import co.ucentral.CreditAplication.models.dtos.CreditStatusChangeRequestDto;
import co.ucentral.CreditAplication.services.CreditoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("Credito")
public class CreditoControlador {

    @Autowired
    CreditoService creditService;

    @RequestMapping(value = "/credits", method = RequestMethod.GET)
    public ResponseEntity<Credito> list() {
        List<Credito> users = creditService.getAll();
        return new ResponseEntity(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/non-approved-credits", method = RequestMethod.GET)
    public ResponseEntity<Credito> listPendingCredits() {
        List<Credito> creditoList = creditService.getAllPending();
        return new ResponseEntity(creditoList, HttpStatus.OK);
    }

    @RequestMapping(value = "/credit", method = RequestMethod.GET)
    public ResponseEntity<Credito> creditoPorId(@RequestParam(value = "id") long id) {
        Optional<Credito> creditoOptional = creditService.getById(id);
        if(creditoOptional.isPresent()) {
            return new ResponseEntity(creditoOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/update-state", method = RequestMethod.PUT)
    public ResponseEntity changeStatusCredit(@RequestBody CreditStatusChangeRequestDto creditoCreditStatusChangeRequestDto) {
        creditService.updateCreditState(creditoCreditStatusChangeRequestDto);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Credito> create(@RequestBody Credito credito) {
        Credito userCreated = creditService.save(credito);
        return new ResponseEntity(userCreated, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<Credito> delete(@RequestParam(value = "id") long id) {
        creditService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}

