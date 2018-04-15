/*******************************************************************************
 * Copyright (C) 2018 Joao Sousa
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 ******************************************************************************/
package org.rookit.api.storage;

import java.io.Closeable;
import java.util.stream.Stream;

import org.rookit.api.dm.album.Album;
import org.rookit.api.dm.artist.Artist;
import org.rookit.api.dm.factory.RookitFactories;
import org.rookit.api.dm.genre.Genre;
import org.rookit.api.dm.parser.IgnoredField;
import org.rookit.api.dm.parser.TrackFormat;
import org.rookit.api.dm.play.Playlist;
import org.rookit.api.dm.track.Track;
import org.rookit.api.storage.queries.AlbumQuery;
import org.rookit.api.storage.queries.ArtistQuery;
import org.rookit.api.storage.queries.GenreQuery;
import org.rookit.api.storage.queries.PlaylistQuery;
import org.rookit.api.storage.queries.TrackQuery;
import org.rookit.api.storage.update.AlbumUpdateQuery;
import org.rookit.api.storage.update.ArtistUpdateQuery;
import org.rookit.api.storage.update.GenreUpdateQuery;
import org.rookit.api.storage.update.PlaylistUpdateQuery;
import org.rookit.api.storage.update.TrackUpdateQuery;
import org.rookit.api.storage.utils.Order;

@SuppressWarnings("javadoc")
public interface StorageManager extends Closeable {

    void addAlbum(Album album);

    void addArtist(Artist artist);

    void addGenre(Genre genre);
    
    void addPlaylist(Playlist playlist);

    void addTrack(Track track);
    
    void clear();
    
    AlbumQuery getAlbums();
    
    ArtistQuery getArtists();

    RookitFactories getFactories();
    
    GenreQuery getGenres();
    
    int getIgnoredOccurrences(String value);
    
    String getName();
    
    PlaylistQuery getPlaylists();
    
    int getTrackFormatOccurrences(String value);
    
    TrackQuery getTracks();
    
    void init();
    
    Order newOrder();
    
    void replaceAlbum(Album album);
    
    void replaceArtist(Artist artist);

    void replaceGenre(Genre genre);
    
    void replacePlaylist(Playlist playlist);
    
    void replaceTrack(Track track);
    
    void reset();
    
    Stream<String> streamTrackFormats();

    AlbumUpdateQuery updateAlbum();

    ArtistUpdateQuery updateArtist();
    
    GenreUpdateQuery updateGenre();
    
    void updateIgnored(IgnoredField value);

    PlaylistUpdateQuery updatePlaylist();

    TrackUpdateQuery updateTrack();

    void updateTrackFormat(TrackFormat value);
}
