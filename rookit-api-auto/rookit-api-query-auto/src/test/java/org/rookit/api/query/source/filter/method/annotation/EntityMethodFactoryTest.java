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

import com.google.common.collect.ImmutableList;
import com.squareup.javapoet.MethodSpec;
import org.junit.jupiter.api.Test;
import org.rookit.utils.convention.annotation.Entity;
import org.rookit.api.query.JavaxMockUtils;
import org.rookit.test.AbstractUnitTest;
import org.rookit.test.junit.categories.UnitTest;
import org.rookit.auto.javax.ExtendedProperty;

import javax.lang.model.element.Element;
import javax.lang.model.type.TypeMirror;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@UnitTest
class EntityMethodFactoryTest extends AbstractUnitTest<EntityMethodFactory> {

    private static final JavaxMockUtils MOCK_UTILITIES = new JavaxMockUtils();

    @Override
    public EntityMethodFactory doCreateTestResource() {
        return new EntityMethodFactory(null, null);
    }

    @Test
    public final void testSupportedAnnotations() {
        assertThat(this.testResource)
                .as("The expected supported annotations")
                .isEqualTo(ImmutableList.of(Entity.class));
    }

    @Test
    public final void testCreate() {
        final TypeMirror returnType = mock(TypeMirror.class);
        final Element returnTypeElement = mock(Element.class);
        final Element packageElement = mock(Element.class);
        final ExtendedProperty method = mock(ExtendedProperty.class);
        final String propertyName = "Abc";
        when(method.name()).thenReturn("method");
        MOCK_UTILITIES.mockElementSimpleName(returnTypeElement, propertyName);
        MOCK_UTILITIES.mockElementSimpleName(packageElement, "org.package");
        when(method.type()).thenReturn(returnType);
        when(returnTypeElement.getEnclosingElement()).thenReturn(packageElement);

        final List<MethodSpec> methods = this.testResource.create(method).collect(Collectors.toList());
        assertThat(methods)
                .as("The generated methods")
                .hasSize(2);
    }

}