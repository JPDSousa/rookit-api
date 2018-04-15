
package org.rookit.api.bistream;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

@SuppressWarnings("javadoc")
public interface BiStream extends Serializable {

    void clear();

    boolean isEmpty();

    InputStream toInput();

    OutputStream toOutput();
}
