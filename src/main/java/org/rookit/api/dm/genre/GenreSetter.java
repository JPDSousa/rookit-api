package org.rookit.api.dm.genre;

@SuppressWarnings("javadoc")
public interface GenreSetter<T> {

	T setDescription(String description);
	T resetDescription();

}
