package pt.jnation.tv.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Path("/")
public class SessionizeResource {
    @GET
    @Path("/GridSmart")
    @Produces(MediaType.APPLICATION_JSON)
    public Response gridSmart() throws Exception {
        return Response.ok(loadResponse("GridSmart.json")).build();
    }

    @GET
    @Path("/Sessions")
    @Produces(MediaType.APPLICATION_JSON)
    public Response sessions() throws Exception {
        return Response.ok(loadResponse("Sessions.json")).build();
    }

    @GET
    @Path("/Speakers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response speakers() throws Exception {
        return Response.ok(loadResponse("Speakers.json")).build();
    }

    private static String loadResponse(String fileName) throws IOException {
        URL resource = Thread.currentThread().getContextClassLoader().getResource(fileName);
        assert resource != null;
        try (InputStream inputStream = resource.openStream()) {
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        }
    }
}
