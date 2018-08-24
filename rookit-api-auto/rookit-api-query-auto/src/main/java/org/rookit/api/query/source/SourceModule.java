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
package org.rookit.api.query.source;

import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.util.Modules;
import org.rookit.api.query.source.filter.FilterModule;
import org.rookit.api.query.source.guice.Query;
import org.rookit.api.query.source.javapoet.JavaPoetModule;
import org.rookit.api.query.source.naming.NamingModule;
import org.rookit.api.query.source.property.PropertyModule;
import org.rookit.auto.EntityHandler;
import org.rookit.auto.StatelessEntityHandler;
import org.rookit.auto.entity.BaseEntityFactory;
import org.rookit.auto.entity.EntityFactory;
import org.rookit.auto.javax.element.ElementUtils;

import javax.annotation.processing.Filer;

@SuppressWarnings("MethodMayBeStatic")
public final class SourceModule extends AbstractModule {

    private static final Module MODULE = Modules.combine(new SourceModule(),
            FilterModule.getModule(),
            PropertyModule.getModule(),
            JavaPoetModule.getModule(),
            NamingModule.getModule());

    public static Module getModule() {
        return MODULE;
    }

    private SourceModule() {}

    @Override
    protected void configure() {
    }

    @Singleton
    @Provides
    EntityFactory entityFactory(final EntityFactory entityFactory) {
        return BaseEntityFactory.create(entityFactory);
    }

    @Singleton
    @Provides
    EntityHandler queryHandler(@Query final EntityFactory queryEntityFactory,
                               final ElementUtils utils,
                               final Filer filer) {
        return StatelessEntityHandler.create(queryEntityFactory, utils, filer);
    }
}
