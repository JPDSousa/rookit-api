package org.rookit.api.dm.genre.factory;

import org.rookit.api.dm.factory.RookitFactory;
import org.rookit.api.dm.genre.Genre;

@SuppressWarnings("javadoc")
public interface GenreFactory extends RookitFactory<Genre> {

	Genre createGenre(String name);
}
