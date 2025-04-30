package pt.jnation.tv.resource;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.Location;
import io.quarkus.qute.TemplateInstance;
import io.smallrye.common.annotation.Blocking;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import pt.jnation.tv.AppConfig;
import pt.jnation.tv.AppConfig.RoomConfig;

@Path("/stream")
public class StreamResource {
    @Inject
    AppConfig appConfig;

    @GET
    @Path("/{room}")
    @Produces(MediaType.TEXT_HTML)
    @Blocking
    public TemplateInstance get(@PathParam("room") final String room) {
        RoomConfig roomConfig = appConfig.getRoom(room);
        if (roomConfig.stream().isPresent()) {
            return Templates.stream(roomConfig.stream().get().toString());
        }
        return ErrorTemplate.error("No Stream found for Room " + room);
    }

    @CheckedTemplate
    public static class Templates {
        @Location("stream.html")
        public static native TemplateInstance stream(String url);
    }
}
