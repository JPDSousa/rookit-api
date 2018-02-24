package org.rookit.api.storage.update;

import org.rookit.api.dm.genre.GenreableSetter;

@SuppressWarnings("javadoc")
public interface GenreableUpdateQuery<Q extends GenreableUpdateQuery<Q, S>, S extends GenreableUpdateFilterQuery<S>> extends PlayableUpdateQuery<Q, S>, GenreableSetter<Q> {

	//
	
}
