
package org.rookit.api.dm.track;

import java.util.Collection;

import org.rookit.api.dm.artist.Artist;

@SuppressWarnings("javadoc")
public interface TrackSetter<T> {

    T addFeature(Artist artist);

    T addFeatures(Collection<Artist> features);
    
    T addProducer(Artist producer);
    
    T addProducers(Collection<Artist> producers);
    
    T clearFeatures();
    
    T clearProducers();
    
    T removeFeature(Artist artist);

    T removeFeatures(Collection<Artist> features);
    
    T removeProducer(Artist producer);

    T removeProducers(Collection<Artist> producers);
    
    T resetHiddenTrack();
    
    T setAudioContent(byte[] audioContent);
    
    T setExplicit(boolean explicit);
    
    T setFeatures(Collection<Artist> features);
    
    T setHiddenTrack(String hiddenTrack);

    T setLyrics(String lyrics);

    T setProducers(Collection<Artist> producers);

}
