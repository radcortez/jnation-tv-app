package pt.jnation.tv.resource;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
class RoomsResourceTest {
    @Inject
    RoomsResource roomsResource;

    /**
     * Schedule
     * <p>
     * 11.00 - 11.50 - Codepocalypse Now: LangChain4j vs. Spring AI
     * 12.10 - 13.00 - 3 years of Quarkus in production, what have we learned?
     * 14.00 - 14.50 - Green Coding with Spring Boot: Sustainability as a Path to Better Software
     * 15.10 - 16.00 - Static You Can Maintain. Static With a Live CMS… What Is the Missing Roq?
     * 16:30 - 17.20 - The Ultimate Showdown of Database Migration Tools
     * 17:50 - 18.30 - Fun with Flags: How OpenFeature Solves Your Feature Flag Headaches
     */
    @Test
    void session() {
        RoomsResource.SessionResponse session = roomsResource.getSession("Mondego", LocalDateTime.of(2026, 5, 26, 8, 0));
        assertTrue(session.hasSession());
        assertEquals("Codepocalypse Now: LangChain4j vs. Spring AI", session.getSession().title());
        session = roomsResource.getSession("Mondego", LocalDateTime.of(2026, 5, 26, 11, 0));
        assertTrue(session.hasSession());
        assertEquals("Codepocalypse Now: LangChain4j vs. Spring AI", session.getSession().title());
        session = roomsResource.getSession("Mondego", LocalDateTime.of(2026, 5, 26, 11, 49));
        assertTrue(session.hasSession());
        assertEquals("Codepocalypse Now: LangChain4j vs. Spring AI", session.getSession().title());

        // It should change session, at 11.50h,
        // Because we list all sessions in the room, ordered and get the first one which is before the current time
        session = roomsResource.getSession("Mondego", LocalDateTime.of(2026, 5, 26, 11, 50));
        assertTrue(session.hasSession());
        assertEquals("3 years of Quarkus in production, what have we learned?", session.getSession().title());
        session = roomsResource.getSession("Mondego", LocalDateTime.of(2026, 5, 26, 12, 59));
        assertTrue(session.hasSession());
        assertEquals("3 years of Quarkus in production, what have we learned?", session.getSession().title());

        session = roomsResource.getSession("Mondego", LocalDateTime.of(2026, 5, 26, 13, 0));
        assertTrue(session.hasSession());
        assertEquals("Green Coding with Spring Boot: Sustainability as a Path to Better Software", session.getSession().title());
        session = roomsResource.getSession("Mondego", LocalDateTime.of(2026, 5, 26, 13, 49));
        assertTrue(session.hasSession());
        assertEquals("Green Coding with Spring Boot: Sustainability as a Path to Better Software", session.getSession().title());
        session = roomsResource.getSession("Mondego", LocalDateTime.of(2026, 5, 26, 13, 50));
        assertTrue(session.hasSession());
        assertEquals("Green Coding with Spring Boot: Sustainability as a Path to Better Software", session.getSession().title());
        session = roomsResource.getSession("Mondego", LocalDateTime.of(2026, 5, 26, 14, 0));
        assertTrue(session.hasSession());
        assertEquals("Green Coding with Spring Boot: Sustainability as a Path to Better Software", session.getSession().title());
        session = roomsResource.getSession("Mondego", LocalDateTime.of(2026, 5, 26, 14, 49));
        assertTrue(session.hasSession());
        assertEquals("Green Coding with Spring Boot: Sustainability as a Path to Better Software", session.getSession().title());

        session = roomsResource.getSession("Mondego", LocalDateTime.of(2026, 5, 26, 14, 50));
        assertTrue(session.hasSession());
        assertEquals("Static You Can Maintain. Static With a Live CMS… What Is the Missing Roq?", session.getSession().title());
        session = roomsResource.getSession("Mondego", LocalDateTime.of(2026, 5, 26, 15, 59));
        assertTrue(session.hasSession());
        assertEquals("Static You Can Maintain. Static With a Live CMS… What Is the Missing Roq?", session.getSession().title());

        session = roomsResource.getSession("Mondego", LocalDateTime.of(2026, 5, 26, 16, 0));
        assertTrue(session.hasSession());
        assertEquals("The Ultimate Showdown of Database Migration Tools", session.getSession().title());
        session = roomsResource.getSession("Mondego", LocalDateTime.of(2026, 5, 26, 17, 19));
        assertTrue(session.hasSession());
        assertEquals("The Ultimate Showdown of Database Migration Tools", session.getSession().title());

        session = roomsResource.getSession("Mondego", LocalDateTime.of(2026, 5, 26, 17, 20));
        assertTrue(session.hasSession());
        assertEquals("Fun with Flags: How OpenFeature Solves Your Feature Flag Headaches", session.getSession().title());
        session = roomsResource.getSession("Mondego", LocalDateTime.of(2026, 5, 26, 18, 29));
        assertTrue(session.hasSession());
        assertEquals("Fun with Flags: How OpenFeature Solves Your Feature Flag Headaches", session.getSession().title());

        session = roomsResource.getSession("Mondego", LocalDateTime.of(2026, 5, 26, 18, 30));
        assertFalse(session.hasSession());
    }
}