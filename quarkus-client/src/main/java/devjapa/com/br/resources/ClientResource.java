package devjapa.com.br.resources;

import devjapa.com.br.dto.client.AddClientDTO;
import devjapa.com.br.dto.client.ClientDTO;
import devjapa.com.br.dto.client.UpdateClientDTO;
import devjapa.com.br.entities.Client;
import devjapa.com.br.repositories.ClientRepository;
import devjapa.com.br.services.ClientService;
import devjapa.com.br.services.ClienteServiceImpl;

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
    ClientService clientService;

    @GET
    @Path("/health")
    @Produces(MediaType.TEXT_PLAIN)
    public String health() {
        return clientService.health();
    }

//    @GET
//    public Response findAll() {
//        return Response.status(200).entity(clientRepository.listAll()).build();
//    }

    @GET
    public List<ClientDTO> findAll() {
        return clientService.findAll();
    }

    @GET
    @Path("/{id}")
    public ClientDTO findById(@PathParam("id") Long id) {
        // tratar no service id not found
        return clientService.findById(id);
    }

    @POST
    @Transactional
    public Response insert(AddClientDTO dto) {
        clientService.insert(dto);
        return Response.status(Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public void update(@PathParam("id") Long id, UpdateClientDTO dto) {
        clientService.update(id, dto);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("id") Long id) {
        clientService.delete(id);
    }

}