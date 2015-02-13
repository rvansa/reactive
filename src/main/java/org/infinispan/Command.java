package org.infinispan;

/**
 * // TODO: Document this
 *
 * @author Radim Vansa &lt;rvansa@redhat.com&gt;
 */
public interface Command {
   Object perform(Context context);
}
