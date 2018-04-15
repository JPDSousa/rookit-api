
package org.rookit.api.storage.queries.filter;

import java.util.regex.Pattern;

import org.bson.types.ObjectId;
import org.rookit.api.dm.artist.Artist;
import org.rookit.api.dm.track.Track;

@SuppressWarnings("javadoc")
public interface TrackFilter<Q extends TrackFilter<Q>> extends GenreableFilter<Q>, AudioFeaturesFilter<Q> {

    Q withExplicitLyrics(boolean explicit);
    
    Q withFeature(Artist artist);

    Q withHiddenTrack(Pattern regex);

    Q withHiddenTrack(String hiddenTrack);

    Q withLyrics(Pattern regex);

    Q withLyrics(String lyrics);
    
    Q withMainArtist(Artist artist);

    Q withOriginal(ObjectId id);

    Q withOriginal(Track track);
    
    Q withPath(boolean isPathPresent);

    Q withProducer(Artist artist);
    
    Q withTitle(Pattern regex);

    Q withTitle(String title);

    Q withVersionArtist(Artist artist);
    
    Q withVersionToken(Pattern regex);

    Q withVersionToken(String token);

}
