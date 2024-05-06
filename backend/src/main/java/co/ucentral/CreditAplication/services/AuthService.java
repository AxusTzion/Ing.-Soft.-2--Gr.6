package co.ucentral.CreditAplication.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Value("${admin.username}")
    private  String USER_NAME_CONFIG = "";

    @Value("${admin.password}")
    private String PASSWORD_CONFIG = "";

    public boolean loginAdmin(String userName, String password) {

        return this.USER_NAME_CONFIG.equals(userName) && this.PASSWORD_CONFIG.equals(password);
    }
}
