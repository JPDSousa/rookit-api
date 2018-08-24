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
import com.google.inject.Inject;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import one.util.streamex.StreamEx;
import org.rookit.api.query.source.guice.Filter;
import org.rookit.auto.javapoet.MethodFactory;
import org.rookit.auto.javapoet.naming.JavaPoetParameterResolver;
import org.rookit.auto.javapoet.type.TypeSourceAdapter;
import org.rookit.auto.javax.PropertyExtractor;
import org.rookit.auto.javax.element.ExtendedTypeElement;
import org.rookit.utils.guice.Top;

import java.util.Collection;
import java.util.stream.Collectors;

final class FilterPartialTypeSourceFactory extends AbstractPartialTypeSourceFactory {

    private final MethodFactory methodFactory;
    private final PropertyExtractor extractor;

    @Inject
    FilterPartialTypeSourceFactory(@Filter final JavaPoetParameterResolver parameterResolver,
                                   @Filter final PropertyExtractor extractor,
                                   @Top final MethodFactory methodFactory,
                                   final TypeSourceAdapter adapter) {
        super(parameterResolver, adapter);
        this.methodFactory = methodFactory;
        this.extractor = extractor;
    }

    @Override
    Collection<MethodSpec> methodsFor(final ExtendedTypeElement element) {
        return this.extractor.fromType(element)
                .filter(this.methodFactory::isCompatible)
                .flatMap(this.methodFactory::create)
                .collect(Collectors.toSet());
    }

    @Override
    StreamEx<TypeName> superTypesFor(final ExtendedTypeElement parent) {
        return StreamEx.of(parameterResolver().resolveParameters(parent));
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("methodFactory", this.methodFactory)
                .add("extractor", this.extractor)
                .toString();
    }
}
