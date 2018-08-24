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
package org.rookit.api.query.source.javapoet;

import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.util.Modules;
import com.squareup.javapoet.TypeVariableName;
import org.rookit.api.query.source.guice.ElementQuery;
import org.rookit.api.query.source.guice.Filter;
import org.rookit.api.query.source.guice.FilterEntity;
import org.rookit.api.query.source.guice.Query;
import org.rookit.api.query.source.javapoet.entity.JavaPoetEntityModule;
import org.rookit.api.query.source.javapoet.identifier.JavaPoetIdentifierModule;
import org.rookit.api.query.source.javapoet.naming.JavaPoetNamingModule;
import org.rookit.api.query.source.javapoet.type.TypeSourceModule;
import org.rookit.auto.javapoet.type.BaseTypeSourceAdapter;
import org.rookit.auto.javapoet.type.EmptyTypeSourceFactory;
import org.rookit.auto.javapoet.type.TypeSourceAdapter;
import org.rookit.auto.naming.NamingFactory;
import org.rookit.auto.source.SingleTypeSourceFactory;

@SuppressWarnings("MethodMayBeStatic")
public final class JavaPoetModule extends AbstractModule {

    private static final Module MODULE = Modules.combine(
            new JavaPoetModule(),
            JavaPoetEntityModule.getModule(),
            JavaPoetIdentifierModule.getModule(),
            JavaPoetNamingModule.getModule(),
            TypeSourceModule.getModule()
    );

    public static Module getModule() {
        return MODULE;
    }

    private JavaPoetModule() {}

    @Override
    protected void configure() {
        final TypeVariableName typeQ = TypeVariableName.get("Q");
        bind(TypeVariableName.class).annotatedWith(Filter.class).toInstance(typeQ);
        bind(TypeVariableName.class).annotatedWith(Query.class).toInstance(typeQ);
        bind(TypeVariableName.class).annotatedWith(ElementQuery.class).toInstance(TypeVariableName.get("E"));
        bind(TypeSourceAdapter.class).toInstance(BaseTypeSourceAdapter.create());
    }

    @Provides
    @Singleton
    @FilterEntity
    SingleTypeSourceFactory filterEntityFactory(@Filter final NamingFactory namingFactory) {
        return EmptyTypeSourceFactory.create(namingFactory);
    }

}
