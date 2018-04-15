
package org.rookit.api.dm;

import org.bson.types.ObjectId;

@SuppressWarnings("javadoc")
public interface RookitModelSetter<V> {

    V setId(ObjectId id);

}
