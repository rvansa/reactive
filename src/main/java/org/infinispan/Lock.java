package org.infinispan;

import java.util.concurrent.CompletionStage;

/**
 * // TODO: Document this
 *
 * @author Radim Vansa &lt;rvansa@redhat.com&gt;
 */
public interface Lock {
   CompletionStage<Void> lockWithCompletion();
   void unlock();
}
