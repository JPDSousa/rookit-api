
package org.rookit.api.dm.genre.factory;

import org.rookit.api.dm.factory.RookitFactory;
import org.rookit.api.dm.genre.Genre;
import org.rookit.api.dm.genre.key.GenreKey;

@SuppressWarnings("javadoc")
public interface GenreFactory extends RookitFactory<Genre, GenreKey> {

    Genre createGenre(String name);
    
}
