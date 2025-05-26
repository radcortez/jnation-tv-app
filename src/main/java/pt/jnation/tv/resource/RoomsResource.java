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
import pt.jnation.tv.AppConfig.RoomConfig;
import pt.jnation.tv.sessionize.Room;
import pt.jnation.tv.sessionize.Schedule;
import pt.jnation.tv.sessionize.Session;
import pt.jnation.tv.sessionize.SessionizeClient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Path("/room")
public class RoomsResource {
    @Inject
    AppConfig appConfig;
    @Inject
    @RestClient
    SessionizeClient sessionizeClient;

    @GET
    @Path("/{name}")
    @Produces(MediaType.TEXT_HTML)
    @Blocking
    public TemplateInstance get(@PathParam("name") final String name) {
        return get(name, LocalDate.now());
    }

    @GET
    @Path("/{name}/{date}")
    @Produces(MediaType.TEXT_HTML)
    @Blocking
    public TemplateInstance get(@PathParam("name") final String name, @PathParam("date") final LocalDate date) {
        Optional<Schedule> schedule = sessionizeClient.getSchedule(date);
        if (schedule.isEmpty()) {
            return ErrorTemplate.error("Schedule Not Found!");
        }

        RoomConfig roomConfig = appConfig.findRoom(name);
        Optional<Room> room = schedule.get().rooms()
                .stream()
                .filter(r -> roomConfig.name().equals(r.name()))
                .findFirst();
        if (room.isEmpty()) {
            return ErrorTemplate.error("Room Not Found!");
        }

        List<Session> sessions = room.get().sessions()
                .stream()
                .filter(session -> !session.speakers().isEmpty())
                .sorted(Comparator.comparing(Session::startsAt))
                .toList();
        if (sessions.isEmpty()) {
            return ErrorTemplate.error("Sessions Not Found!");
        }

        LocalDateTime now = LocalDateTime.now()
                .withYear(date.getYear()).withMonth(date.getMonthValue()).withDayOfMonth(date.getDayOfMonth());
        System.out.println("now = " + now);
        for (Session session : sessions) {
            if (now.isBefore(session.endsAt())) {
                return Templates.sessionCard(sessionizeClient.getSessionWithSpeakers(session), roomConfig);
            }
        }

        return ErrorTemplate.error("No more Sessions for Today!");
    }

    @CheckedTemplate
    public static class Templates {
        @Location("session-card.html")
        public static native TemplateInstance sessionCard(Session session, RoomConfig roomConfig);
    }
}
