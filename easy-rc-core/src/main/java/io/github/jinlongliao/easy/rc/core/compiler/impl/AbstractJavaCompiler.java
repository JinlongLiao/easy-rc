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
package io.github.jinlongliao.easy.rc.core.compiler.impl;


import io.github.jinlongliao.easy.rc.common.util.ClassUtils;
import io.github.jinlongliao.easy.rc.core.compiler.Compiler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 抽象实现类，执行通用JAVA 代码编译 处理逻辑
 *
 * @author liaojinlong
 * @since 2020/9/11 16:53
 */
public abstract class AbstractJavaCompiler implements Compiler {
    private static final String suffix = "}";

    private static final Pattern PACKAGE_PATTERN = Pattern.compile("package\\s+([$_a-zA-Z][$_a-zA-Z0-9\\.]*);");

    private static final Pattern CLASS_PATTERN = Pattern.compile("class\\s+([$_a-zA-Z][$_a-zA-Z0-9]*)\\s+");

    @Override
    public Class<?> compile(String code, ClassLoader classLoader) {
        code = code.trim();
        Matcher matcher = PACKAGE_PATTERN.matcher(code);
        String pkg;
        if (matcher.find()) {
            pkg = matcher.group(1);
        } else {
            pkg = "";
        }
        matcher = CLASS_PATTERN.matcher(code);
        String cls;
        if (matcher.find()) {
            cls = matcher.group(1);
        } else {
            throw new IllegalArgumentException("No such class name in " + code);
        }
        String className = pkg != null && pkg.length() > 0 ? pkg + "." + cls : cls;
        try {
            return Class.forName(className, true, ClassUtils.getCallerClassLoader(getClass()));
        } catch (ClassNotFoundException e) {
            if (!code.endsWith(suffix)) {
                throw new IllegalStateException("The java code not endsWith \"}\", code: \n" + code + "\n");
            }
            try {
                return doCompile(className, code);
            } catch (RuntimeException t) {
                throw t;
            } catch (Throwable t) {
                throw new IllegalStateException("Failed to compile class, cause: "
                        + t.getMessage()
                        + ", class: "
                        + className
                        + ", code: \n"
                        + code
                        + "\n, stack: "
                        + ClassUtils.toString(t));
            }
        }
    }

    /**
     * 编译
     *
     * @param name   类名
     * @param source 源码
     * @return Class
     * @throws Throwable
     */
    protected abstract Class<?> doCompile(String name, String source) throws Throwable;

}
