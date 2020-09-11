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
package io.github.jinlongliao.easy.rc.core.compiler;


import io.github.jinlongliao.easy.rc.common.enums.CompilerName;
import io.github.jinlongliao.easy.rc.common.util.StringUtils;

import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Compiler 工厂
 *
 * @author liaojinlong
 * @since 2020/9/11 22:51
 */
public class CompilerFactory {
    private static final Map<String, Compiler> compilersCache = new ConcurrentHashMap();
    private static final Compiler DEFAULT;

    static {
        final ServiceLoader<Compiler> load = ServiceLoader.load(Compiler.class);
        load.forEach(item -> {
            compilersCache.put(item.name(), item);
        });
        DEFAULT = compilersCache.get(CompilerName.JDK.name());
    }

    private static CompilerFactory instance = null;

    private CompilerFactory() {
    }

    public static CompilerFactory getInstance() {
        if (instance == null) {
            //双重检查加锁，只有在第一次实例化时，才启用同步机制，提高了性能。
            synchronized (CompilerFactory.class) {
                if (instance == null) {
                    instance = new CompilerFactory();
                }
            }
        }
        return instance;
    }

    /**
     * Compiler
     *
     * @param compilerName 编译类唯一标识
     * @return Compiler
     */
    public Compiler getCompiler(String compilerName) {
        if (StringUtils.isBlank(compilerName)) {
            return DEFAULT;
        }
        return compilersCache.getOrDefault(compilerName, DEFAULT);
    }
}
