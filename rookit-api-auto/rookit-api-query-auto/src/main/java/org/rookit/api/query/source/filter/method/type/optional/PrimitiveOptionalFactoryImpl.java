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
package org.rookit.api.query.source.filter.method.type.optional;

import com.google.common.base.MoreObjects;
import com.google.inject.Inject;
import com.google.inject.Provider;
import org.rookit.api.query.source.guice.Filter;
import org.rookit.auto.javapoet.MethodFactory;
import org.rookit.api.query.source.method.MethodSpecFactory;
import org.rookit.api.query.source.method.TypeBasedMethodFactory;
import org.rookit.auto.javax.element.ElementUtils;
import org.rookit.utils.guice.Top;
import org.rookit.auto.javax.PropertyAdapter;

import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;

final class PrimitiveOptionalFactoryImpl implements PrimitiveOptionalFactory {

    private final ElementUtils utils;
    private final MethodSpecFactory methodSpecFactory;
    private final Provider<MethodFactory> methodFactory;
    private final PropertyAdapter propertyAdapter;

    @Inject
    private PrimitiveOptionalFactoryImpl(final ElementUtils utils,
                                         @Filter final MethodSpecFactory methodSpecFactory,
                                         @Top final Provider<MethodFactory> methodFactory,
                                         final PropertyAdapter propertyAdapter) {
        this.utils = utils;
        this.methodSpecFactory = methodSpecFactory;
        this.methodFactory = methodFactory;
        this.propertyAdapter = propertyAdapter;
    }

    @Override
    public TypeBasedMethodFactory create(final TypeMirror optionalType, final TypeKind primitiveType) {
        return new OptionalPrimitiveMethodFactory(optionalType,
                primitiveType,
                this.utils,
                this.methodSpecFactory,
                this.methodFactory,
                this.propertyAdapter);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("utils", this.utils)
                .add("methodSpecFactory", this.methodSpecFactory)
                .add("methodFactory", this.methodFactory)
                .add("propertyAdapter", this.propertyAdapter)
                .toString();
    }
}
