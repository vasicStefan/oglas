package beans;

import java.util.List;

import model.Ogla;
import model.OglasKorisnik;
import model.OglasPrijava;

public interface OglasBeanRemote {

	boolean CheckUser(String username, String password);
	
	OglasKorisnik GetUser(String username, String password);
	
	List<Ogla> SearchOgla(String searchText);
	
	boolean AddOglas(Ogla o);
	
	boolean OglasPrijavljivanje(OglasKorisnik ok,int idOglasa);
	
	
}
