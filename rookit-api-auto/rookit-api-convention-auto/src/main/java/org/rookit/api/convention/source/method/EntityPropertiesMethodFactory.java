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
package org.rookit.api.convention.source.method;

import com.google.common.base.MoreObjects;
import com.google.inject.Inject;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeVariableName;
import org.rookit.api.convention.guice.Container;
import org.rookit.auto.entity.Entity;
import org.rookit.auto.entity.EntityFactory;
import org.rookit.auto.javax.ExtendedProperty;
import org.rookit.auto.javax.element.ElementUtils;
import org.rookit.auto.javax.element.ExtendedTypeElement;
import org.rookit.auto.javapoet.MethodFactory;

import javax.lang.model.element.Modifier;
import java.util.stream.Stream;

final class EntityPropertiesMethodFactory implements MethodFactory {

    private final EntityFactory propertyContainerFactory;
    private final ElementUtils utils;

    @Inject
    private EntityPropertiesMethodFactory(@Container final EntityFactory factory,
                                          final ElementUtils utils) {
        this.propertyContainerFactory = factory;
        this.utils = utils;
    }

    @Override
    public Stream<MethodSpec> create(final ExtendedProperty property) {
        return Stream.of(MethodSpec.methodBuilder(property.name())
                .addModifiers(Modifier.PUBLIC)
                .returns(getReturnType(property))
                .addStatement("return this.$L", property.name())
                .build());
    }

    private TypeName getReturnType(final ExtendedProperty property) {
        if (property.isContainer()) {
            final ExtendedTypeElement propertyReturns = property.typeAsElement();
            final Entity entity = this.propertyContainerFactory.create(propertyReturns);
            return TypeVariableName.get(entity.identifier().fqdn());
        }
        return ClassName.get(org.rookit.utils.convention.Property.class);
    }

    @Override
    public boolean isCompatible(final ExtendedProperty property) {
        // all properties match
        return true;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("propertyContainerFactory", this.propertyContainerFactory)
                .add("utils", this.utils)
                .toString();
    }
}
