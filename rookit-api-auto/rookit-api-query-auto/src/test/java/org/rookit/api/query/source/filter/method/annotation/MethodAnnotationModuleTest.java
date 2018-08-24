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
package org.rookit.api.query.source.filter.method.annotation;

import com.google.common.collect.ImmutableList;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.squareup.javapoet.TypeVariableName;
import org.rookit.api.query.source.AnnotationBasedMethodFactory;
import org.rookit.api.query.source.guice.Filter;
import org.rookit.auto.javapoet.MethodFactory;
import org.rookit.test.guice.ModuleTest;
import org.rookit.utils.guice.Top;

import javax.annotation.processing.Messager;
import javax.lang.model.util.Types;
import java.util.Collection;

import static org.mockito.Mockito.mock;

class MethodAnnotationModuleTest implements ModuleTest {

    private static final class MockModule extends AbstractModule {

        @Override
        protected void configure() {
            bind(MethodFactory.class).annotatedWith(Top.class).toInstance(mock(MethodFactory.class));
            bind(TypeVariableName.class).annotatedWith(Filter.class).toInstance(TypeVariableName.get("Q"));
            bind(Messager.class).toInstance(mock(Messager.class));
            bind(Types.class).toInstance(mock(Types.class));
        }
    }

    private static final Injector INJECTOR = Guice.createInjector(MethodAnnotationModule.getModule(), new MockModule());

    @Override
    public Injector getInjector() {
        return INJECTOR;
    }

    @Override
    public Collection<Class<?>> getInstanceClasses() {
        return ImmutableList.of(EntityMethodFactory.class,
                AnnotationBasedMethodFactory.class,
                PropertyMethodFactory.class);
    }
}