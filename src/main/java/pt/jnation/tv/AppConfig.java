package pt.jnation.tv;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.Converters;
import io.smallrye.config.WithDefault;

import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@ConfigMapping(prefix = "jnation.tv")
public interface AppConfig {
    Map<Color, RoomConfig> rooms();

    @WithDefault("https://jnation.pt")
    List<URI> media();

    @WithDefault("15s")
    Duration interval();

    interface RoomConfig {
        String name();

        @WithDefault("white")
        String cssColor();

        Optional<URL> stream();
    }

    default RoomConfig getRoom(String color) {
        return rooms().get(Color.getColor(color));
    }

    default Optional<RoomConfig> getRoomByName(String name) {
        for (Map.Entry<Color, RoomConfig> entry : rooms().entrySet()) {
            if (entry.getValue().name().equals(name)) {
                return Optional.of(entry.getValue());
            }
        }
        return Optional.empty();
    }

    default URI nextMedia() {
        return media().get(LocalTime.now().toSecondOfDay() / interval().toSecondsPart() % media().size());
    }

    enum Color {
        RED,
        BLUE,
        GREEN,
        ORANGE,
        PURPLE,
        GREY;

        static Color getColor(final String name) {
            return Converters.getImplicitConverter(Color.class).convert(name);
        }
    }
}
