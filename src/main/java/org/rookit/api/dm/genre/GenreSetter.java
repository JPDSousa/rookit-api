
package org.rookit.api.dm.genre;

@SuppressWarnings("javadoc")
public interface GenreSetter<T> {

    T resetDescription();
    
    T setDescription(String description);

}
