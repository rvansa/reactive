package org.infinispan;

import java.util.function.BiFunction;

/**
 * // TODO: Document this
 *
 * @author Radim Vansa &lt;rvansa@redhat.com&gt;
 */
public class OtherCommandInterceptor implements Interceptor {
   @Override
   public void up(Context context) {
      Runnable function = context.retrieve();
      function.run();
   }

   @Override
   public void down(final Context context) {
      Command otherCommand = null; // new FooCommand()
      context.store(new OtherCommandUpHandler(context, context.getCommand()));
      context.setCommand(otherCommand);
      context.invokeDownInterceptor();
   }

   private static class OtherCommandUpHandler implements Runnable {
      private final Context context;
      private final Command original;

      public OtherCommandUpHandler(Context context, Command original) {
         this.context = context;
         this.original = original;
      }

      @Override
      public void run() {
         context.setCommand(original);
         if (context.getError() == null) {
            context.store(new FirstCommandHandler(context));
            context.invokeDownInterceptor();
         } else {
            context.invokeUpInterceptor();
         }
      }

   }

   private static class FirstCommandHandler implements Runnable {
      private final Context context;

      public FirstCommandHandler(Context context) {
         this.context = context;
      }

      @Override
      public void run() {
         context.invokeUpInterceptor();
      }
   }
}
