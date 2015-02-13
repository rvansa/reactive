package org.infinispan;

import java.util.function.BiFunction;

/**
 * // TODO: Document this
 *
 * @author Radim Vansa &lt;rvansa@redhat.com&gt;
 */
public class Context {
   Interceptor[] interceptors;
   Object[] data;
   int currentInterceptor;
   Command command;
   Object result;
   Throwable error;

   BiFunction<Object, Throwable, Void> handler;

   void invoke(Command command, BiFunction<Object, Throwable, Void> handler) {
      this.command = command;
      this.handler = handler;
      interceptors[0].down(this);
   }

   void invokeUpInterceptor() {
      currentInterceptor--;
      if (currentInterceptor >= 0) {
         interceptors[currentInterceptor].up(this);
      } else {
         handler.apply(result, error);
      }
   }

   void invokeDownInterceptor() {
      currentInterceptor++;
      interceptors[currentInterceptor].down(this);
   }

   public Command getCommand() {
      return command;
   }

   public Object getResult() {
      return result;
   }

   public void setResult(Object result) {
      this.result = result;
   }

   public Throwable getError() {
      return error;
   }

   public void setError(Throwable error) {
      this.error = error;
   }

   void store(Object foo) {
      data[currentInterceptor] = foo;
   }

   <T> T retrieve() {
      return (T) data[currentInterceptor];
   }

   public void setCommand(Command command) {
      this.command = command;
   }
}
