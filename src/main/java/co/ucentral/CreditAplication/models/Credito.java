package co.ucentral.CreditAplication.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CREDITO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Credito {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CREDITO")
    @SequenceGenerator(name = "SEQ_CREDITO", sequenceName = "SEQ_CREDITO", allocationSize = 1)
    @Column(name = "CRE_ID", nullable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name="CRE_CLIENT_ID", nullable=true)
    private Cliente cliente;
}
