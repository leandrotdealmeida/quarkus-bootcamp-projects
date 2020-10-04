package devjapa.com.br.entities;

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
    public Double income;
    @Column(name = "birth_date", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    public Instant birthDate;
    public Integer children;

}
