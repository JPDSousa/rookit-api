package org.rookit.api.storage.datastore;

import org.rookit.api.dm.RookitModel;
import org.rookit.api.storage.queries.RookitQuery;
import org.rookit.api.storage.update.RookitUpdateFilterQuery;
import org.rookit.api.storage.update.RookitUpdateQuery;

public interface RookitModelDataStore<T extends RookitModel,
        Q extends RookitQuery<Q, T>,
        U extends RookitUpdateQuery<F>,
        F extends RookitUpdateFilterQuery<F>> {

    String name();

    void insert(T element);

    void replace(T element);

    U update(T element);

    void delete(T element);

    void clear();

    Q getAll();
}
