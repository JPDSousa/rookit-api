
package org.rookit.api.dm;

import java.util.Map;

@SuppressWarnings("javadoc")
public interface MetadataHolder extends RookitModel, MetadataHolderSetter<Void> {

    String EXTERNAL_META = "external_meta";

    Map<String, Map<String, Object>> getExternalMetadata();
    
    Map<String, Object> getExternalMetadata(String key);

}
