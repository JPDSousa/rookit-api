
package org.rookit.api.storage.queries.filter;

import java.time.LocalDate;
import java.util.regex.Pattern;

import org.rookit.api.dm.artist.Artist;
import org.rookit.api.dm.artist.Musician;
import org.rookit.api.dm.artist.TypeArtist;
import org.rookit.api.dm.artist.TypeGender;
import org.rookit.api.dm.artist.TypeGroup;

@SuppressWarnings("javadoc")
public interface ArtistFilter<Q extends ArtistFilter<Q>> extends GenreableFilter<Q> {

    Q relatedTo(Artist artist);
    
    Q withAlias(Pattern regex);

    Q withAlias(String alias);
    
    Q withArtistType(TypeArtist type);

    Q withBeginDate(LocalDate date);

    Q withEndDate(LocalDate date);
    
    Q withFullName(Pattern regex);

    Q withFullName(String fullName);

    Q withGender(TypeGender gender);

    Q withGroupType(TypeGroup type);

    Q withIPI(String ipi);
    
    Q withISNI(String isni);

    Q withMember(Musician member);

    Q withName(Pattern regex);

    Q withName(String artistName);

    Q withOrigin(String origin);

}
