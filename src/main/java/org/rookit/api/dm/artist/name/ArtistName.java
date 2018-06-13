package org.rookit.api.dm.artist.name;

import java.util.Collection;

public interface ArtistName extends Comparable<ArtistName> {

    String official();

    Collection<String> aliases();

}
