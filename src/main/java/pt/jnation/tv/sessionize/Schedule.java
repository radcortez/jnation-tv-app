package pt.jnation.tv.sessionize;

import java.time.LocalDateTime;
import java.util.List;

public record Schedule(
        LocalDateTime date,
        boolean isDefault,
        List<Room> rooms) {
}
