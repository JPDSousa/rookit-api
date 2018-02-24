package org.rookit.api.storage.utils;

import org.rookit.api.storage.utils.Order.TypeOrder;

@SuppressWarnings("javadoc")
public final class FieldOrder {

	private final String field;
	private final TypeOrder order;
	
	public FieldOrder(String field, TypeOrder order) {
		super();
		this.field = field;
		this.order = order;
	}

	public String getField() {
		return field;
	}

	public TypeOrder getOrder() {
		return order;
	}
	
}
