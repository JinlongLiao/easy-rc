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
package io.github.jinlongliao.easy.rc.core.invoke;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Modifier;


/**
 * 公共判断
 *
 * @author liaojinlong
 * @since 2020/9/12 10:06
 */
public abstract class AbstractClassInvoke implements ClassInvoke {
    private static final Logger log = LoggerFactory.getLogger(AbstractClassInvoke.class);

    @Override
    public <T> T toInstance(Class<T> tClass, Class<?>[] paramType, Object... args) throws ReflectiveOperationException {
        T t;
        final boolean ab = Modifier.isInterface(tClass.getModifiers()) || Modifier.isAbstract(tClass.getModifiers());
        if (ab) {
            log.error(tClass.getName() + " Cant be Instant!!!");
            return null;
        } else {
            t = getInstance(tClass, paramType, args);
        }
        return t;
    }

    /**
     * 实例化
     *
     * @param tClass
     * @param paramType
     * @param args
     * @param <T>
     * @return /
     * @throws ReflectiveOperationException
     */
    protected abstract <T> T getInstance(Class<T> tClass, Class<?>[] paramType, Object[] args) throws ReflectiveOperationException;
}
