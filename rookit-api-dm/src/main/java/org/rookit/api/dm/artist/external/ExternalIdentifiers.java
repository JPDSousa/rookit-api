package org.rookit.api.dm.artist.external;

import org.rookit.utils.convention.annotation.Property;
import org.rookit.utils.convention.annotation.PropertyContainer;

import java.util.Optional;

@PropertyContainer
public interface ExternalIdentifiers {

    @Property
    Optional<String> ipi();

    @Property
    String isni();
}
