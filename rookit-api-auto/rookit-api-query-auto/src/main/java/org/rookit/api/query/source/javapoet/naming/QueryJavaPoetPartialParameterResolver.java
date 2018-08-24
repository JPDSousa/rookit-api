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
import org.rookit.api.query.source.guice.ElementQuery;
import org.rookit.api.query.source.guice.Query;
import org.rookit.auto.javapoet.naming.JavaPoetNamingFactory;
import org.rookit.auto.javapoet.naming.JavaPoetParameterResolver;
import org.rookit.auto.javax.element.ExtendedTypeElement;

final class QueryJavaPoetPartialParameterResolver implements JavaPoetParameterResolver {

    private final TypeVariableName selfType;
    private final TypeVariableName elementType;
    private final JavaPoetNamingFactory namingFactory;

    @Inject
    private QueryJavaPoetPartialParameterResolver(@Query final TypeVariableName selfType,
                                                  @ElementQuery final TypeVariableName elementType,
                                                  @Query final JavaPoetNamingFactory namingFactory) {
        this.selfType = selfType;
        this.elementType = elementType;
        this.namingFactory = namingFactory;
    }

    @Override
    public TypeName resolveParameters(final ExtendedTypeElement element) {
        final ParameterizedTypeName params = ParameterizedTypeName.get(this.namingFactory.classNameFor(element),
                this.elementType,
                this.selfType);
        return TypeVariableName.get(params.toString());
    }

    @Override
    public Iterable<TypeVariableName> createParameters(final ExtendedTypeElement element) {
        final TypeName typeName = ParameterizedTypeName.get(this.namingFactory.classNameFor(element),
                this.elementType,
                this.selfType);
        return ImmutableSet.of(
                TypeVariableName.get(this.elementType.name + " extends " + element.getQualifiedName()),
                TypeVariableName.get(this.selfType + " extends " + typeName));
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("selfType", this.selfType)
                .add("elementType", this.elementType)
                .add("namingFactory", this.namingFactory)
                .toString();
    }
}
