package org.infinispan;

/**
 * // TODO: Document this
 *
 * @author Radim Vansa &lt;rvansa@redhat.com&gt;
 */
public class CallInterceptor implements Interceptor {
   @Override
   public void up(Context context) {
      context.invokeUpInterceptor();
   }

   @Override
   public void down(Context context) {
      try {
         Object result = context.getCommand().perform(context);
         context.setResult(result);
         up(context);
      } catch (Exception e) {
         context.setError(e);
         up(context);
      }
   }
}
