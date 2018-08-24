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
package org.rookit.api.query.source.javapoet.identifier;

import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.rookit.api.query.source.guice.FilterEntity;
import org.rookit.api.query.source.guice.QueryEntity;
import org.rookit.auto.identifier.BaseIdentifierFactory;
import org.rookit.auto.naming.NamingFactory;
import org.rookit.api.query.source.guice.Filter;
import org.rookit.api.query.source.guice.Query;
import org.rookit.auto.identifier.IdentifierFactory;

@SuppressWarnings("MethodMayBeStatic")
public final class JavaPoetIdentifierModule extends AbstractModule {

    private static final Module MODULE = new JavaPoetIdentifierModule();

    public static Module getModule() {
        return MODULE;
    }

    private JavaPoetIdentifierModule() {}

    @Override
    protected void configure() {

    }

    @Provides
    @Filter
    @Singleton
    IdentifierFactory filterIdentifier(@Filter final NamingFactory namingFactory) {
        return BaseIdentifierFactory.create(namingFactory);
    }

    @Provides
    @FilterEntity
    @Singleton
    IdentifierFactory filterEntityIdentifier(@FilterEntity final NamingFactory namingFactory) {
        return BaseIdentifierFactory.create(namingFactory);
    }

    @Provides
    @Query
    @Singleton
    IdentifierFactory queryIdentifier(@Query final NamingFactory namingFactory) {
        return BaseIdentifierFactory.create(namingFactory);
    }

    @Provides
    @QueryEntity
    @Singleton
    IdentifierFactory queryEntityIdentifier(@QueryEntity final NamingFactory namingFactory) {
        return BaseIdentifierFactory.create(namingFactory);
    }
}
