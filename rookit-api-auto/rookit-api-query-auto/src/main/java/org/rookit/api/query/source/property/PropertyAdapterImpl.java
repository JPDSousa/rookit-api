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
package org.rookit.api.query.source.property;

import com.google.common.base.MoreObjects;
import com.google.inject.Inject;
import org.rookit.auto.javax.element.ElementUtils;
import org.rookit.auto.javax.ExtendedProperty;
import org.rookit.auto.javax.PropertyAdapter;

import javax.lang.model.type.TypeMirror;

final class PropertyAdapterImpl implements PropertyAdapter {

    private final ElementUtils utils;

    @Inject
    private PropertyAdapterImpl(final ElementUtils utils) {
        this.utils = utils;
    }

    @Override
    public ExtendedProperty changeReturnType(final ExtendedProperty source, final TypeMirror newReturnType) {
        return new ReturnProperty(source, newReturnType, this.utils);
    }

    @Override
    public ExtendedProperty changeName(final ExtendedProperty source, final String newName) {
        return new NameProperty(this.utils, newName, source);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("utils", this.utils)
                .toString();
    }
}