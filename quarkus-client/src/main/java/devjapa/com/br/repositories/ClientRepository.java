package devjapa.com.br.repositories;

import devjapa.com.br.entities.Client;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ClientRepository implements PanacheRepository<Client> {

    // put your custom logic here as instance methods
}
