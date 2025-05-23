package pt.jnation.tv.resource;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.Location;
import io.quarkus.qute.TemplateInstance;
import io.smallrye.common.annotation.Blocking;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import pt.jnation.tv.AppConfig;

@Path("/keynote")
public class KeynoteResource {
    @Inject
    AppConfig appConfig;

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Blocking
    public TemplateInstance get() {
        return Templates.keynote(appConfig.nextKeynote().toString());
    }

    @CheckedTemplate
    public static class Templates {
        @Location("keynote.html")
        public static native TemplateInstance keynote(String url);
    }
}
