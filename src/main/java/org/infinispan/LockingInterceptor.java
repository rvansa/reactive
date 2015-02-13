package org.infinispan;

import java.util.concurrent.CompletionStage;

/**
 * // TODO: Document this
 *
 * @author Radim Vansa &lt;rvansa@redhat.com&gt;
 */
public class LockingInterceptor implements Interceptor {
   Lock lock;

   @Override
   public void up(Context context) {
      lock.unlock();
      context.invokeUpInterceptor();
   }

   @Override
   public void down(final Context context) {
      CompletionStage<Void> stage = lock.lockWithCompletion();
      stage.handle((ignored, throwable) -> {
         if (throwable == null) {
            context.invokeDownInterceptor();
         } else {
            context.invokeUpInterceptor();
         }
         return null;
      });
   }
}
