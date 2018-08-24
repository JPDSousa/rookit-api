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

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;
import com.squareup.javapoet.MethodSpec;
import org.rookit.auto.javax.ExtendedProperty;
import org.rookit.utils.convention.annotation.Entity;
import org.rookit.utils.convention.annotation.Property;
import org.rookit.api.query.source.AnnotationBasedMethodFactory;

import javax.annotation.processing.Messager;
import javax.tools.Diagnostic;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.stream.Stream;

import static java.lang.String.format;

final class AnnotationMethodFactory implements AnnotationBasedMethodFactory {

    private final AnnotationBasedMethodFactory entityMethodFactory;
    private final AnnotationBasedMethodFactory propertyMethodFactory;
    private final Messager messager;

    @SuppressWarnings({"MethodParameterOfConcreteClass", "TypeMayBeWeakened"}) //due to guice
    @Inject
    private AnnotationMethodFactory(final EntityMethodFactory entityFactory,
                                    final PropertyMethodFactory propertyFactory,
                                    final Messager messager) {
        this.entityMethodFactory = entityFactory;
        this.propertyMethodFactory = propertyFactory;
        this.messager = messager;
    }

    @Override
    public Collection<Class<? extends Annotation>> supportedAnnotations() {
        return ImmutableList.of(Entity.class, Property.class);
    }

    @Override
    public Stream<MethodSpec> create(final ExtendedProperty property) {
        if (this.entityMethodFactory.isCompatible(property)) {
            final String message = format("Using entity factory for %s", property.name());
            this.messager.printMessage(Diagnostic.Kind.NOTE, message);
            return this.entityMethodFactory.create(property);
        }
        final String message = format("Using property factory for %s", property.name());
        this.messager.printMessage(Diagnostic.Kind.NOTE, message);
        return this.propertyMethodFactory.create(property);
    }

    @Override
    public boolean isCompatible(final ExtendedProperty property) {
        return this.entityMethodFactory.isCompatible(property) || this.propertyMethodFactory.isCompatible(property);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("entityMethodFactory", this.entityMethodFactory)
                .add("propertyMethodFactory", this.propertyMethodFactory)
                .add("messager", this.messager)
                .toString();
    }
}
