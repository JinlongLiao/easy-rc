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


import io.github.jinlongliao.easy.rc.common.enums.CompilerName;
import io.github.jinlongliao.easy.rc.core.compiler.Compiler;
import io.github.jinlongliao.easy.rc.core.compiler.CompilerFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author liaojinlong
 * @since 2020/9/11 23:28
 */
public class GroovyCompilerTest {
    public static final String GROOVY = "package io.github.jinlongliao.easy.rc.groovy.compiler\n" +
            "\n" +
            "class A {\n" +
            "    void say(String name) {\n" +
            "        println(\"Groovy ：\" + name)\n" +
            "    }\n" +
            "}";

    @Test
    public void compileGroovy() {
        final Compiler compiler = CompilerFactory.getInstance().getCompiler(CompilerName.GROOVY.name());
        final Class<?> aClass = compiler.compile(GROOVY, getClass().getClassLoader());
        Assert.assertEquals("Class Name 对比", aClass.getName(), "io.github.jinlongliao.easy.rc.groovy.compiler.A");
    }

}
