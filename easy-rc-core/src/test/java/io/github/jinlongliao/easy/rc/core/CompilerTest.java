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
package io.github.jinlongliao.easy.rc.core;

import io.github.jinlongliao.easy.rc.common.enums.CompilerName;
import io.github.jinlongliao.easy.rc.core.compiler.Compiler;
import io.github.jinlongliao.easy.rc.core.compiler.CompilerFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author liaojinlong
 * @since 2020/9/11 23:06
 */
public class CompilerTest {
    public static final String CODE = "package io.github.jinlongliao.easy.rc.core;\n" +
            "\n" +
            "public class A {\n" +
            "    public String say(String name) {\n" +
            "        return \"I AM \" + name;\n" +
            "    }\n" +
            "}";

    @Test
    public void jdkCompiler() {
        final Compiler compiler = CompilerFactory.getInstance().getCompiler(CompilerName.JDK.name());
        final Class<?> aClass = compiler.compile(CODE, getClass().getClassLoader());
        Assert.assertEquals("Class Name 对比", aClass.getName(), "io.github.jinlongliao.easy.rc.core.A");
    }

    @Test
    public void javassitCompiler() {
        final Compiler compiler = CompilerFactory.getInstance().getCompiler(CompilerName.JAVASSIST.name());
        final Class<?> aClass = compiler.compile(CODE, getClass().getClassLoader());
        Assert.assertEquals("Class Name 对比", aClass.getName(), "io.github.jinlongliao.easy.rc.core.A");
    }
}
