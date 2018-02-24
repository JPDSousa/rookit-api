package org.rookit.api.storage.utils;

@SuppressWarnings("javadoc")
public interface Order {
	
	enum TypeOrder {
		ASC,
		DSC
	}

	void addField(FieldOrder criteria);
	void addField(String fieldName, TypeOrder order);
	
	Iterable<FieldOrder> getFields();
	
}
