package org.rookit.api.dm.artist;

import java.util.Collection;

@SuppressWarnings("javadoc")
public interface GroupArtistSetter<T> {

	T setGroupType(TypeGroup type);

	T setMembers(Collection<Musician> members);

	T addMember(Musician member);

	T addMembers(Collection<Musician> members);

	T removeMember(Musician member);

	T removeMembers(Collection<Musician> members);

	T clearMembers();

}