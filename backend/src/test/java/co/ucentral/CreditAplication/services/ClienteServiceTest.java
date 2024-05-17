package co.ucentral.CreditAplication.services;

import co.ucentral.CreditAplication.dtos.CustomerLoginDto;
import co.ucentral.CreditAplication.models.Cliente;
import jakarta.persistence.EntityManager;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ClienteServiceTest {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EntityManager entityManager;


    @Test
    public void testSaveCliente() {
        Cliente cliente = new Cliente();
        cliente.setNombre("Juan");
        cliente.setApellido("Perez");
        cliente.setNumeroDeIdentificacion("123456789");

        cliente = clienteService.save(cliente);

        assertNotNull(cliente.getId());

        Cliente savedCliente = entityManager.find(Cliente.class, cliente.getId());
        assertNotNull(savedCliente);
        assertEquals("Juan", savedCliente.getNombre());
    }


    @Test
    public void testGetAllClientes() {
        Cliente cliente1 = new Cliente();
        cliente1.setNombre("Pedro");
        Cliente cliente2 = new Cliente();
        cliente2.setNombre("Ana");
        clienteService.save(cliente1);
        clienteService.save(cliente2);

        List<Cliente> clientes = clienteService.getAll();

        assertEquals(2, clientes.size());
    }

    @Test
    public void testGetClienteById() {
        Cliente cliente = new Cliente();
        cliente.setNombre("Luis");
        cliente = clienteService.save(cliente);

        Optional<Cliente> retrievedCliente = clienteService.getById(cliente.getId());

        assertTrue(retrievedCliente.isPresent());
        assertEquals("Luis", retrievedCliente.get().getNombre());
    }

    @Test
    public void testDeleteCliente() {
        // Create and save a cliente
        Cliente cliente = new Cliente();
        cliente.setNombre("Sofia");
        cliente.setApellido("Martinez");
        cliente = clienteService.save(cliente);

        clienteService.delete(cliente.getId());

        Optional<Cliente> deletedCliente = clienteService.getById(cliente.getId());

        assertFalse(deletedCliente.isPresent());
    }
}