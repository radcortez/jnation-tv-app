package pt.jnation.tv.sessionize;

import java.net.URI;

public record Speaker(
        String id,
        String firstName,
        String lastName,
        String fullName,
        String bio,
        String tagLine,
        URI profilePicture) {
}
