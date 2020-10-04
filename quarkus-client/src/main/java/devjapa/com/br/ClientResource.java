package devjapa.com.br;

import io.netty.util.internal.StringUtil;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/clients")
public class ClientResource {

    private static ArrayList<String> clients = new ArrayList<>();

    static {
        clients.add("DevJapa 0");
    }

    @GET
    @Path("/health")
    @Produces(MediaType.TEXT_PLAIN)
    public String health() {
        return "UP";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String findAll() {
        return StringUtil.join(",", clients).toString();
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