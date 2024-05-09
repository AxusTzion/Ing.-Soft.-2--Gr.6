package co.ucentral.CreditAplication.models.dtos;

import co.ucentral.CreditAplication.models.enums.UserRole;

public record SignUpDto(
        String login,
        String password,
        UserRole role) {
}
