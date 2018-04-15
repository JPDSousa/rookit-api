
package org.rookit.api.dm.artist.key;

import org.rookit.api.dm.artist.TypeGender;

@SuppressWarnings("javadoc")
public interface MusicianKeySetter<T> {

    T setFullName(String fullName);

    T setGender(TypeGender gender);

}
