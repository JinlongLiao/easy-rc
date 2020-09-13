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


/**
 * @author liaojinlong
 * @since 2020/9/12 10:03
 */
public interface ClassInvoke {
    /**
     * 实例化
     *
     * @param tClass    class
     * @param paramType 参数
     * @param args      参数
     * @param <T>       类型
     * @return T 实例
     * @throws ReflectiveOperationException 异常
     */
    <T> T toInstance(Class<T> tClass, Class<?>[] paramType, Object... args) throws ReflectiveOperationException;
}
