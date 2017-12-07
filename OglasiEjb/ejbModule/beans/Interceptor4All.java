package beans;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class Interceptor4All {

	
	@AroundInvoke
	public Object intercept(InvocationContext context) throws Exception {
                // Log to console before executing method
		System.out.println("Entering method:" + context.getMethod().getName());
 
                // Execute method
		Object result = context.proceed();
 
                // Log to console after executing method
		System.out.println("Leaving method: " + context.getMethod().getName() );
 
		return result;
	}
	
}
