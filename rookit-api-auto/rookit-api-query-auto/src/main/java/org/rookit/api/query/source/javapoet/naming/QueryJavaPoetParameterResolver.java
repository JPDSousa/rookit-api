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
package org.rookit.api.query.source.javapoet.naming;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableSet;
import com.google.inject.Inject;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeVariableName;
import org.rookit.api.query.source.guice.Query;
import org.rookit.api.query.source.guice.QueryEntity;
import org.rookit.auto.javapoet.naming.JavaPoetNamingFactory;
import org.rookit.auto.javapoet.naming.JavaPoetParameterResolver;
import org.rookit.auto.javax.element.ExtendedTypeElement;

final class QueryJavaPoetParameterResolver implements JavaPoetParameterResolver {

    private final JavaPoetNamingFactory namingFactory;
    private final JavaPoetNamingFactory genericNamingFactory;

    @Inject
    private QueryJavaPoetParameterResolver(@QueryEntity final JavaPoetNamingFactory namingFactory,
                                           @Query final JavaPoetNamingFactory genericNamingFactory) {
        this.namingFactory = namingFactory;
        this.genericNamingFactory = genericNamingFactory;
    }

    @Override
    public TypeName resolveParameters(final ExtendedTypeElement element) {
        final ClassName className = this.namingFactory.classNameFor(element);
        final ClassName genericClassName = this.genericNamingFactory.classNameFor(element);
        final TypeName elementName = TypeVariableName.get(element.getQualifiedName().toString());
        return TypeVariableName.get(ParameterizedTypeName.get(genericClassName, elementName, className).toString());
    }

    @Override
    public Iterable<TypeVariableName> createParameters(final ExtendedTypeElement element) {
        return ImmutableSet.of();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("namingFactory", this.namingFactory)
                .add("genericNamingFactory", this.genericNamingFactory)
                .toString();
    }
}
