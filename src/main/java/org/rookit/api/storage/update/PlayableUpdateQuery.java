
package org.rookit.api.storage.update;

import org.rookit.api.dm.play.able.PlayableSetter;

@SuppressWarnings("javadoc")
public interface PlayableUpdateQuery<Q extends PlayableUpdateQuery<Q, S>, S extends PlayableUpdateFilterQuery<S>>
        extends RookitUpdateQuery<S>, PlayableSetter<Q> {

    //

}
