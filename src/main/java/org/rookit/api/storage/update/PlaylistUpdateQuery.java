
package org.rookit.api.storage.update;

import org.rookit.api.dm.play.PlaylistSetter;
import org.rookit.api.dm.track.Track;

@SuppressWarnings("javadoc")
public interface PlaylistUpdateQuery extends PlayableUpdateQuery<PlaylistUpdateQuery, PlaylistUpdateFilterQuery>,
        PlaylistSetter<PlaylistUpdateQuery> {

    PlaylistUpdateQuery add(Track track);

}
