package devjapa.com.br.resources;

import devjapa.com.br.entities.Client;
import devjapa.com.br.repositories.ClientRepository;
import io.netty.util.internal.StringUtil;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    @GET
    public Collection<Client> findAll() {
        return clientRepository.listAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String insert(String client) {
        clients.add(client);
        return client;
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String update(@PathParam("id") Integer index, String client) {
        clients.remove((int) index);
        clients.add(index, client);
        return client;
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String delete(@PathParam("id") Integer index) {
        return clients.remove((int) index);
    }

}