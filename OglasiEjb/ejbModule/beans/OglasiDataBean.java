package beans;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Ogla;

/**
 * Session Bean implementation class OglasiDataBean
 */
@Singleton
@LocalBean
public class OglasiDataBean {
	
	@PersistenceContext
	EntityManager em;
	
	@Resource
	TimerService ts;
	private Timer tm;

	private Map<Integer, Integer> brPregleda = new HashMap<>();
	private Map<Integer, Integer> brJavljanja = new HashMap<>();

	public Map<Integer, Integer> getBrPregleda() {
		return brPregleda;
	}

	public void setBrPregleda(Map<Integer, Integer> brPregleda) {
		this.brPregleda = brPregleda;
	}

	public Map<Integer, Integer> getBrJavljanja() {
		return brJavljanja;
	}

	public void setBrJavljanja(Map<Integer, Integer> brJavljanja) {
		this.brJavljanja = brJavljanja;
	}

	public void AddPregled(int id) {
		this.startTimer();
		if (brPregleda.containsKey(id)) {
			brPregleda.put(id, brPregleda.get(id) + 1);
		} else {

			brPregleda.put(id, 1);
		}
		System.out.println("Added pregled za oglas sa id :"+id);

	}

	public void AddJavljanje(int id) {
		if (brJavljanja.containsKey(id)) {
			brJavljanja.put(id, brJavljanja.get(id) + 1);
		} else {

			brJavljanja.put(id, 1);
		}
	}

	/**
	 * Default constructor.
	 */
	public OglasiDataBean() {
		// TODO Auto-generated constructor stub
	}
	
	@Schedule(second = "59", minute = "59", hour = "23",dayOfWeek="*", persistent = false)
	public void ispisPregledaNaKrajuDana() {
		for(Map.Entry<Integer, Integer> entry : brPregleda.entrySet()) {
		    System.out.println("Broj pregleda za danas" + entry.getValue() + "Za oglas " + entry.getKey());
		}
	}
	
	@Timeout
	public void updateFields(Timer t) {
		for(Map.Entry<Integer, Integer> entry : brPregleda.entrySet()) {
			Ogla oglas = em.find(Ogla.class, entry.getKey());
			oglas.setBrojPregleda(entry.getValue()+oglas.getBrojPregleda());
			em.persist(oglas);
		    System.out.println("Br pregleda" + entry.getValue() + "Za oglas " + entry.getKey());
		}
		this.reset();
	}
	
	public void startTimer() {
		
		TimerConfig config = new TimerConfig();
        config.setPersistent(false);
        tm = ts.createIntervalTimer(0, 9000, config);
        
	}
	
	public void reset() {
		brJavljanja = new HashMap<>();
		brPregleda = new HashMap<>();
	}
	

}
