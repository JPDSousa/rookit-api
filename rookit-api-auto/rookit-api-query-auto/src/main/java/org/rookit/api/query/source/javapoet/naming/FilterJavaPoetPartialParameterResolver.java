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
package org.rookit.api.query.source.javapoet.naming;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableSet;
import com.google.inject.Inject;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeVariableName;
import org.rookit.api.query.source.guice.Filter;
import org.rookit.auto.javapoet.naming.JavaPoetNamingFactory;
import org.rookit.auto.javapoet.naming.JavaPoetParameterResolver;
import org.rookit.auto.javax.element.ExtendedTypeElement;

final class FilterJavaPoetPartialParameterResolver implements JavaPoetParameterResolver {

    private final JavaPoetNamingFactory namingFactory;
    private final TypeVariableName filterType;

    @Inject
    private FilterJavaPoetPartialParameterResolver(@Filter final JavaPoetNamingFactory namingFactory,
                                                   @Filter final TypeVariableName filterType) {
        this.namingFactory = namingFactory;
        this.filterType = filterType;
    }

    @Override
    public TypeName resolveParameters(final ExtendedTypeElement element) {
        final ParameterizedTypeName parameterizedType = ParameterizedTypeName
                .get(this.namingFactory.classNameFor(element), this.filterType);
        return TypeVariableName.get(parameterizedType.toString());
    }

    @Override
    public Iterable<TypeVariableName> createParameters(final ExtendedTypeElement element) {
        final TypeName type = ParameterizedTypeName.get(this.namingFactory.classNameFor(element), this.filterType);
        return ImmutableSet.of(TypeVariableName.get(this.filterType.name + " extends " + type));
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("namingFactory", this.namingFactory)
                .add("filterType", this.filterType)
                .toString();
    }
}
