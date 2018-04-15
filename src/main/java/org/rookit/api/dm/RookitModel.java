
package org.rookit.api.dm;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.bson.types.ObjectId;

@SuppressWarnings("javadoc")
public interface RookitModel extends RookitModelSetter<Void>, Serializable {

    String ID = "_id";

    ObjectId getId();

    String getIdAsString();

    LocalDateTime getStorageTime();

}
