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
import com.squareup.javapoet.TypeName;
import one.util.streamex.StreamEx;
import org.rookit.api.query.source.guice.Filter;
import org.rookit.api.query.source.guice.Query;
import org.rookit.auto.javapoet.MethodFactory;
import org.rookit.auto.javapoet.naming.JavaPoetParameterResolver;
import org.rookit.auto.javapoet.type.TypeSourceAdapter;
import org.rookit.auto.javax.PropertyExtractor;
import org.rookit.auto.javax.element.ExtendedTypeElement;

import java.util.Collection;

final class QueryPartialTypeSourceFactory extends AbstractPartialTypeSourceFactory {

    private final JavaPoetParameterResolver filterParameterResolver;

    @Inject
    private QueryPartialTypeSourceFactory(@Query final JavaPoetParameterResolver parameterResolver,
                                          @Query final PropertyExtractor extractor,
                                          @Query final MethodFactory methodFactory,
                                          final TypeSourceAdapter adapter,
                                          @Filter final JavaPoetParameterResolver fParameterResolver) {
        super(parameterResolver, extractor, methodFactory, adapter);
        this.filterParameterResolver = fParameterResolver;
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
                .toString();
    }
}
