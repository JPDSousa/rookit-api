/*******************************************************************************
 * Copyright (C) 2018 Joao Sousa
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 ******************************************************************************/
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
