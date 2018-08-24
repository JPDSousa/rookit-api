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
import java.util.Optional;

final class EntityTypeSourceFactory extends BaseTypeSourceFactory {

    private final String singleton;

    @Inject
    private EntityTypeSourceFactory(final PropertyExtractor extractor,
                                    final MethodFactory methodFactory,
                                    final FieldFactory fieldFactory,
                                    final EntityMethodFactory entityMethodFactory,
                                    final TypeSourceAdapter adapter,
                                    final JavaPoetParameterResolver parameterResolver) {
        super(extractor, methodFactory, fieldFactory, entityMethodFactory, adapter, parameterResolver);
        this.singleton = "SINGLETON";
    }

    @Override
    Optional<TypeName> superType(final ExtendedTypeElement element) {
        return element.conventionInterfaces()
                .map(parameterResolver()::resolveParameters)
                .findAny();
    }

    @Override
    Iterable<MethodSpec> methods(final ExtendedTypeElement element,
                                 final Collection<ExtendedProperty> properties,
                                 final ClassName className) {
        final MethodSpec singleton = MethodSpec.methodBuilder("getInstance")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(className)
                .addStatement("return $L", this.singleton)
                .build();

        return ImmutableList.<MethodSpec>builder()
                .add(singleton)
                .addAll(super.methods(element, properties, className))
                .build();
    }

    @Override
    Iterable<FieldSpec> fields(final ExtendedTypeElement element,
                               final Collection<ExtendedProperty> properties,
                               final ClassName className) {
        final FieldSpec singleton = FieldSpec.builder(className, this.singleton,
                Modifier.PRIVATE, Modifier.STATIC, Modifier.FINAL)
                .initializer("new $T()", className)
                .build();

        return ImmutableList.<FieldSpec>builder()
                .add(singleton)
                .addAll(super.fields(element, properties, className))
                .build();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("singleton", this.singleton)
                .toString();
    }
}
