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
package org.rookit.api.convention.source.field;

import com.google.common.base.MoreObjects;
import com.google.inject.Inject;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeVariableName;
import one.util.streamex.StreamEx;
import org.rookit.api.convention.guice.Container;
import org.rookit.auto.entity.Entity;
import org.rookit.auto.javapoet.FieldFactory;
import org.rookit.auto.entity.EntityFactory;
import org.rookit.auto.javax.ExtendedProperty;
import org.rookit.auto.javax.element.ElementUtils;
import org.rookit.auto.javax.element.ExtendedTypeElement;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.util.stream.Stream;

final class MultiFieldFactory implements FieldFactory {

    private static final Modifier[] MODIFIERS = {Modifier.PRIVATE, Modifier.FINAL};

    private final EntityFactory entityFactory;
    private final ElementUtils utils;
    private final Filer filer;

    @Inject
    private MultiFieldFactory(@Container final EntityFactory entityFactory,
                              final ElementUtils utils,
                              final Filer filer) {
        this.entityFactory = entityFactory;
        this.utils = utils;
        this.filer = filer;
    }

    @Override
    public Stream<FieldSpec> create(final ExtendedProperty property) {
        try {
            final ExtendedTypeElement returnType = property.typeAsElement();
            // TODO we should not be writing entities here. If the upstream fails, this is already written :(
            final Entity entity = this.entityFactory.create(returnType);
            entity.writeTo(this.filer);
            final TypeName entityType = TypeVariableName.get(entity.identifier().fqdn());
            return StreamEx.of(FieldSpec.builder(entityType, property.name(), MODIFIERS)
                    .initializer("$T.create($S)", entityType, property.name())
                    .build());
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isCompatible(final ExtendedProperty property) {
        return property.isContainer();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("entityFactory", this.entityFactory)
                .add("utils", this.utils)
                .add("filer", this.filer)
                .toString();
    }
}
