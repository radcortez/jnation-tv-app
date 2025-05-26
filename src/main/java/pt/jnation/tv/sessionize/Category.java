package pt.jnation.tv.sessionize;

import java.util.List;

public record Category(String name, List<Item> categoryItems) {
    public record Item(String name) {}
}
