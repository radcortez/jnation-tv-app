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
        List<Category> categories,
        LocalDateTime startsAt,
        LocalDateTime endsAt,
        List<Speaker> speakers) {

    public Session(String id, String room, RoomCssColor roomCssColor, String title, String description, List<Category> categories, LocalDateTime startsAt, LocalDateTime endsAt, List<Speaker> speakers) {
        this.id = id;
        this.room = room;
        this.roomCssColor = Optional.ofNullable(roomCssColor).orElse(new RoomCssColor());
        this.title = title;
        this.description = description;
        this.categories = categories;
        this.startsAt = startsAt;
        this.endsAt = endsAt;
        this.speakers = speakers;
    }

    public List<String> tags() {
        return categories.stream()
                .filter(category -> category.name().equals("Tags"))
                .flatMap(category -> category.categoryItems().stream().map(Category.Item::name))
                .toList();
    }

    public String level() {
        return categories.stream()
                .filter(category -> category.name().equals("Level"))
                .flatMap(category -> category.categoryItems().stream().map(Category.Item::name))
                .findFirst().orElse("");
    }

    public String format() {
        return categories.stream()
                .filter(category -> category.name().equals("Session format"))
                .flatMap(category -> category.categoryItems().stream().map(Category.Item::name))
                .findFirst().orElse("");
    }
}
