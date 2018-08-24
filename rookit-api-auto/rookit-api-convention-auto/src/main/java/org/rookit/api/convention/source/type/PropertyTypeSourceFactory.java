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
import com.google.inject.Inject;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import org.rookit.auto.javapoet.EntityMethodFactory;
import org.rookit.auto.javapoet.FieldFactory;
import org.rookit.auto.javapoet.MethodFactory;
import org.rookit.auto.javapoet.naming.JavaPoetParameterResolver;
import org.rookit.auto.javapoet.type.TypeSourceAdapter;
import org.rookit.auto.javax.ExtendedProperty;
import org.rookit.auto.javax.PropertyExtractor;
import org.rookit.auto.javax.element.ExtendedTypeElement;

import javax.lang.model.element.Modifier;
import java.util.Collection;

final class PropertyTypeSourceFactory extends BaseTypeSourceFactory {

    private final String varName;
    private final TypeName property;
    private final MethodSpec method;
    private final FieldSpec field;

    @Inject
    private PropertyTypeSourceFactory(final PropertyExtractor extractor,
                                      final MethodFactory methodFactory,
                                      final FieldFactory fieldFactory,
                                      final EntityMethodFactory entityMethodFactory,
                                      final TypeSourceAdapter adapter,
                                      final JavaPoetParameterResolver parameterResolver) {
        super(extractor, methodFactory, fieldFactory, entityMethodFactory, adapter, parameterResolver);
        this.varName = "propertyName";
        this.property = ClassName.get(org.rookit.utils.convention.Property.class);
        this.method = MethodSpec.methodBuilder(this.varName)
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class)
                .returns(String.class)
                .addStatement("return this.$L", this.varName)
                .build();
        this.field = FieldSpec.builder(String.class, this.varName, Modifier.PRIVATE, Modifier.FINAL)
                .build();
    }

    @Override
    Iterable<TypeName> superInterfaces(final ExtendedTypeElement element,
                                       final Collection<ExtendedProperty> properties) {
        return ImmutableList.<TypeName>builder()
                .addAll(super.superInterfaces(element, properties))
                .add(this.property)
                .build();
    }

    @Override
    Iterable<MethodSpec> methods(final ExtendedTypeElement element,
                                 final Collection<ExtendedProperty> properties,
                                 final ClassName className) {
        final MethodSpec createMethod = MethodSpec.methodBuilder("create")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(className)
                .addParameter(String.class, this.varName, Modifier.FINAL)
                .addStatement("return new $T($L)", className, this.varName)
                .build();
        final ImmutableList.Builder<MethodSpec> builder = ImmutableList.<MethodSpec>builder()
                .addAll(super.methods(element, properties, className))
                .add(createMethod);
        if (element.isTopLevel()) {
            builder.add(this.method);
        }
        return builder.build();
    }

    @Override
    Iterable<FieldSpec> fields(final ExtendedTypeElement element,
                               final Collection<ExtendedProperty> properties,
                               final ClassName className) {
        final ImmutableList.Builder<FieldSpec> builder = ImmutableList.<FieldSpec>builder()
                .addAll(super.fields(element, properties, className));
        if (element.isTopLevel()) {
            builder.add(this.field);
        }

        return builder.build();
    }

    @Override
    MethodSpec constructor(final ExtendedTypeElement element) {
        final MethodSpec.Builder builder = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PROTECTED)
                .addParameter(String.class, "propertyName", Modifier.FINAL);

        if (element.isTopLevel()) {
            builder.addStatement("this.propertyName = propertyName");
        }
        else {
            builder.addStatement("super(propertyName)");
        }

        return builder
                .build();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("varName", this.varName)
                .add("property", this.property)
                .add("method", this.method)
                .add("field", this.field)
                .toString();
    }
}
