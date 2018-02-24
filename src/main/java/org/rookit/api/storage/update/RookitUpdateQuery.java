package org.rookit.api.storage.update;

@SuppressWarnings("javadoc")
public interface RookitUpdateQuery<S extends RookitUpdateFilterQuery<S>> {
	
	S where();

}
