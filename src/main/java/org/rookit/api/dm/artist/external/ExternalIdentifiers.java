package org.rookit.api.dm.artist.external;

import java.util.Optional;

public interface ExternalIdentifiers {

    Optional<String> getIPI();

    String getISNI();
}
