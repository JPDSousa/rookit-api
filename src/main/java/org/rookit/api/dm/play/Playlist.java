package org.rookit.api.dm.play;

import static org.rookit.api.dm.play.PlaylistFields.*;

import java.util.stream.Stream;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.IndexOptions;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.utils.IndexType;
import org.rookit.api.bistream.BiStream;
import org.rookit.api.dm.play.able.Playable;
import org.rookit.api.dm.track.Track;

@Entity("Playlist")
@Indexes({
	@Index(fields = {@Field(value = NAME, type = IndexType.ASC)}, 
			options = @IndexOptions(unique = true))
})
@SuppressWarnings("javadoc")
public interface Playlist extends Playable, PlaylistSetter<Void> {

	String IMAGE_BUCKET = "Playlist_Images";

	String getName();
	
	BiStream getImage();
	
	TypePlaylist getType();

}
