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
package org.rookit.api.convention.source;

import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.util.Modules;
import org.rookit.api.convention.source.entity.EntityModule;
import org.rookit.api.convention.source.field.FieldModule;
import org.rookit.api.convention.source.method.MethodModule;
import org.rookit.api.convention.source.type.TypeSpecModule;
import org.rookit.auto.EntityHandler;
import org.rookit.auto.StatelessEntityHandler;
import org.rookit.auto.annotation.Noop;
import org.rookit.auto.entity.EntityFactory;
import org.rookit.auto.entity.PartialEntityFactory;
import org.rookit.auto.entity.noop.NoopPartialEntityFactory;
import org.rookit.auto.entity.noop.NoopEntityFactory;
import org.rookit.auto.identifier.BaseIdentifierFactory;
import org.rookit.auto.identifier.IdentifierFactory;
import org.rookit.auto.javapoet.naming.JavaPoetNamingFactory;
import org.rookit.auto.javapoet.naming.JavaPoetParameterResolver;
import org.rookit.auto.javapoet.naming.NoParamsJavaPoetParameterResolver;
import org.rookit.auto.javax.BaseExtendedPropertyFactory;
import org.rookit.auto.javax.BasePropertyExtractor;
import org.rookit.auto.javax.ExtendedPropertyFactory;
import org.rookit.auto.javax.PropertyExtractor;
import org.rookit.auto.javax.element.ElementUtils;
import org.rookit.auto.naming.NamingFactory;

import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;

@SuppressWarnings("MethodMayBeStatic")
public final class SourceModule extends AbstractModule {

    private static final Module MODULE = Modules.combine(new SourceModule(),
            MethodModule.getModule(),
            TypeSpecModule.getModule(),
            EntityModule.getModule(),
            FieldModule.getModule());

    public static Module getModule() {
        return MODULE;
    }

    private SourceModule() {}

    @Override
    protected void configure() {
        bind(JavaPoetNamingFactory.class).to(EntityPropertiesNamingFactory.class).in(Singleton.class);

        // yes it can!
        //noinspection UninstantiableBinding
        bind(NamingFactory.class).to(JavaPoetNamingFactory.class).in(Singleton.class);
    }

    @Singleton
    @Provides
    JavaPoetParameterResolver namingFactory(final JavaPoetNamingFactory namingFactory) {
        return NoParamsJavaPoetParameterResolver.create(namingFactory);
    }

    @Singleton
    @Provides
    EntityHandler conventionHandler(final EntityFactory entityFactory,
                                    final ElementUtils utils,
                                    final Filer filer) {
        return StatelessEntityHandler.create(entityFactory, utils, filer);
    }

    @Singleton
    @Provides
    PropertyExtractor methodExtractor(final ExtendedPropertyFactory propertyFactory) {
        return BasePropertyExtractor.create(propertyFactory);
    }

    @Singleton
    @Provides
    ExtendedPropertyFactory propertyFactory(final ElementUtils utils) {
        return BaseExtendedPropertyFactory.create(utils);
    }

    @Singleton
    @Provides
    IdentifierFactory identifierFactory(final NamingFactory namingFactory) {
        return BaseIdentifierFactory.create(namingFactory);
    }

    @Singleton
    @Provides
    @Noop
    EntityFactory noopFactory(final Messager messager,
                              final IdentifierFactory identifierFactory,
                              final PartialEntityFactory partialEntityFactory) {
        return NoopEntityFactory.create(messager, identifierFactory, partialEntityFactory);
    }

    @Singleton
    @Provides
    @Noop
    PartialEntityFactory noopPartialFactory(final IdentifierFactory identifierFactory) {
        return NoopPartialEntityFactory.create(identifierFactory);
    }
}
