package pt.jnation.tv.resource;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import pt.jnation.tv.sessionize.Session;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
class SessionsResourceTest {
    @Inject
    SessionsResource sessionsResource;

    @Test
    void sessions() {
        List<Session> sessions = sessionsResource.getSessions(LocalDateTime.of(2026, 5, 26, 8, 0));
        assertEquals(1, sessions.size());
        assertTrue(sessions.getFirst().isPlenumSession());
        assertEquals("You, AI, and the Future of Society", sessions.getFirst().title());
        sessions = sessionsResource.getSessions(LocalDateTime.of(2026, 5, 26, 9, 30));
        assertEquals(1, sessions.size());
        assertTrue(sessions.getFirst().isPlenumSession());
        assertEquals("You, AI, and the Future of Society", sessions.getFirst().title());
        sessions = sessionsResource.getSessions(LocalDateTime.of(2026, 5, 26, 10, 29));
        assertEquals(1, sessions.size());
        assertTrue(sessions.getFirst().isPlenumSession());
        assertEquals("You, AI, and the Future of Society", sessions.getFirst().title());

        sessions = sessionsResource.getSessions(LocalDateTime.of(2026, 5, 26, 10, 30));
        assertEquals(6, sessions.size());
        assertEquals("Scotty, I need Warp Speed - Ways to improve JVM startup", sessions.get(0).title());
        assertEquals("How I Made Immer Twice as Fast: Performance Optimization in Practice", sessions.get(1).title());
        assertEquals("Codepocalypse Now: LangChain4j vs. Spring AI", sessions.get(2).title());
        assertEquals(" Tour of Agent Protocols: MCP, A2A, AG-UI, A2UI", sessions.get(3).title());
        assertEquals("From Theory to Practice: Real-World Lessons in Post-Quantum Cryptography Migration", sessions.get(4).title());
        assertEquals("WebAssembly and the Future of the JVM Ecosystem", sessions.get(5).title());
        sessions = sessionsResource.getSessions(LocalDateTime.of(2026, 5, 26, 11, 49));
        assertEquals(6, sessions.size());
        assertEquals("Scotty, I need Warp Speed - Ways to improve JVM startup", sessions.get(0).title());
        assertEquals("How I Made Immer Twice as Fast: Performance Optimization in Practice", sessions.get(1).title());
        assertEquals("Codepocalypse Now: LangChain4j vs. Spring AI", sessions.get(2).title());
        assertEquals(" Tour of Agent Protocols: MCP, A2A, AG-UI, A2UI", sessions.get(3).title());
        assertEquals("From Theory to Practice: Real-World Lessons in Post-Quantum Cryptography Migration", sessions.get(4).title());
        assertEquals("WebAssembly and the Future of the JVM Ecosystem", sessions.get(5).title());

        sessions = sessionsResource.getSessions(LocalDateTime.of(2026, 5, 26, 11, 50));
        assertEquals(6, sessions.size());
        assertEquals("Bootiful Spring Boot 4", sessions.get(0).title());
        assertEquals("(Re)building a Framework: Lessons, Community, and Impostor Syndrome", sessions.get(1).title());
        assertEquals("3 years of Quarkus in production, what have we learned?", sessions.get(2).title());
        assertEquals("Choose your own adventure in agentic design patterns", sessions.get(3).title());
        assertEquals("Be more productive with IntelliJ IDEA", sessions.get(4).title());
        assertEquals("FinOps at scale: Lessons from scaling Kubernetes workloads", sessions.get(5).title());
        sessions = sessionsResource.getSessions(LocalDateTime.of(2026, 5, 26, 12, 34));
        assertEquals(6, sessions.size());
        assertEquals("Bootiful Spring Boot 4", sessions.get(0).title());
        assertEquals("(Re)building a Framework: Lessons, Community, and Impostor Syndrome", sessions.get(1).title());
        assertEquals("3 years of Quarkus in production, what have we learned?", sessions.get(2).title());
        assertEquals("Choose your own adventure in agentic design patterns", sessions.get(3).title());
        assertEquals("Be more productive with IntelliJ IDEA", sessions.get(4).title());
        assertEquals("FinOps at scale: Lessons from scaling Kubernetes workloads", sessions.get(5).title());

        sessions = sessionsResource.getSessions(LocalDateTime.of(2026, 5, 26, 12, 35));
        assertEquals(6, sessions.size());
        assertEquals("Bootiful Spring Boot 4", sessions.get(0).title());
        assertEquals("Orchestrating payments for the millions", sessions.get(1).title());
        assertEquals("3 years of Quarkus in production, what have we learned?", sessions.get(2).title());
        assertEquals("Choose your own adventure in agentic design patterns", sessions.get(3).title());
        assertEquals("Be more productive with IntelliJ IDEA", sessions.get(4).title());
        assertEquals("FinOps at scale: Lessons from scaling Kubernetes workloads", sessions.get(5).title());
        sessions = sessionsResource.getSessions(LocalDateTime.of(2026, 5, 26, 12, 59));
        assertEquals(6, sessions.size());
        assertEquals("Bootiful Spring Boot 4", sessions.get(0).title());
        assertEquals("Orchestrating payments for the millions", sessions.get(1).title());
        assertEquals("3 years of Quarkus in production, what have we learned?", sessions.get(2).title());
        assertEquals("Choose your own adventure in agentic design patterns", sessions.get(3).title());
        assertEquals("Be more productive with IntelliJ IDEA", sessions.get(4).title());
        assertEquals("FinOps at scale: Lessons from scaling Kubernetes workloads", sessions.get(5).title());

        sessions = sessionsResource.getSessions(LocalDateTime.of(2026, 5, 26, 13, 0));
        assertEquals(6, sessions.size());
        assertEquals("When a Java Programmer Meets C", sessions.get(0).title());
        assertEquals("How to not use AI for profit", sessions.get(1).title());
        assertEquals("Green Coding with Spring Boot: Sustainability as a Path to Better Software", sessions.get(2).title());
        assertEquals("AI agents won’t save your productivity – human–agent collaboration will", sessions.get(3).title());
        assertEquals("Observability’s Sixth Sense: Detecting Anomalies in Metrics", sessions.get(4).title());
        assertEquals("ToDo: Test Driven Development and Accessibility", sessions.get(5).title());
        sessions = sessionsResource.getSessions(LocalDateTime.of(2026, 5, 26, 14, 49));
        assertEquals(6, sessions.size());
        assertEquals("When a Java Programmer Meets C", sessions.get(0).title());
        assertEquals("Why can't all apps be web apps?", sessions.get(1).title());
        assertEquals("Green Coding with Spring Boot: Sustainability as a Path to Better Software", sessions.get(2).title());
        assertEquals("AI agents won’t save your productivity – human–agent collaboration will", sessions.get(3).title());
        assertEquals("Observability’s Sixth Sense: Detecting Anomalies in Metrics", sessions.get(4).title());
        assertEquals("ToDo: Test Driven Development and Accessibility", sessions.get(5).title());

        sessions = sessionsResource.getSessions(LocalDateTime.of(2026, 5, 26, 18, 29));
        // 4 - Because there are 2 missing sessions in this slot
        assertEquals(4, sessions.size());

        sessions = sessionsResource.getSessions(LocalDateTime.of(2026, 5, 26, 18, 30));
        assertEquals(0, sessions.size());
    }
}
