package pt.jnation.tv;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithDefault;

import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@ConfigMapping(prefix = "jnation.tv")
public interface AppConfig {
    List<RoomConfig> rooms();

    @WithDefault("https://jnation.pt")
    List<URI> media();

    @WithDefault("https://jnation.pt")
    List<URI> keynote();

    @WithDefault("15s")
    Duration interval();

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

    default URI nextMedia() {
        return media().get(LocalTime.now().toSecondOfDay() / interval().toSecondsPart() % media().size());
    }

    default URI nextKeynote() {
        return keynote().get(LocalTime.now().toSecondOfDay() / interval().toSecondsPart() % keynote().size());
    }
}
