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

@Path("/media")
public class MediaResource {
    @Inject
    AppConfig appConfig;

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Blocking
    public TemplateInstance get() {
        return Templates.media(appConfig.media().get("default").next().toString());
    }

    @GET
    @Path("/{type}")
    @Produces(MediaType.TEXT_HTML)
    @Blocking
    public TemplateInstance get(@PathParam("type") final String type) {
        return Templates.media(appConfig.media().get(type).next().toString());
    }

    @CheckedTemplate
    public static class Templates {
        @Location("media.html")
        public static native TemplateInstance media(String url);
    }
}
