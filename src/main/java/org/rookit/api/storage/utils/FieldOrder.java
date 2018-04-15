
package org.rookit.api.storage.utils;

import org.rookit.api.storage.utils.Order.TypeOrder;

@SuppressWarnings("javadoc")
public final class FieldOrder {

    private final String field;
    private final TypeOrder order;

    public FieldOrder(final String field, final TypeOrder order) {
        super();
        this.field = field;
        this.order = order;
    }

    public String getField() {
        return this.field;
    }

    public TypeOrder getOrder() {
        return this.order;
    }

}
