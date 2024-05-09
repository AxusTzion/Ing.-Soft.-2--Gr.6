package co.ucentral.CreditAplication.configurations;
import co.ucentral.CreditAplication.models.dtos.SignUpDto;
import co.ucentral.CreditAplication.models.enums.UserRole;
import co.ucentral.CreditAplication.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer implements ApplicationRunner {

    private final AuthService authService;

    @Value("${admin.username}")
    private String ADMIN_USER;

    @Value("${admin.password}")
    private String ADMIN_PASSWORD;

    @Autowired
    public DatabaseInitializer(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (authService.loadUserByUsername("") == null) {
            authService.signUp(new SignUpDto(this.ADMIN_USER, this.ADMIN_PASSWORD, UserRole.ADMIN));
        }
    }
}
