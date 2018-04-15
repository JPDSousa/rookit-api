
package org.rookit.api.dm.parser;

import org.mongodb.morphia.annotations.Entity;
import org.rookit.api.dm.RookitModel;

@SuppressWarnings("javadoc")
@Entity("IgnoredField")
public interface IgnoredField extends RookitModel {

    int getOccurrences();

    String getValue();

}
