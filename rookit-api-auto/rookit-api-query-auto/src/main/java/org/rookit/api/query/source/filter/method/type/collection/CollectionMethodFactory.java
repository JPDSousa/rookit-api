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
package org.rookit.api.query.source.filter.method.type.collection;

import com.google.common.base.MoreObjects;
import com.google.inject.Inject;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;
import org.rookit.api.query.source.filter.method.type.AbstractMethodFactory;
import org.rookit.api.query.source.guice.Filter;
import org.rookit.api.query.source.method.MethodSpecFactory;
import org.rookit.auto.javax.element.ElementUtils;
import org.rookit.auto.javax.ExtendedProperty;

import javax.lang.model.type.TypeMirror;
import java.util.Collection;
import java.util.stream.Stream;

import static org.apache.commons.collections4.CollectionUtils.extractSingleton;

final class CollectionMethodFactory extends AbstractMethodFactory {

    private final TypeMirror type;
    private final MethodSpecFactory methodSpecFactory;

    @Inject
    private CollectionMethodFactory(final ElementUtils utils,
                                    @Filter final MethodSpecFactory methodSpecFactory) {
        super(utils);
        this.methodSpecFactory = methodSpecFactory;
        this.type = utils.erasure(Collection.class);
    }

    @Override
    public TypeMirror type() {
        return this.type;
    }

    @Override
    public boolean isCompatible(final ExtendedProperty property) {
        return utils().isSameTypeErasure(property.type(), type());
    }

    @Override
    public Stream<MethodSpec> create(final ExtendedProperty property) {
        final TypeMirror type = property.type();
        final TypeMirror unwrapped = extractSingleton(utils().typeParameters(type));

        final String propertyName = property.name();
        return Stream.of(
                createContains(unwrapped, propertyName),
                createContains(type, propertyName),
                createNotContains(unwrapped, propertyName),
                createNotContains(type, propertyName)
        );
    }

    private MethodSpec createNotContains(final TypeMirror returnType, final String propertyName) {
        final ParameterSpec param = ParameterSpec.builder(TypeName.get(returnType), "absent").build();
        return this.methodSpecFactory.create(propertyName, "no", param);
    }

    private MethodSpec createContains(final TypeMirror returnType, final String propertyName) {
        final ParameterSpec param = ParameterSpec.builder(TypeName.get(returnType), "present").build();
        return this.methodSpecFactory.create(propertyName, param);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("type", this.type)
                .add("methodSpecFactory", this.methodSpecFactory)
                .toString();
    }
}
