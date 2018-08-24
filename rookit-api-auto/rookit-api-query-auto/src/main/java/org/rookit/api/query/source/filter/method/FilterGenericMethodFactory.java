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
package org.rookit.api.query.source.filter.method;

import com.google.common.base.MoreObjects;
import com.google.inject.Inject;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;
import org.rookit.api.query.source.guice.Filter;
import org.rookit.api.query.source.method.MethodSpecFactory;
import org.rookit.auto.javapoet.MethodFactory;
import org.rookit.auto.javax.ExtendedProperty;

import javax.lang.model.util.Types;
import java.util.stream.Stream;

final class FilterGenericMethodFactory implements MethodFactory {

    private final Types typeUtils;
    private final MethodSpecFactory methodSpecFactory;

    @Inject
    private FilterGenericMethodFactory(final Types typeUtils,
                                       @Filter final MethodSpecFactory methodSpecFactory) {
        this.typeUtils = typeUtils;
        this.methodSpecFactory = methodSpecFactory;
    }

    @Override
    public Stream<MethodSpec> create(final ExtendedProperty property) {
        return Stream.of(getGenericMethod(property));
    }

    @Override
    public boolean isCompatible(final ExtendedProperty property) {
        return true;
    }

    private MethodSpec getGenericMethod(final ExtendedProperty property) {
        final String propertyName = property.name();
        final ParameterSpec param = ParameterSpec.builder(TypeName.get(property.type()), propertyName).build();

        return this.methodSpecFactory.create(propertyName, param);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("typeUtils", this.typeUtils)
                .add("methodSpecFactory", this.methodSpecFactory)
                .toString();
    }
}
