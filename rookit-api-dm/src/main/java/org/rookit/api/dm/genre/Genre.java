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
package org.rookit.api.dm.genre;

import org.rookit.utils.convention.annotation.Entity;
import org.rookit.utils.convention.annotation.Property;
import org.rookit.api.dm.play.able.Playable;
import org.rookit.utils.OptionalUtils;

import java.util.Optional;

@SuppressWarnings("javadoc")
@Entity
public interface Genre extends Playable, Comparable<Genre>, GenreSetter<Void> {

    @Override
    default int compareTo(final Genre o) {
        final int name = name().compareTo(o.name());
        return (name == 0) ? OptionalUtils.compare(id(), o.id()) : name;
    }

    @Property
    Optional<String> description();

    @Property
    String name();

}
