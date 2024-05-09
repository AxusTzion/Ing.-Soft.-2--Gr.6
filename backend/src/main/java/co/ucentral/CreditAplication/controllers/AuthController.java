package co.ucentral.CreditAplication.controllers;

import co.ucentral.CreditAplication.dtos.CustomerLoginDto;
import co.ucentral.CreditAplication.models.Cliente;
import co.ucentral.CreditAplication.models.User;
import co.ucentral.CreditAplication.models.dtos.SignUpClientDto;
import co.ucentral.CreditAplication.models.dtos.SignUpDto;
import co.ucentral.CreditAplication.models.enums.JwtDto;
import co.ucentral.CreditAplication.models.enums.SignInDto;
import co.ucentral.CreditAplication.models.enums.UserRole;
import co.ucentral.CreditAplication.models.excetions.InvalidJwtException;
import co.ucentral.CreditAplication.services.AuthService;
import co.ucentral.CreditAplication.services.ClienteService;
import co.ucentral.CreditAplication.utils.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AuthService service;
    @Autowired
    private TokenProvider tokenService;

/*    @PostMapping("/signup")
    public ResponseEntity<?> signUpClient(@RequestBody SignUpClientDto data) throws InvalidJwtException {
        service.signUp(new SignUpDto(data.login(), data.password(), UserRole.USER));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }*/

    @PostMapping("/signin")
    public ResponseEntity<JwtDto> signIn(@RequestBody SignInDto data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var authUser = authenticationManager.authenticate(usernamePassword);
        var accessToken = tokenService.generateAccessToken((User) authUser.getPrincipal());
        return ResponseEntity.ok(new JwtDto(accessToken));
    }
}
