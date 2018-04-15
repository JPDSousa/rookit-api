
package org.rookit.api.dm.artist;

import java.util.Collection;

@SuppressWarnings("javadoc")
public interface GroupArtistSetter<T> {

    T addMember(Musician member);

    T addMembers(Collection<Musician> members);

    T clearMembers();

    T removeMember(Musician member);

    T removeMembers(Collection<Musician> members);

    T setMembers(Collection<Musician> members);

}
