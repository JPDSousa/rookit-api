package org.rookit.api.storage;

import org.rookit.api.dm.RookitModel;
import org.rookit.api.storage.query.RookitModelQuery;
import org.rookit.api.storage.update.RookitModelUpdateFilterQuery;
import org.rookit.api.storage.update.RookitModelUpdateQuery;

public interface RookitModelDataStore<T extends RookitModel,
        Q extends RookitModelQuery<T, Q>,
        U extends RookitModelUpdateQuery<F>,
        F extends RookitModelUpdateFilterQuery<F>> extends DataSource<T, Q> {

    String name();

    void insert(T element);

    void replace(T element);

    U update();

    void delete(T element);

    void clear();
}
