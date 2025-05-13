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
import org.eclipse.microprofile.rest.client.inject.RestClient;
import pt.jnation.tv.AppConfig;
import pt.jnation.tv.sessionize.Session;
import pt.jnation.tv.sessionize.SessionizeClient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Path("/sessions")
public class SessionsResource {
    @Inject
    AppConfig appConfig;
    @Inject
    @RestClient
    SessionizeClient sessionizeClient;

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Blocking
    public TemplateInstance get() {
        return get(LocalDate.now());
    }

    @GET
    @Path("/{date}")
    @Produces(MediaType.TEXT_HTML)
    @Blocking
    public TemplateInstance get(@PathParam("date") final LocalDate date) {
        LocalDateTime now = LocalDateTime.now()
                .withYear(date.getYear()).withMonth(date.getMonthValue()).withDayOfMonth(date.getDayOfMonth());

        List<Session> sessions = sessionizeClient.getSessions()
                .stream()
                .filter(session -> !session.speakers().isEmpty())
                .filter(session -> session.startsAt() != null && session.endsAt() != null)
                .filter(session -> now.isBefore(session.startsAt()))
                .filter(session -> ChronoUnit.MINUTES.between(now, session.startsAt()) <= 30)
                .toList();

        if (sessions.isEmpty()) {
            return ErrorTemplate.error("No more Sessions for Today!");
        }

        Time time = new Time(
                sessions.stream().map(Session::startsAt).min(LocalDateTime::compareTo).get(),
                sessions.stream().map(Session::endsAt).max(LocalDateTime::compareTo).get());

        for (Session session : sessions) {
            sessionizeClient.getSessionWithSpeakers(session);
            session.roomCssColor().setCss(appConfig.findRoom(session.room()).cssColor());
        }

        return Templates.sessions(time, sessions);
    }

    public record Time(LocalDateTime startsAt, LocalDateTime endsAt) {
    }

    @CheckedTemplate
    public static class Templates {
        @Location("sessions.html")
        public static native TemplateInstance sessions(Time time, List<Session> sessions);
    }
}
