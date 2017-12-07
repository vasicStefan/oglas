package beans;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class InterceptorJavljanjaNaOglas {
	
	@EJB
	OglasiDataBean odb;
	
	@AroundInvoke
	public Object intercept(InvocationContext context) throws Exception {
		
		int id=(int)context.getParameters()[1];
		try {
			//int idOglasa=Integer.parseInt(id);
			odb.AddJavljanje(id);
			System.out.println("Dodo sam");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		
		Object result = context.proceed();
		 

		return result;
	}

}
