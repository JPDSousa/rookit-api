package org.rookit.api.storage.update;

import org.rookit.api.storage.queries.filter.PlayableFilter;

@SuppressWarnings("javadoc")
public interface PlayableUpdateFilterQuery<Q extends PlayableUpdateFilterQuery<Q>> 
	extends PlayableFilter<Q>, RookitUpdateFilterQuery<Q> {
	
	//

}
