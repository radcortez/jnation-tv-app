package pt.jnation.tv.sessionize;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public record Session(
        String id,
        String room,
        RoomCssColor roomCssColor,
        String title,
        String description,
        LocalDateTime startsAt,
        LocalDateTime endsAt,
        List<Speaker> speakers) {

    public Session(String id, String room, RoomCssColor roomCssColor, String title, String description, LocalDateTime startsAt, LocalDateTime endsAt, List<Speaker> speakers) {
        this.id = id;
        this.room = room;
        this.roomCssColor = Optional.ofNullable(roomCssColor).orElse(new RoomCssColor());
        this.title = title;
        this.description = description;
        this.startsAt = startsAt;
        this.endsAt = endsAt;
        this.speakers = speakers;
    }
}
