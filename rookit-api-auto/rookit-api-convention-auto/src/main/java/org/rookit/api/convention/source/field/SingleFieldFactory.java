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

import com.squareup.javapoet.FieldSpec;
import org.rookit.utils.convention.BaseProperty;
import org.rookit.auto.javax.ExtendedProperty;
import org.rookit.auto.javapoet.FieldFactory;

import javax.lang.model.element.Modifier;
import java.util.stream.Stream;

final class SingleFieldFactory implements FieldFactory {

    private static final Modifier[] MODIFIERS = {Modifier.PRIVATE, Modifier.FINAL};

    @Override
    public Stream<FieldSpec> create(final ExtendedProperty property) {
        return Stream.of(FieldSpec.builder(org.rookit.utils.convention.Property.class, property.name(), MODIFIERS)
                .initializer("$T.create($S)", BaseProperty.class, property.name())
                .build());
    }

    @Override
    public boolean isCompatible(final ExtendedProperty property) {
        // all properties are compatible
        return true;
    }
}
