package org.rookit.api.dm.track;

import java.util.Collection;

import org.rookit.api.dm.artist.Artist;

@SuppressWarnings("javadoc")
public interface TrackSetter<T> {

	T setTitle(String title);
	T setTitle(TrackTitle title);

	T setMainArtists(Collection<Artist> artists);
	T addMainArtist(Artist artist);
	T addMainArtists(Collection<Artist> artists);
	T removeMainArtist(Artist artist);
	T removeMainArtists(Collection<Artist> artists);

	T setFeatures(Collection<Artist> features);
	T addFeature(Artist artist);
	T addFeatures(Collection<Artist> features);
	T removeFeature(Artist artist);
	T removeFeatures(Collection<Artist> features);
	T clearFeatures();

	T setHiddenTrack(String hiddenTrack);
	T resetHiddenTrack();

	T addProducer(Artist producer);
	T addProducers(Collection<Artist> producers);
	T setProducers(Collection<Artist> producer);
	T removeProducer(Artist producer);
	T removeProducers(Collection<Artist> producers);
	T clearProducers();
	
	T setLyrics(String lyrics);

	T setExplicit(boolean explicit);

}
