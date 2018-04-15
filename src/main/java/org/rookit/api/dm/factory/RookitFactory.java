
package org.rookit.api.dm.factory;

import java.util.Map;

import org.rookit.api.dm.key.Key;

@SuppressWarnings("javadoc")
public interface RookitFactory<T, K extends Key> {

    T create(Map<String, Object> data);
    
    T create(K key);

    T createEmpty();

}
