
package org.rookit.api.storage.update;

import org.rookit.api.storage.queries.filter.RookitFilter;

@SuppressWarnings("javadoc")
public interface RookitUpdateFilterQuery<Q extends RookitUpdateFilterQuery<Q>> extends RookitFilter<Q> {

    void execute();

}
