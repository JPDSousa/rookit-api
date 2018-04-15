
package org.rookit.api.storage.update;

import org.rookit.api.dm.track.TrackSetter;
import org.rookit.api.dm.track.audio.AudioFeatureSetter;
import org.rookit.api.dm.track.key.TrackKeySetter;

@SuppressWarnings("javadoc")
public interface TrackUpdateQuery extends GenreableUpdateQuery<TrackUpdateQuery, TrackUpdateFilterQuery>,
        TrackSetter<TrackUpdateQuery>, TrackKeySetter<TrackUpdateQuery>, AudioFeatureSetter<TrackUpdateQuery> {

    //

}
