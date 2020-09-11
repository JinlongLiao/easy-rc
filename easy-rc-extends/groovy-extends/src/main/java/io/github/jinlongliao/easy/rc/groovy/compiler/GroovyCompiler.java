/*
 *  Copyright 2019-2020
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package io.github.jinlongliao.easy.rc.groovy.compiler;

import groovy.lang.GroovyClassLoader;
import io.github.jinlongliao.easy.rc.common.enums.CompilerName;
import io.github.jinlongliao.easy.rc.core.compiler.Compiler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Groovy 编译
 *
 * @author liaojinlong
 * @since 2020/9/11 17:45
 */
public class GroovyCompiler implements Compiler {
    private static final Logger log = LoggerFactory.getLogger(GroovyCompiler.class);
    private static GroovyClassLoader groovyClassLoader;

    /**
     * Compiles groovy class from a String
     *
     * @param code        源代码
     * @param classLoader classloader
     * @return /
     */
    public Class<?> compileGroovy(String code, ClassLoader classLoader) {
        GroovyClassLoader loader = getGroovyClassLoader(classLoader);
        return loader.parseClass(code);
    }

    /**
     * ClassLoader 加载
     *
     * @param classLoader ClassLoader
     * @return a new GroovyClassLoader
     */
    GroovyClassLoader getGroovyClassLoader(ClassLoader classLoader) {
        if (groovyClassLoader == null) {
            synchronized (GroovyCompiler.log) {
                if (groovyClassLoader == null) {
                    groovyClassLoader = new GroovyClassLoader(classLoader);
                }
            }
        }
        return groovyClassLoader;
    }

    @Override
    public Class<?> compile(String code, ClassLoader classLoader) {
        return this.compileGroovy(code, classLoader);
    }

    @Override
    public String name() {
        return CompilerName.GROOVY.name();
    }
}
