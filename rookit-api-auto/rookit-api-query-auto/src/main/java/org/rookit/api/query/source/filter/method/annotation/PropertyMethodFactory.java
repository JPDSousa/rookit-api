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
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.squareup.javapoet.MethodSpec;
import org.apache.commons.text.WordUtils;
import org.rookit.api.query.source.AnnotationBasedMethodFactory;
import org.rookit.auto.javapoet.MethodFactory;
import org.rookit.auto.javax.ExtendedProperty;
import org.rookit.auto.javax.PropertyAdapter;
import org.rookit.auto.javax.PropertyExtractor;
import org.rookit.utils.convention.annotation.Property;
import org.rookit.utils.guice.Top;

import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Types;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.stream.Stream;

@Singleton
final class PropertyMethodFactory implements AnnotationBasedMethodFactory {

    private final Types types;
    private final PropertyExtractor propertyExtractor;
    private final Provider<MethodFactory> methodFactory;
    private final PropertyAdapter adapter;

    @Inject
    private PropertyMethodFactory(final Types types,
                                  final PropertyExtractor propertyExtractor,
                                  @Top final Provider<MethodFactory> methodFactory,
                                  final PropertyAdapter adapter) {
        this.types = types;
        this.propertyExtractor = propertyExtractor;
        this.methodFactory = methodFactory;
        this.adapter = adapter;
    }

    @Override
    public Collection<Class<? extends Annotation>> supportedAnnotations() {
        return ImmutableList.of(Property.class);
    }

    @Override
    public Stream<MethodSpec> create(final ExtendedProperty property) {
        final MethodFactory methodFactory = this.methodFactory.get();
        return this.propertyExtractor.fromType(getReturnType(property))
                .map(nestedProperty -> concat(property, nestedProperty))
                .filter(methodFactory::isCompatible)
                .flatMap(methodFactory::create);
    }

    private ExtendedProperty concat(final ExtendedProperty property, final ExtendedProperty nestedProperty) {
        final String name = property.name() + WordUtils.capitalize(nestedProperty.name());
        return this.adapter.changeName(nestedProperty, name);
    }

    @Override
    public boolean isCompatible(final ExtendedProperty property) {
        return property.isContainer();
    }

    private TypeElement getReturnType(final ExtendedProperty executable) {
        return (TypeElement) this.types.asElement(executable.type());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("types", this.types)
                .add("propertyExtractor", this.propertyExtractor)
                .add("methodFactory", this.methodFactory)
                .add("adapter", this.adapter)
                .toString();
    }
}
