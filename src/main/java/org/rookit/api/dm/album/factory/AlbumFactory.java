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
package org.rookit.api.dm.album.factory;

import org.rookit.api.dm.album.Album;
import org.rookit.api.dm.album.key.AlbumKey;
import org.rookit.api.dm.factory.RookitFactory;

@SuppressWarnings("javadoc")
public interface AlbumFactory extends RookitFactory<Album, AlbumKey> {

    /**
     * Creates a new album with the specified type. The album will then be set
     * with the specified title, release type and artists.
     *
     * If any of these last three parameters is irrelevant to the album (e.g. VA
     * album has no main artists) the correspondent parameter can be set to
     * null.
     * @param albumKey TODO
     *
     * @return a newly created Album object, with the parameters passed by
     *         parameter.
     */
    Album createSingleArtistAlbum(AlbumKey albumKey);
    
    Album createVariousArtistsAlbum(AlbumKey albumKey);

}
