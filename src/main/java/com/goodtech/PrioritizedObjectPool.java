package com.goodtech;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C) 2012 by Scott Byrns
 * http://github.com/scottbyrns
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 * <p/>
 * Created 5/17/12 6:06 PM
 */

/**
 * An object pool that registers objects by priority and returns them in order of priority.
 *
 * @param <T> Type of object to hold in the pool.
 */
public class PrioritizedObjectPool<T>
{
    private Map<Priority, List<T>> priorityMap;
    private Priority[] priorities = {Priority.CRITICAL, Priority.HIGH, Priority.MEDIUM, Priority.LOW, Priority.WHENEVER};

    public PrioritizedObjectPool()
    {
        priorityMap = new HashMap<Priority, List<T>>();
        recycle();
    }

    /**
     * The next object with the highest priority.
     *
     * @return The object with the highest priority. Null if the pool is empty.
     */
    public T next () {
        if (null != getNextPopulatedPriorityList()) {
            T t = getNextPopulatedPriorityList().get(0);
            if (null != t) {
                getNextPopulatedPriorityList().remove(t);
                return t;
            }
        }
        return null;
    }

    /**
     * Add an object to the pool.
     *
     * @param object The object to add to the pool.
     * @param priority The priority in which this object should be returned.
     */
    public void add (T object, Priority priority) {
        priorityMap.get(priority).add(object);
    }

    /**
     * Recycle the pool.
     * This will dump all objects from the pool.
     */
    public void recycle () {
        priorityMap.clear();
        for (Priority priority : priorities) {
            priorityMap.put(priority, new ArrayList<T>());
        }
    }

    private List<T> getNextPopulatedPriorityList () {
        priorityMap.get(Priority.CRITICAL);
        for (Priority priority : priorities) {
            if (priorityMap.get(priority).size() > 0) {
                return priorityMap.get(priority);
            }
        }
        return null;
    }
}
