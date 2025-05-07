package pt.jnation.tv.sessionize;

import io.quarkus.cache.CacheInvalidateAll;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/sessionize")
public class SessionizeResource {
    @GET
    @Path("/cache/clear")
    @CacheInvalidateAll(cacheName = "gridSmart")
    @CacheInvalidateAll(cacheName = "sessions")
    @CacheInvalidateAll(cacheName = "speakers")
    public Response clearCache() {
        return Response.ok().build();
    }
}
