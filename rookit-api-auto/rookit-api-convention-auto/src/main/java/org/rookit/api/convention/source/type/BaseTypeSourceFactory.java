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
package org.rookit.api.convention.source.type;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import org.apache.commons.collections4.CollectionUtils;
import org.rookit.auto.entity.Identifier;
import org.rookit.auto.javapoet.EntityMethodFactory;
import org.rookit.auto.javapoet.FieldFactory;
import org.rookit.auto.javapoet.MethodFactory;
import org.rookit.auto.javapoet.naming.JavaPoetParameterResolver;
import org.rookit.auto.javapoet.type.TypeSourceAdapter;
import org.rookit.auto.javax.ExtendedProperty;
import org.rookit.auto.javax.PropertyExtractor;
import org.rookit.auto.javax.element.ExtendedTypeElement;
import org.rookit.auto.source.SingleTypeSourceFactory;
import org.rookit.auto.source.TypeSource;
import org.rookit.utils.convention.Properties;

import javax.lang.model.element.Modifier;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

class BaseTypeSourceFactory implements SingleTypeSourceFactory {

    private final TypeName properties;
    private final PropertyExtractor extractor;
    private final MethodFactory methodFactory;
    private final FieldFactory fieldFactory;
    private final EntityMethodFactory entityMethodFactory;
    private final TypeSourceAdapter adapter;
    private final JavaPoetParameterResolver parameterResolver;

    BaseTypeSourceFactory(final PropertyExtractor extractor,
                          final MethodFactory methodFactory,
                          final FieldFactory fieldFactory,
                          final EntityMethodFactory entityMethodFactory,
                          final TypeSourceAdapter adapter,
                          final JavaPoetParameterResolver parameterResolver) {
        this.extractor = extractor;
        this.methodFactory = methodFactory;
        this.fieldFactory = fieldFactory;
        this.entityMethodFactory = entityMethodFactory;
        this.adapter = adapter;
        this.parameterResolver = parameterResolver;
        this.properties = ClassName.get(Properties.class);
    }

    JavaPoetParameterResolver parameterResolver() {
        return this.parameterResolver;
    }

    @Override
    public TypeSource create(final Identifier identifier,
                             final ExtendedTypeElement element) {
        final ClassName className = ClassName.get(identifier.packageName().fullName(), identifier.name());
        final Collection<ExtendedProperty> properties = this.extractor.fromType(element).collect(Collectors.toList());
        final TypeSpec.Builder builder = TypeSpec.classBuilder(className)
                .addMethod(constructor(element))
                .addModifiers(Modifier.PUBLIC)
                .addSuperinterfaces(superInterfaces(element, properties))
                .addMethods(methods(element, properties, className))
                .addFields(fields(element, properties, className));
        superType(element).ifPresent(builder::superclass);
        return this.adapter.fromTypeSpec(identifier, builder.build());
    }

    Optional<TypeName> superType(final ExtendedTypeElement element) {
        final Collection<TypeName> superTypes = element.conventionInterfaces()
                .map(this.parameterResolver::resolveParameters)
                .collect(Collectors.toList());
        return superTypes.isEmpty() ? Optional.empty() : Optional.of(CollectionUtils.extractSingleton(superTypes));
    }

    MethodSpec constructor(final ExtendedTypeElement element) {
        return MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PROTECTED)
                .build();
    }

    Iterable<TypeName> superInterfaces(final ExtendedTypeElement element,
                                       final Collection<ExtendedProperty> properties) {
        return ImmutableList.of(this.properties);
    }

    Iterable<MethodSpec> methods(final ExtendedTypeElement element,
                                 final Collection<ExtendedProperty> properties,
                                 final ClassName className) {
        return ImmutableList.<MethodSpec>builder()
                .addAll(this.entityMethodFactory.create(element).collect(Collectors.toList()))
                .addAll(this.methodFactory.filterCompatible(properties))
                .build();
    }

    Iterable<FieldSpec> fields(final ExtendedTypeElement element,
                               final Collection<ExtendedProperty> properties,
                               final ClassName className) {
        return this.fieldFactory.filterCompatible(properties);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("properties", this.properties)
                .add("extractor", this.extractor)
                .add("methodFactory", this.methodFactory)
                .add("fieldFactory", this.fieldFactory)
                .add("entityMethodFactory", this.entityMethodFactory)
                .add("adapter", this.adapter)
                .add("parameterResolver", this.parameterResolver)
                .toString();
    }
}
