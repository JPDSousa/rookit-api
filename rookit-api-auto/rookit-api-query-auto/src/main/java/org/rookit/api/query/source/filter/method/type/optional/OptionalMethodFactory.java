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
package org.rookit.api.query.source.filter.method.type.optional;

import com.google.common.base.MoreObjects;
import com.google.inject.Inject;
import com.google.inject.Provider;
import org.apache.commons.collections4.CollectionUtils;
import org.rookit.api.query.source.guice.Filter;
import org.rookit.auto.javapoet.MethodFactory;
import org.rookit.api.query.source.method.MethodSpecFactory;
import org.rookit.auto.javax.element.ElementUtils;
import org.rookit.auto.javax.ExtendedProperty;
import org.rookit.utils.guice.Top;
import org.rookit.auto.javax.PropertyAdapter;

import javax.lang.model.type.TypeMirror;
import java.util.Optional;

final class OptionalMethodFactory extends AbstractOptionalMethodFactory {

    private final TypeMirror optionalType;

    @Inject
    OptionalMethodFactory(@Filter final MethodSpecFactory methodSpecFactory,
                          final ElementUtils utils,
                          @Top final Provider<MethodFactory> methodFactory,
                          final PropertyAdapter propertyAdapter) {
        super(utils, methodSpecFactory, methodFactory, propertyAdapter);
        this.optionalType = utils.erasure(Optional.class);
    }

    @Override
    public boolean isCompatible(final ExtendedProperty property) {
        return utils().isSameTypeErasure(property.type(), type());
    }

    @Override
    TypeMirror unwrapOptional(final TypeMirror optional) {
        return CollectionUtils.extractSingleton(utils().typeParameters(optional));
    }

    @Override
    public TypeMirror type() {
        return this.optionalType;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("optionalType", this.optionalType)
                .toString();
    }
}
