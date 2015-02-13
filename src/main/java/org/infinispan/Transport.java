package org.infinispan;

/**
 * // TODO: Document this
 *
 * @author Radim Vansa &lt;rvansa@redhat.com&gt;
 */
public interface Transport {
   void sendResponse(Object result, Throwable throwable);
}
