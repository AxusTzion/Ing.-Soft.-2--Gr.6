package co.ucentral.CreditAplication.models.dtos;

import co.ucentral.CreditAplication.models.Cliente;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Data
public class ClienteCreateDto {
    private long id;
    private double ingresos;
    private String nombre;
    private String apellido;
    private String numeroTelefonico;
    private Date fechaNacimiento;
    private String direccion;
    private String correoElectronico;
    private String numeroDeIdentificacion;
    private String password;
}
