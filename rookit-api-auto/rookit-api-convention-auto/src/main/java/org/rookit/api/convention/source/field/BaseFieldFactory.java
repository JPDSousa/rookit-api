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
package org.rookit.api.convention.source.field;

import com.google.common.base.MoreObjects;
import com.google.inject.Inject;
import com.squareup.javapoet.FieldSpec;
import org.rookit.auto.javax.ExtendedProperty;
import org.rookit.utils.guice.Multi;
import org.rookit.utils.guice.Single;
import org.rookit.auto.javapoet.FieldFactory;

import java.util.stream.Stream;

final class BaseFieldFactory implements FieldFactory {

    private final FieldFactory singleFieldFactory;
    private final FieldFactory multiFieldFactory;

    @Inject
    private BaseFieldFactory(@Single final FieldFactory singleFieldFactory,
                             @Multi final FieldFactory multiFieldFactory) {
        this.singleFieldFactory = singleFieldFactory;
        this.multiFieldFactory = multiFieldFactory;
    }

    @Override
    public Stream<FieldSpec> create(final ExtendedProperty property) {
        return this.multiFieldFactory.isCompatible(property)
                ? this.multiFieldFactory.create(property)
                : this.singleFieldFactory.create(property);
    }

    @Override
    public boolean isCompatible(final ExtendedProperty property) {
        return this.singleFieldFactory.isCompatible(property) || this.multiFieldFactory.isCompatible(property);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("singleFieldFactory", this.singleFieldFactory)
                .add("multiFieldFactory", this.multiFieldFactory)
                .toString();
    }
}
