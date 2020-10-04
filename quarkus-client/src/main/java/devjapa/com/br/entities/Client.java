package devjapa.com.br.entities;

import devjapa.com.br.dto.client.AddClientDTO;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "tb_client")
public class Client extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String name;
    public String cpf;
    public Double income;
    @Column(name = "birth_date", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    public Instant birthDate;
    public Integer children;

    public Client() {
    }

    public Client(Client entity) {
        this.name = entity.name;
        this.cpf = entity.cpf;
        this.income = entity.income;
        this.birthDate = entity.birthDate;
        this.children = entity.children;
    }

    public Client(AddClientDTO dto) {
        this.name = dto.name;
        this.cpf = dto.cpf;
        this.income = dto.income;
        this.birthDate = dto.birthDate;
        this.children = dto.children;
    }
}
