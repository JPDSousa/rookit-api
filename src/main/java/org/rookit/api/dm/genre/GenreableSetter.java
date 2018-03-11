package org.rookit.api.dm.genre;

import java.util.Collection;

@SuppressWarnings("javadoc")
public interface GenreableSetter<T> {
	
	T addGenre(Genre genre);
	T addGenres(Collection<Genre> genres);
	T setGenres(Collection<Genre> genres);
	T removeGenre(Genre genre);
	T removeGenres(Collection<Genre> genres);
	T clearGenres();

}
