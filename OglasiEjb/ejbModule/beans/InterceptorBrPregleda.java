package beans;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import model.Ogla;

public class InterceptorBrPregleda {

	@EJB
	OglasiDataBean odb;

	@AroundInvoke
	public Object intercept(InvocationContext context) throws Exception {

		ArrayList<Ogla> result = (ArrayList<Ogla>) context.proceed();
		for (Ogla o : result) {
			odb.AddPregled(o.getIdOglas());
		}

		return result;
	}

}
