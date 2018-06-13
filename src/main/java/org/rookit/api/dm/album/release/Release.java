package org.rookit.api.dm.album.release;

import org.immutables.value.Value;
import org.rookit.api.dm.album.TypeRelease;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Optional;

@Value.Immutable
public interface Release extends Serializable {

    TypeRelease type();

    Optional<LocalDate> date();

}
