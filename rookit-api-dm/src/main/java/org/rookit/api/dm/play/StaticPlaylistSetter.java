package org.rookit.api.dm.play;

import org.rookit.api.dm.track.Track;

import java.util.Collection;

public interface StaticPlaylistSetter<T> {

    boolean addTrack(Track track);

    boolean addTracks(Collection<? extends Track> tracks);

    T clear();

    boolean removeTrack(Track o);

    boolean removeTracks(Collection<? extends Track> tracks);
}
