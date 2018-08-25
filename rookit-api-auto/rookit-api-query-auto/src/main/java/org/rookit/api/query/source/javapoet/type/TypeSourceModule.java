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

import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.rookit.api.query.source.guice.Filter;
import org.rookit.api.query.source.guice.Query;
import org.rookit.api.query.source.guice.QueryEntity;
import org.rookit.auto.javapoet.MethodFactory;
import org.rookit.auto.javapoet.naming.JavaPoetParameterResolver;
import org.rookit.auto.javapoet.type.PropertyBasedTypeSourceFactory;
import org.rookit.auto.javapoet.type.TypeSourceAdapter;
import org.rookit.auto.javax.PropertyExtractor;
import org.rookit.auto.source.SingleTypeSourceFactory;
import org.rookit.utils.guice.Top;

@SuppressWarnings("MethodMayBeStatic")
public final class TypeSourceModule extends AbstractModule {

    private static final Module MODULE = new TypeSourceModule();

    public static Module getModule() {
        return MODULE;
    }

    private TypeSourceModule() {}

    @Override
    protected void configure() {
        bind(SingleTypeSourceFactory.class).annotatedWith(Query.class).to(QueryPartialTypeSourceFactory.class)
                .in(Singleton.class);
        bind(SingleTypeSourceFactory.class).annotatedWith(QueryEntity.class).to(QueryTypeSourceFactory.class)
                .in(Singleton.class);
    }

    @Singleton
    @Provides
    @Filter
    SingleTypeSourceFactory filterTypeSourceFactory(@Filter final JavaPoetParameterResolver parameterResolver,
                                                    final PropertyExtractor extractor,
                                                    @Top final MethodFactory methodFactory,
                                                    final TypeSourceAdapter adapter) {
        return PropertyBasedTypeSourceFactory.create(parameterResolver, methodFactory, adapter, extractor);
    }
}
