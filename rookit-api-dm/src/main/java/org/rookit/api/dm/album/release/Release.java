package org.rookit.api.dm.album.release;

import org.immutables.value.Value;
import org.rookit.utils.convention.annotation.Property;
import org.rookit.api.dm.album.TypeRelease;
import org.rookit.utils.convention.annotation.PropertyContainer;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Optional;

@PropertyContainer
@Value.Immutable
@Value.Style(canBuild = "isBuildable")
public interface Release extends Serializable {

    @Property
    TypeRelease type();

    @Property
    Optional<LocalDate> date();

}
