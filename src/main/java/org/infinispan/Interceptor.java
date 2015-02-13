package org.infinispan;

/**
 * // TODO: Document this
 *
 * @author Radim Vansa &lt;rvansa@redhat.com&gt;
 */
public interface Interceptor {
   void up(Context context);
   void down(Context context);

}
