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
package org.rookit.api.storage.queries.filter;

import java.time.LocalDate;
import java.util.regex.Pattern;

import org.rookit.api.dm.artist.Artist;
import org.rookit.api.dm.artist.Musician;
import org.rookit.api.dm.artist.TypeArtist;
import org.rookit.api.dm.artist.TypeGender;
import org.rookit.api.dm.artist.TypeGroup;

@SuppressWarnings("javadoc")
public interface ArtistFilter<Q extends ArtistFilter<Q>> extends GenreableFilter<Q> {

    Q relatedTo(Artist artist);
    
    Q withAlias(Pattern regex);

    Q withAlias(String alias);
    
    Q withArtistType(TypeArtist type);

    Q withBeginDate(LocalDate date);

    Q withEndDate(LocalDate date);
    
    Q withFullName(Pattern regex);

    Q withFullName(String fullName);

    Q withGender(TypeGender gender);

    Q withGroupType(TypeGroup type);

    Q withIPI(String ipi);
    
    Q withISNI(String isni);

    Q withMember(Musician member);

    Q withName(Pattern regex);

    Q withName(String artistName);

    Q withOrigin(String origin);

}
