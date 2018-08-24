package org.rookit.api.dm.artist.name;

import org.rookit.utils.convention.annotation.Property;
import org.rookit.utils.convention.annotation.PropertyContainer;

import java.util.Collection;

@PropertyContainer
public interface ArtistName extends Comparable<ArtistName> {

    @Property
    String official();

    @Property
    Collection<String> aliases();

}
