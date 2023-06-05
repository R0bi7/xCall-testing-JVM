/*
 * Copyright 2021 ICONLOOP Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.protokol7.utils;

import java.math.BigInteger;

import score.Context;
import score.DictDB;
import score.VarDB;

public class Map256<K, V> {
    protected final DictDB<K, V> values;
    protected final VarDB<BigInteger> size;

    public Map256(String id, Class<K> keyClass, Class<V> valueClass) {
        this.values = Context.newDictDB(id + "_values", valueClass);
        this.size = Context.newVarDB(id + "_size", BigInteger.class);
    }

    public BigInteger size() {
        return this.size.get();
    }

    public V get(K key) {
        return values.get(key);
    }

    public V getOrDefault(K key, V value) {
        var entry = this.get(key);
        return entry != null ? entry : value;
    }

    public void set(K key, V value) {
        BigInteger size = this.size.get();
        V oldValue = values.get(key);

        if (oldValue == null && value != null) {
            // Add a new item
            this.size.set(size.add(BigInteger.ONE));
        }
        else if (oldValue != null && value == null) {
            // Remove an existing item
            this.size.set(size.subtract(BigInteger.ONE));
        }

        values.set(key, value);
    }
}