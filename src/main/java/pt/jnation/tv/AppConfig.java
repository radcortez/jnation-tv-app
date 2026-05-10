package pt.jnation.tv;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithDefault;
import io.smallrye.config.WithDefaults;
import io.smallrye.config.WithParentName;
import io.smallrye.config.WithUnnamedKey;

import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@ConfigMapping(prefix = "jnation.tv")
public interface AppConfig {
    @WithDefault("Europe/Lisbon")
    ZoneId zoneId();

    List<RoomConfig> rooms();

    @WithUnnamedKey("default")
    @WithDefaults
    Map<String, MediaConfig> media();

    interface RoomConfig {
        String name();

        String color();

        String cssColor();

        Optional<URL> stream();
    }

    default RoomConfig findRoom(String name) {
        for (RoomConfig room : rooms()) {
            if (room.name().equals(name) || room.color().equals(name)) {
                return room;
            }
        }
        throw new IllegalArgumentException("Room not found: " + name);
    }

    interface MediaConfig {
        @WithDefault("https://jnation.pt")
        @WithParentName
        List<URI> media();

        @WithDefault("15s")
        Duration interval();

        default URI next() {
            return media().get(LocalTime.now().toSecondOfDay() / interval().toSecondsPart() % media().size());
        }
    }
}
