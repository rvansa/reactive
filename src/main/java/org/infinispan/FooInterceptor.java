package org.infinispan;

/**
 * // TODO: Document this
 *
 * @author Radim Vansa &lt;rvansa@redhat.com&gt;
 */
public class FooInterceptor implements Interceptor {
   @Override
   public void up(Context context) {
      Foo foo = context.retrieve();
      context.invokeUpInterceptor();
   }

   @Override
   public void down(Context context) {
      context.store(new Foo());
      context.invokeDownInterceptor();
   }

   private class Foo {
      int bar;
   }
}
