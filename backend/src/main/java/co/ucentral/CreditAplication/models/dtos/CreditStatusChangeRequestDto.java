package co.ucentral.CreditAplication.models.dtos;

import lombok.Data;

@Data
public class CreditStatusChangeRequestDto {
    public long id;
    public Boolean isApproved;
}
