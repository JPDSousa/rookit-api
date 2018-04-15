
package org.rookit.api.storage.utils;

import java.util.Collection;

@SuppressWarnings("javadoc")
public interface Order {

    enum TypeOrder {
        ASC,
        DSC
    }

    void addField(FieldOrder criteria);
    
    void addField(String fieldName, TypeOrder order);

    Collection<FieldOrder> getFields();

}
