package devjapa.com.br.services;

import devjapa.com.br.dto.client.AddClientDTO;
import devjapa.com.br.dto.client.ClientDTO;
import devjapa.com.br.dto.client.UpdateClientDTO;
import devjapa.com.br.entities.Client;
import devjapa.com.br.repositories.ClientRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class ClienteServiceImpl implements ClientService{

    @Inject
    ClientRepository clientRepository;

    @Override
    public String health() {
        return "UP";
    }

    @Override
    public List<ClientDTO> findAll() {
        return clientRepository.listAll().stream().map(x -> new ClientDTO(x)).collect(Collectors.toList());
    }

    @Override
    public ClientDTO findById(Long id) {
        Optional<Client> clientOp = clientRepository.findByIdOptional(id);
        Client client = clientOp.orElseThrow(() -> new NotFoundException("Id Not Found"));
        return new ClientDTO(client);
    }

    @Override
    public ClientDTO insert(AddClientDTO dto) {
        Client client = new Client(dto);
        clientRepository.persist(client);
        return new ClientDTO(client);
    }

    @Override
    public void update(Long id, UpdateClientDTO dto) {
        Optional<Client> clientOp = clientRepository.findByIdOptional(id);
        if(clientOp.isEmpty()) {
            throw new NotFoundException("Client Not Found");
        }
        Client client = clientOp.get();
        client.name = dto.name;
        clientRepository.persist(client);

    }

    @Override
    public void delete(Long id) {
        Optional<Client> clientOp = clientRepository.findByIdOptional(id);

        clientOp.ifPresentOrElse(clientRepository::delete, () -> {
            throw new NotFoundException("Client not found");
        });

    }
}
