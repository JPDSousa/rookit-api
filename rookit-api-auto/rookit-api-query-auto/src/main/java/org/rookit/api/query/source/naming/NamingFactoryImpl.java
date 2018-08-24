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
package org.rookit.api.query.source.naming;

import com.google.common.base.MoreObjects;
import org.apache.commons.lang3.StringUtils;
import org.rookit.auto.javapoet.naming.JavaPoetNamingFactory;
import org.rookit.auto.javax.element.ExtendedTypeElement;
import org.rookit.auto.naming.PackageReference;

import javax.lang.model.element.TypeElement;

import static org.apache.commons.text.WordUtils.capitalize;

final class NamingFactoryImpl implements JavaPoetNamingFactory {

    private final PackageReference packageReference;
    private final String typeNameSuffix;
    private final String entityPrefix;

    NamingFactoryImpl(final PackageReference packageReference, final String typeNameSuffix, final String entityPrefix) {
        this.packageReference = packageReference;
        this.typeNameSuffix = typeNameSuffix;
        this.entityPrefix = entityPrefix;
    }

    @Override
    public PackageReference packageName(final TypeElement element) {
        return this.packageReference;
    }

    @Override
    public String type(final ExtendedTypeElement element) {
        final String prefix = element.isEntity() ? this.entityPrefix : StringUtils.EMPTY;
        return prefix + element.getSimpleName() + this.typeNameSuffix;
    }

    @Override
    public String method(final String propertyName, final String prefix, final String suffix) {
        return "with" + capitalize(prefix) + capitalize(propertyName) + capitalize(suffix);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("packageReference", this.packageReference)
                .add("typeNameSuffix", this.typeNameSuffix)
                .add("entityPrefix", this.entityPrefix)
                .toString();
    }
}
