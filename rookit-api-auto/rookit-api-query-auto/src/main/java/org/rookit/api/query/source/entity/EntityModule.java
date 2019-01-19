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
import org.rookit.api.query.guice.Filter;
import org.rookit.api.query.guice.NoopFilter;
import org.rookit.api.query.guice.NoopQuery;
import org.rookit.api.query.guice.NoopUpdate;
import org.rookit.api.query.guice.NoopUpdateFilter;
import org.rookit.api.query.guice.PartialFilter;
import org.rookit.api.query.guice.PartialQuery;
import org.rookit.api.query.guice.PartialUpdate;
import org.rookit.api.query.guice.PartialUpdateFilter;
import org.rookit.api.query.guice.Query;
import org.rookit.api.query.guice.Update;
import org.rookit.api.query.guice.UpdateFilter;
import org.rookit.auto.entity.EntityFactory;
import org.rookit.auto.entity.PartialEntityFactory;
import org.rookit.auto.entity.noop.NoopEntityFactory;
import org.rookit.auto.entity.noop.NoopPartialEntityFactory;
import org.rookit.auto.identifier.IdentifierFactory;
import org.rookit.utils.primitive.VoidUtils;

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
        filterConfigurations();
        queryConfigurations();
        updateConfigurations();
        updateFilterConfigurations();
    }

    private void updateFilterConfigurations() {
        bind(EntityFactory.class).annotatedWith(UpdateFilter.class).to(UpdateFilterEntityFactory.class)
                .in(Singleton.class);
        bind(PartialEntityFactory.class).annotatedWith(PartialUpdateFilter.class)
                .to(UpdateFilterPartialEntityFactory.class).in(Singleton.class);
    }

    private void updateConfigurations() {
        bind(EntityFactory.class).annotatedWith(Update.class).to(UpdateEntityFactory.class).in(Singleton.class);
        bind(PartialEntityFactory.class).annotatedWith(PartialUpdate.class).to(UpdatePartialEntityFactory.class)
                .in(Singleton.class);
    }

    private void queryConfigurations() {
        bind(EntityFactory.class).annotatedWith(Query.class)
                .to(QueryEntityFactory.class).in(Singleton.class);
        bind(PartialEntityFactory.class).annotatedWith(PartialQuery.class)
                .to(QueryPartialEntityFactory.class).in(Singleton.class);
    }

    private void filterConfigurations() {
        bind(EntityFactory.class).annotatedWith(Filter.class)
                .to(FilterEntityFactory.class).in(Singleton.class);
        bind(PartialEntityFactory.class).annotatedWith(PartialFilter.class)
                .to(FilterPartialEntityFactory.class).in(Singleton.class);
    }

    @Singleton
    @Provides
    @NoopFilter
    EntityFactory noopFilterFactory(final Messager messager,
                                    @PartialFilter final IdentifierFactory identifierFactory,
                                    @PartialFilter final PartialEntityFactory partialEntityFactory,
                                    final VoidUtils voidUtils) {
        return NoopEntityFactory.create(messager, identifierFactory, partialEntityFactory, voidUtils);
    }

    @Singleton
    @Provides
    @NoopFilter
    PartialEntityFactory noopPartialFilterFactory(@PartialFilter final IdentifierFactory identifierFactory,
                                                  final VoidUtils voidUtils) {
        return NoopPartialEntityFactory.create(identifierFactory, voidUtils);
    }

    @Singleton
    @Provides
    @NoopQuery
    EntityFactory noopFactory(final Messager messager,
                              @PartialQuery final IdentifierFactory identifierFactory,
                              @PartialFilter final PartialEntityFactory partialEntityFactory,
                              final VoidUtils voidUtils) {
        return NoopEntityFactory.create(messager, identifierFactory, partialEntityFactory, voidUtils);
    }

    @Singleton
    @Provides
    @NoopQuery
    PartialEntityFactory noopFactory(@PartialQuery final IdentifierFactory identifierFactory,
                                     final VoidUtils voidUtils) {
        return NoopPartialEntityFactory.create(identifierFactory, voidUtils);
    }

    @Singleton
    @Provides
    @NoopUpdate
    EntityFactory noopUpdateEntityFactory(final Messager messager,
                                          @Update final IdentifierFactory identifierFactory,
                                          @PartialUpdate final PartialEntityFactory partialEntityFactory,
                                          final VoidUtils voidUtils) {
        return NoopEntityFactory.create(messager, identifierFactory, partialEntityFactory, voidUtils);
    }

    @Singleton
    @Provides
    @NoopUpdate
    PartialEntityFactory noopUpdatePartialEntityFactory(@PartialUpdate final IdentifierFactory identifierFactory,
                                                        final VoidUtils voidUtils) {
        return NoopPartialEntityFactory.create(identifierFactory, voidUtils);
    }

    @Singleton
    @Provides
    @NoopUpdateFilter
    EntityFactory noopUpdateFilterEntityFactory(final Messager messager,
                                                @UpdateFilter final IdentifierFactory identifierFactory,
                                                @PartialUpdateFilter final PartialEntityFactory partialEntityFactory,
                                                final VoidUtils voidUtils) {
        return NoopEntityFactory.create(messager, identifierFactory, partialEntityFactory, voidUtils);
    }


    @Singleton
    @Provides
    @NoopUpdateFilter
    PartialEntityFactory noopUpdateFilterPartialEntityFactory(
            @PartialUpdateFilter final IdentifierFactory identifierFactory,
            final VoidUtils voidUtils) {
        return NoopPartialEntityFactory.create(identifierFactory, voidUtils);
    }

}
