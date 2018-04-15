
package org.rookit.api.dm;

import java.util.Map;

@SuppressWarnings("javadoc")
public interface MetadataHolderSetter<V> {

    V putExternalMetadata(String key, Map<String, Object> value);

}
