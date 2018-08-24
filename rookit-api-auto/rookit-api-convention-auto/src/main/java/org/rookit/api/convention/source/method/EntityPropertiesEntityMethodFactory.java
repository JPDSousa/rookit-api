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
import com.google.common.collect.Lists;
import com.google.common.collect.MoreCollectors;
import com.google.inject.Inject;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.MethodSpec;
import one.util.streamex.StreamEx;
import org.rookit.auto.javax.element.ElementUtils;
import org.rookit.auto.javax.element.ExtendedTypeElement;
import org.rookit.auto.javapoet.EntityMethodFactory;
import org.rookit.auto.javax.ExtendedProperty;
import org.rookit.auto.javax.PropertyExtractor;
import org.rookit.utils.convention.Properties;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

final class EntityPropertiesEntityMethodFactory implements EntityMethodFactory {

    private final ExecutableElement allProperties;
    private final ElementUtils utils;
    private final PropertyExtractor extractor;

    @Inject
    private EntityPropertiesEntityMethodFactory(final ElementUtils utils,
                                                final PropertyExtractor extractor) {
        this.utils = utils;
        this.extractor = extractor;
        final Optional<Element> elementOrNone = utils.toElement(utils.erasure(Properties.class));
        this.allProperties = StreamEx.of(elementOrNone)
                .map(Element::getEnclosedElements)
                .flatMap(Collection::stream)
                .select(ExecutableElement.class)
                .filter(method -> method.getSimpleName().contentEquals("properties"))
                .collect(MoreCollectors.onlyElement());
    }

    @Override
    public StreamEx<MethodSpec> create(final ExtendedTypeElement element) {
        final MethodSpec.Builder builder = MethodSpec.overriding(this.allProperties);
        final String varName = "allProperties";
        if (element.isTopLevel()) {
            builder.addStatement("final $T<$T> $L = $T.newArrayList()", List.class,
                    org.rookit.utils.convention.Property.class, varName, Lists.class);
        } else {
            builder.addStatement("final $T<$T> $L = $T.newArrayList(super.properties())",
                    List.class, org.rookit.utils.convention.Property.class, varName, Lists.class);
        }
        this.extractor.fromType(element)
                .map(ExtendedProperty::name)
                .map(name -> CodeBlock.of("$L.add(this.$L)", varName, name))
                .forEach(builder::addStatement);
        builder.addStatement("return $T.unmodifiableCollection($L)", Collections.class, varName);
        return StreamEx.of(builder.build());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("allProperties", this.allProperties)
                .add("utils", this.utils)
                .add("extractor", this.extractor)
                .toString();
    }
}
