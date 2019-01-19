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
package org.rookit.api.query.source.method.update.type;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.rookit.api.query.guice.PartialUpdate;
import org.rookit.api.query.guice.TopUpdate;
import org.rookit.auto.javapoet.method.MethodFactory;
import org.rookit.auto.javapoet.method.MethodSpecFactory;
import org.rookit.auto.javapoet.method.TypeBasedMethodFactory;
import org.rookit.auto.javax.PropertyAdapter;
import org.rookit.auto.javax.element.ElementUtils;

import javax.lang.model.type.TypeKind;

final class OptionalIntMethodFactoryProvider implements Provider<TypeBasedMethodFactory> {

    private final ElementUtils utils;
    private final PropertyAdapter adapter;
    private final Provider<MethodFactory> factory;
    private final MethodSpecFactory methodSpecFactory;

    @Inject
    private OptionalIntMethodFactoryProvider(final ElementUtils utils,
                                             final PropertyAdapter adapter,
                                             @TopUpdate final Provider<MethodFactory> factory,
                                             @PartialUpdate final MethodSpecFactory methodSpecFactory) {
        this.utils = utils;
        this.adapter = adapter;
        this.factory = factory;
        this.methodSpecFactory = methodSpecFactory;
    }

    @Override
    public TypeBasedMethodFactory get() {
        return new OptionalPrimitiveMethodFactory(this.utils,
                this.methodSpecFactory,
                this.factory,
                this.adapter,
                TypeKind.INT);
    }

    @Override
    public String toString() {
        return "OptionalBooleanMethodFactoryProvider{" +
                "utils=" + this.utils +
                ", adapter=" + this.adapter +
                ", factory=" + this.factory +
                ", methodSpecFactory=" + this.methodSpecFactory +
                "}";
    }
}