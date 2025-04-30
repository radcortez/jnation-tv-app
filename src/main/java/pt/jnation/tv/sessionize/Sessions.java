package pt.jnation.tv.sessionize;

import java.util.List;

public record Sessions(
        String groupId,
        String groupName,
        List<Session> sessions) {
}
