
package org.rookit.api.dm.parser;

import org.mongodb.morphia.annotations.Entity;
import org.rookit.api.dm.RookitModel;

@Entity("TrackFormat")
@SuppressWarnings("javadoc")
public interface TrackFormat extends RookitModel {

    int getOccurrences();

    String getValue();

}
