package pt.jnation.tv.resource;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;
import pt.jnation.tv.sessionize.Schedule;
import pt.jnation.tv.sessionize.SessionizeClient;
import pt.jnation.tv.sessionize.Sessions;
import pt.jnation.tv.sessionize.Speaker;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@QuarkusTest
class SessionizeClientTest {
    @Inject
    @RestClient
    SessionizeClient sessionizeClient;

    @Test
    void gridSmart() {
        List<Schedule> schedules = sessionizeClient.gridSmart();
        assertFalse(schedules.isEmpty());
    }

    @Test
    void sessions() {
        List<Sessions> sessions = sessionizeClient.sessions();
        assertFalse(sessions.isEmpty());
    }

    @Test
    void speakers() {
        List<Speaker> speakers = sessionizeClient.speakers();
        assertFalse(speakers.isEmpty());
    }
}
