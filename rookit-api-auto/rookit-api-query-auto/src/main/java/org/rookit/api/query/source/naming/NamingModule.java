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

import com.google.inject.Module;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.apache.commons.lang3.StringUtils;
import org.rookit.api.query.source.guice.Filter;
import org.rookit.api.query.source.guice.FilterEntity;
import org.rookit.api.query.source.guice.Query;
import org.rookit.api.query.source.guice.QueryEntity;
import org.rookit.auto.javapoet.naming.JavaPoetNamingFactory;
import org.rookit.auto.naming.AbstractNamingModule;
import org.rookit.auto.naming.BaseJavaPoetNamingFactory;
import org.rookit.auto.naming.PackageReference;
import org.rookit.auto.naming.PackageReferenceFactory;

@SuppressWarnings("MethodMayBeStatic")
public final class NamingModule extends AbstractNamingModule {

    private static final String PACKAGE_API = "api";
    private static final String PACKAGE_STORAGE = "storage";
    private static final String PACKAGE_FILTER = "filter";
    private static final String PACKAGE_QUERY = "query";
    private static final String METHOD_PREFIX = "with";

    private static final Module MODULE = new NamingModule();

    public static Module getModule() {
        return MODULE;
    }

    private NamingModule() {}

    @Override
    protected void configure() {
        bindNaming(Query.class);
        bindNaming(Filter.class);
        bindNaming(QueryEntity.class);
        bindNaming(FilterEntity.class);
    }

    @Singleton
    @Provides
    @Filter
    PackageReference filterPackage(final PackageReferenceFactory packageFactory) {
        return packageFactory.basePackage()
                .concat(PACKAGE_API)
                .concat(PACKAGE_STORAGE)
                .concat(PACKAGE_FILTER);
    }

    @Singleton
    @Provides
    @Query
    PackageReference queryPackage(final PackageReferenceFactory packageFactory) {
        return packageFactory.basePackage()
                .concat(PACKAGE_API)
                .concat(PACKAGE_STORAGE)
                .concat(PACKAGE_QUERY);
    }

    @Singleton
    @Provides
    @Filter
    JavaPoetNamingFactory filterNamingFactory(@Filter final PackageReference packageReference) {
        return BaseJavaPoetNamingFactory.create(packageReference, "Filter", "Generic", METHOD_PREFIX);
    }

    @Singleton
    @Provides
    @Query
    JavaPoetNamingFactory queryNamingFactory(@Query final PackageReference packageReference) {
        return BaseJavaPoetNamingFactory.create(packageReference, "Query", "Generic", METHOD_PREFIX);
    }

    @Singleton
    @Provides
    @FilterEntity
    JavaPoetNamingFactory filterEntityNamingFactory(@Filter final PackageReference packageReference) {
        return BaseJavaPoetNamingFactory.create(packageReference, "Filter", StringUtils.EMPTY, METHOD_PREFIX);
    }

    @Singleton
    @Provides
    @QueryEntity
    JavaPoetNamingFactory queryEntityNamingFactory(@Query final PackageReference packageReference) {
        return BaseJavaPoetNamingFactory.create(packageReference, "Query", StringUtils.EMPTY, METHOD_PREFIX);
    }
}
