/*******************************************************************************
 * Copyright (C) 2017 Joao Sousa
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
package org.rookit.api.dm.track;

/**
 * Interface that stores database field metadata for Tracks
 * 
 * @author Joao
 *
 */
public interface TrackFields {
	
	String HIDDEN_TRACK = "hiddenTrack";
	String TYPE = "type";
	String PATH = "path";
	String TITLE = "title";
	String MAIN_ARTISTS = "mainArtists";
	String FEATURES = "features";
	String PRODUCERS = "producers";
	String VERSION_ARTISTS = "versionArtists";
	String VERSION_TOKEN = "versionToken";
	String LYRICS = "lyrics";
	String ORIGINAL = "original";
	String VERSION_TYPE = "versionType";
	String EXPLICIT = "explicit";
	// Audio features
	String BPM = "bpm";
	String KEY = "trackKey";
	String MODE = "trackMode";
	String INSTRUMENTAL = "instrumental";
	String LIVE = "live";
	String ACOUSTIC = "acoustic";
	String DANCEABILITY = "danceability";
	String ENERGY = "energy";
	String VALENCE = "valence";
	
}