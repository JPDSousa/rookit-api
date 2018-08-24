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
package org.rookit.api.query.source.javapoet.type;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableSet;
import com.google.inject.Inject;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeVariableName;
import one.util.streamex.StreamEx;
import org.rookit.api.query.source.guice.Filter;
import org.rookit.api.query.source.guice.Query;
import org.rookit.api.query.source.guice.QueryEntity;
import org.rookit.auto.javapoet.naming.JavaPoetNamingFactory;
import org.rookit.auto.javapoet.naming.JavaPoetParameterResolver;
import org.rookit.auto.javapoet.type.TypeSourceAdapter;
import org.rookit.auto.javax.element.ExtendedTypeElement;

import javax.lang.model.element.Modifier;
import java.util.Collection;

final class QueryPartialTypeSourceFactory extends AbstractPartialTypeSourceFactory {

    private final JavaPoetParameterResolver filterParameterResolver;
    private final JavaPoetNamingFactory namingFactory;

    @Inject
    private QueryPartialTypeSourceFactory(@Query final JavaPoetParameterResolver parameterResolver,
                                          final TypeSourceAdapter adapter,
                                          @Filter final JavaPoetParameterResolver fParameterResolver,
                                          @QueryEntity final JavaPoetNamingFactory namingFactory) {
        super(parameterResolver, adapter);
        this.filterParameterResolver = fParameterResolver;
        this.namingFactory = namingFactory;
    }

    @Override
    Collection<MethodSpec> methodsFor(final ExtendedTypeElement element) {
        return element.upstreamEntity()
                .map(this::adapterMethod)
                .map(ImmutableSet::of)
                .orElse(ImmutableSet.of());
    }

    private MethodSpec adapterMethod(final ExtendedTypeElement element) {
        final ClassName className = this.namingFactory.classNameFor(element);
        return MethodSpec.methodBuilder("to" + className.simpleName())
                .returns(TypeVariableName.get(className.toString()))
                .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
                .build();
    }

    @Override
    StreamEx<TypeName> superTypesFor(final ExtendedTypeElement parent) {
        return StreamEx.of(parameterResolver().resolveParameters(parent));
    }

    @Override
    Collection<TypeName> parentNamesOf(final ExtendedTypeElement baseElement) {
        return ImmutableSet.<TypeName>builder()
                .addAll(super.parentNamesOf(baseElement))
                .add(this.filterParameterResolver.resolveParameters(baseElement))
                .build();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("filterParameterResolver", this.filterParameterResolver)
                .add("namingFactory", this.namingFactory)
                .toString();
    }
}
