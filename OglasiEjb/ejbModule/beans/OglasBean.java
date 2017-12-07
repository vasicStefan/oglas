package beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Ogla;
import model.OglasKorisnik;
import model.OglasPrijava;

/**
 * Session Bean implementation class login
 */
@Stateful
@LocalBean
@Interceptors(Interceptor4All.class)
public class OglasBean implements OglasBeanRemote {

	@PersistenceContext
	EntityManager em;
	
    /**
     * Default constructor. 
     */
    public OglasBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public boolean CheckUser(String username, String password) {
		// TODO Auto-generated method stub
		
		try {
			
			Query q=em.createQuery("SELECT o FROM OglasKorisnik o where o.username= :username and o.password= :password");
			q.setParameter("username", username);
			q.setParameter("password", password);
			return q.getResultList().get(0)!=null;
				
						
			//OglasKorisnik o=(OglasKorisnik)q.getResultList().get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
			return  false;
		}
		
	}

	@Override
	public OglasKorisnik GetUser(String username, String password) {
try {
			
			Query q=em.createQuery("SELECT o FROM OglasKorisnik o where o.username= :username and o.password= :password");
			q.setParameter("username", username);
			q.setParameter("password", password);
			return (OglasKorisnik)q.getResultList().get(0);
				
						
			//OglasKorisnik o=(OglasKorisnik)q.getResultList().get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
			return  null;
		}
	}

	@Override
	@Interceptors(InterceptorBrPregleda.class)
	public List<Ogla> SearchOgla(String searchText) {
		// TODO Auto-generated method stub
		//TODO: mozda '' problem sa %%
		try {
			Query q=em.createQuery("SELECT o FROM Ogla o where o.text like  :text");
			q.setParameter("text", "%"+searchText+"%");
			return  (List<Ogla>)q.getResultList();
		
		} catch (Exception e) {
			e.printStackTrace();
		return null;
		}
	}

	@Override
	public boolean AddOglas(Ogla o) {
		try {
			em.persist(o);
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		return false;
		}
	}

	@Override
	@Interceptors(InterceptorJavljanjaNaOglas.class)
	public boolean OglasPrijavljivanje(OglasKorisnik ok,int idOglasa) {
		try {
			OglasPrijava op=new OglasPrijava();
			op.setOgla(em.find(Ogla.class, idOglasa));
			op.setOglasKorisnik(ok);
			
			em.persist(op);
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		return false;
		}
	}

}
