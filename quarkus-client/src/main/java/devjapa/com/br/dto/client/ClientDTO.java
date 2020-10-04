package devjapa.com.br.dto.client;

import devjapa.com.br.entities.Client;

import java.time.Instant;

public class ClientDTO {

    public Long id;

    public String name;

    public String cpf;

    public Double income;

    public Instant birthDate;

    public Integer children;

    public ClientDTO() {
    }

    public ClientDTO(Client entity) {
        this.id = entity.id;
        this.name = entity.name;
        this.cpf = entity.cpf;
        this.income = entity.income;
        this.birthDate = entity.birthDate;
        this.children = entity.children;
    }


}
