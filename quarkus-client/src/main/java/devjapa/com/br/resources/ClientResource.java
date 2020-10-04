package devjapa.com.br.resources;

import devjapa.com.br.dto.client.AddClientDTO;
import devjapa.com.br.dto.client.ClientDTO;
import devjapa.com.br.dto.client.UpdateClientDTO;
import devjapa.com.br.entities.Client;
import devjapa.com.br.repositories.ClientRepository;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("/clients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClientResource {

    private static ArrayList<String> clients = new ArrayList<>();

    static {
        clients.add("DevJapa 0");
    }

    @Inject
    ClientRepository clientRepository;

    @GET
    @Path("/health")
    @Produces(MediaType.TEXT_PLAIN)
    public String health() {
        return "UP";
    }

//    @GET
//    public Response findAll() {
//        return Response.status(200).entity(clientRepository.listAll()).build();
//    }

    @GET
    public List<ClientDTO> findAll() {
        return clientRepository.listAll().stream().map(x -> new ClientDTO(x)).collect(Collectors.toList());
    }

    @GET
    @Path("/{id}")
    public ClientDTO findById(@PathParam("id") Long id) {
        Client client = clientRepository.findById(id);
        // tratar no service id not found
        return new ClientDTO(client);
    }

    @POST
    @Transactional
    public Response insert(AddClientDTO dto) {
        Client client = new Client(dto);
        clientRepository.persist(client);
        return Response.status(Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public void update(@PathParam("id") Long id, UpdateClientDTO dto) {
        Optional<Client> clientOp = clientRepository.findByIdOptional(id);
        if(clientOp.isEmpty()) {
            throw new NotFoundException("Client Not Found");
        }
        Client client = clientOp.get();
        client.name = dto.name;   
        clientRepository.persist(client);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("id") Long id) {
        Optional<Client> clientOp = clientRepository.findByIdOptional(id);

        clientOp.ifPresentOrElse(clientRepository::delete, () -> {
            throw new NotFoundException("Client not found");
        });
    }

}