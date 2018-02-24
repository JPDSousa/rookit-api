package org.rookit.api.dm.parser;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.IndexOptions;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.utils.IndexType;
import org.rookit.api.dm.RookitModel;

@Entity("TrackFormat")
@SuppressWarnings("javadoc")
public interface TrackFormat extends RookitModel {

	int getOccurrences();

	String getValue();

}
