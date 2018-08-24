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
package org.rookit.api.query.source.javapoet.entity;

import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.rookit.api.query.source.guice.Filter;
import org.rookit.api.query.source.guice.NoopFilter;
import org.rookit.api.query.source.guice.NoopQuery;
import org.rookit.api.query.source.guice.Query;
import org.rookit.auto.entity.EntityFactory;
import org.rookit.auto.entity.PartialEntityFactory;
import org.rookit.auto.entity.noop.ExclusionPartialEntityFactory;
import org.rookit.auto.entity.noop.NoopEntityFactory;
import org.rookit.auto.identifier.IdentifierFactory;

import javax.annotation.processing.Messager;

@SuppressWarnings("MethodMayBeStatic")
public final class JavaPoetEntityModule extends AbstractModule {

    private static final Module MODULE = new JavaPoetEntityModule();

    public static Module getModule() {
        return MODULE;
    }

    private JavaPoetEntityModule() {}

    @Override
    protected void configure() {
        bind(EntityFactory.class).annotatedWith(Filter.class)
                .to(FilterEntityFactory.class).in(Singleton.class);
        bind(EntityFactory.class).annotatedWith(Query.class)
                .to(QueryEntityFactory.class).in(Singleton.class);
        bind(PartialEntityFactory.class).annotatedWith(Query.class)
                .to(QueryPartialEntityFactory.class).in(Singleton.class);
        bind(PartialEntityFactory.class).annotatedWith(Filter.class)
                .to(FilterPartialEntityFactory.class).in(Singleton.class);
    }

    @Singleton
    @Provides
    @NoopFilter
    EntityFactory noopFilterFactory(final Messager messager,
                                    @Filter final IdentifierFactory identifierFactory,
                                    @Filter final PartialEntityFactory partialEntityFactory) {
        return NoopEntityFactory.create(messager, identifierFactory, partialEntityFactory);
    }

    @Singleton
    @Provides
    @NoopFilter
    PartialEntityFactory noopPartialFilterFactory(@Filter final IdentifierFactory identifierFactory) {
        return ExclusionPartialEntityFactory.create(identifierFactory);
    }

    @Singleton
    @Provides
    @NoopQuery
    EntityFactory noopFactory(final Messager messager,
                              @Query final IdentifierFactory identifierFactory,
                              @Filter final PartialEntityFactory partialEntityFactory) {
        return NoopEntityFactory.create(messager, identifierFactory, partialEntityFactory);
    }

    @Singleton
    @Provides
    @NoopQuery
    PartialEntityFactory noopFactory(@Query final IdentifierFactory identifierFactory) {
        return ExclusionPartialEntityFactory.create(identifierFactory);
    }

}
