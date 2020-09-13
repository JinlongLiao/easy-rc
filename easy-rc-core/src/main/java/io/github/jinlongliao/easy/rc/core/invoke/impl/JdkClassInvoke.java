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
package io.github.jinlongliao.easy.rc.core.invoke.impl;

import io.github.jinlongliao.easy.rc.core.invoke.AbstractClassInvoke;

import java.util.Objects;


/**
 * JDK原生 实例化
 *
 * @author liaojinlong
 * @since 2020/9/12 10:06
 */
public class JdkClassInvoke extends AbstractClassInvoke {
    @Override
    public <T> T getInstance(Class<T> tClass, Class<?>[] paramType, Object... args) throws ReflectiveOperationException {
        T t;
        if (Objects.isNull(paramType) || args.length < 1) {
            t = tClass.newInstance();
        } else {
            t = tClass.getDeclaredConstructor(paramType).newInstance(args);
        }
        return t;
    }
}
