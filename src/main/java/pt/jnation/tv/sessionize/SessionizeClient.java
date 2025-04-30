package pt.jnation.tv.sessionize;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

@RegisterRestClient
public interface SessionizeClient {
    @GET
    @Path("/GridSmart")
    List<Schedule> getSchedules();

    @GET
    @Path("/Sessions")
    List<Sessions> getSessionsX();

    @GET
    @Path("/Speakers")
    List<Speaker> getSpeakers();

    default Optional<Schedule> getSchedule(final LocalDate date) {
        List<Schedule> schedules = getSchedules();

        if (schedules.isEmpty()) {
            return Optional.empty();
        }

        for (Schedule schedule : schedules) {
            if (schedule.date().toLocalDate().equals(date)) {
                return Optional.of(schedule);
            }
        }

        return Optional.empty();
    }

    default List<Speaker> getSpeakers(final Set<String> ids) {
        return getSpeakers().stream().filter(speaker -> ids.contains(speaker.id())).collect(Collectors.toList());
    }

    // The Session response from the Schedule does not contain the full Speaker information (only id and name)
    default Session getSessionWithSpeakers(final Session session) {
        List<Speaker> speakers = getSpeakers(session.speakers().stream().map(Speaker::id).collect(toSet()));
        session.speakers().clear();
        session.speakers().addAll(speakers);
        return session;
    }

    default List<Session> getSessions() {
        return getSessionsX()
                .stream()
                .flatMap(sessions -> sessions.sessions().stream())
                .collect(toList());
    }
}
