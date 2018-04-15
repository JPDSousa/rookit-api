
package org.rookit.api.dm.artist;

import java.time.LocalDate;
import java.util.Collection;

/**
 * @author Joao
 *
 * @param <T>
 *            return type
 */
@SuppressWarnings("javadoc")
public interface ArtistSetter<T> {

    T addAlias(String alias);

    T addAliases(Collection<String> aliases);

    /**
     * Add the artist passed as parameter to the list of related artists. This
     * operation should me mutual in both artist instance and related artist
     * instance, e.g.:
     * 
     * if one object calls <code> artist1.addRelatedArtist(artist2)</code> it
     * should also call <code> artist2.addRelatedArtist(artist1)</code> in order
     * to create a bidirectional relationship between the two instances.
     * 
     * @param artist
     *            artist to relate this artist with.
     * @return object to return
     */
    T addRelatedArtist(final Artist artist);

    T clearAliases();

    T removeAlias(String alias);

    T removeAliases(Collection<String> aliases);

    T setAliases(Collection<String> aliases);

    T setBeginDate(LocalDate beginDate);

    T setEndDate(LocalDate endDate);

    T setIPI(String ipi);

    /**
     * Sets a new origin for the artist
     * 
     * @param origin
     *            origin to set
     * @return object to return
     */
    T setOrigin(final String origin);

    T setPicture(byte[] picture);
}
