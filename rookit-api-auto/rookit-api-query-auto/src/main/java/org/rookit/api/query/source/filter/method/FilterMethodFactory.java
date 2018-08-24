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

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;
import com.squareup.javapoet.MethodSpec;
import org.rookit.api.query.source.AnnotationBasedMethodFactory;
import org.rookit.auto.javapoet.MethodFactory;
import org.rookit.api.query.source.method.TypeBasedMethodFactory;
import org.rookit.api.query.source.guice.FallbackFilter;
import org.rookit.auto.javax.ExtendedProperty;

import javax.annotation.processing.Messager;
import javax.tools.Diagnostic;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Stream;

import static java.lang.String.format;

final class FilterMethodFactory implements MethodFactory {

    private final Collection<MethodFactory> typeFactories;
    private final AnnotationBasedMethodFactory annotationFactory;
    private final MethodFactory genericMethodFactory;
    private final Messager messager;

    @SuppressWarnings("TypeMayBeWeakened") //due to guice
    @Inject
    private FilterMethodFactory(@FallbackFilter final MethodFactory genericMethodFactory,
                                final AnnotationBasedMethodFactory annotationFactory,
                                final Set<TypeBasedMethodFactory> typeFactories,
                                final Messager messager) {
        this.genericMethodFactory = genericMethodFactory;
        this.annotationFactory = annotationFactory;
        this.typeFactories = ImmutableList.copyOf(typeFactories);
        this.messager = messager;
    }

    @Override
    public Stream<MethodSpec> create(final ExtendedProperty property) {
        final String propertyName = property.name();
        if (this.annotationFactory.isCompatible(property)) {
            final String message = format("Using annotation factory to process %s", propertyName);
            this.messager.printMessage(Diagnostic.Kind.NOTE, message);
            return this.annotationFactory.create(property);
        }

        for (final MethodFactory factory : this.typeFactories) {
            if (factory.isCompatible(property)) {
                final String message = format("Using type factory '%s' to process %s", factory, propertyName);
                this.messager.printMessage(Diagnostic.Kind.NOTE, message);
                return factory.create(property);
            }
        }
        final String message = format("No compatible factory found for %s. Falling back to generic.", propertyName);
        this.messager.printMessage(Diagnostic.Kind.NOTE, message);
        return this.genericMethodFactory.create(property);
    }

    @Override
    public boolean isCompatible(final ExtendedProperty property) {
        // as long as generic can support it, this factory can also
        return this.genericMethodFactory.isCompatible(property);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("typeFactories", this.typeFactories)
                .add("annotationFactory", this.annotationFactory)
                .add("genericMethodFactory", this.genericMethodFactory)
                .add("messager", this.messager)
                .toString();
    }
}
