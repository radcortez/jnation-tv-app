package pt.jnation.tv;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithDefault;
import io.smallrye.config.WithDefaults;

import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@ConfigMapping(prefix = "jnation.tv")
public interface AppConfig {
    @WithDefaults
    Map<String, RoomConfig> rooms();

    @WithDefault("https://jnation.pt")
    List<URI> media();

    @WithDefault("https://jnation.pt")
    List<URI> keynote();

    @WithDefault("15s")
    Duration interval();

    interface RoomConfig {
        @WithDefault("White")
        String name();

        @WithDefault("white")
        String cssColor();

        Optional<URL> stream();
    }

    default RoomConfig findRoom(String name) {
        for (Map.Entry<String, RoomConfig> entry : rooms().entrySet()) {
            if (entry.getValue().name().equals(name)) {
                return entry.getValue();
            }
        }
        return rooms().get(name);
    }

    default URI nextMedia() {
        return media().get(LocalTime.now().toSecondOfDay() / interval().toSecondsPart() % media().size());
    }

    default URI nextKeynote() {
        return keynote().get(LocalTime.now().toSecondOfDay() / interval().toSecondsPart() % keynote().size());
    }
}
