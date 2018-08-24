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
package org.rookit.api.query.source.filter.method;

import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.google.inject.Singleton;
import com.google.inject.util.Modules;
import org.rookit.auto.javapoet.MethodFactory;
import org.rookit.api.query.source.method.MethodSpecFactory;
import org.rookit.api.query.source.filter.method.annotation.MethodAnnotationModule;
import org.rookit.api.query.source.filter.method.type.TypeMethodFactoryModule;
import org.rookit.api.query.source.guice.FallbackFilter;
import org.rookit.api.query.source.guice.Filter;
import org.rookit.utils.guice.Top;

public final class MethodModule extends AbstractModule {

    private static final Module MODULE = Modules.combine(new MethodModule(),
            MethodAnnotationModule.getModule(),
            TypeMethodFactoryModule.getModule());

    public static Module getModule() {
        return MODULE;
    }

    private MethodModule() {}

    @Override
    protected void configure() {
        bind(MethodFactory.class).annotatedWith(Top.class).to(FilterMethodFactory.class).in(Singleton.class);
        bind(MethodFactory.class).annotatedWith(FallbackFilter.class).to(FilterGenericMethodFactory.class)
                .in(Singleton.class);
        bind(MethodSpecFactory.class).annotatedWith(Filter.class).to(FilterMethodSpecFactory.class).in(Singleton.class);
    }
}
