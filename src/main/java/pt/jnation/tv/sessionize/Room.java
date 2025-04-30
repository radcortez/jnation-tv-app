package pt.jnation.tv.sessionize;

import java.util.List;

public record Room(
        String id,
        String name,
        List<Session> sessions) {
}
