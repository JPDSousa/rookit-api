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

import java.time.Duration;
import java.time.LocalDate;

@SuppressWarnings("javadoc")
public interface PlayableFilter<Q extends PlayableFilter<Q>> extends RookitFilter<Q> {

    Q lastPlayedAfter(LocalDate date);
    
    Q lastPlayedBefore(LocalDate date);
    
    Q lastSkippedAfter(LocalDate date);

    Q lastSkippedBefore(LocalDate date);
    
    Q playedBetween(long min, long max);

    Q playedLessThan(long plays);
    
    Q playedMoreThan(long plays);
    
    Q skippedBetween(long min, long max);

    Q skippedLessThan(long skipped);
    
    Q skippedMoreThan(long skipped);

    Q withDurationBetween(Duration min, Duration max);
    
    Q withDurationGreaterThan(Duration duration);
    
    Q withDurationSmallerThan(Duration duration);
    
    Q isPlayable(boolean isPlayable);

}
