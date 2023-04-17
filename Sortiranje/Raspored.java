package sortiranje;

public class Raspored implements Comparable <Raspored>{
	private final int redniBroj;
	private final int pocetak;
	private final int kraj;
	private final String sala;
	private final int godina;
	private final String ime;
	
	public Raspored(int redniBroj, int pocetak, int kraj, String sala, int godina, String ime) {
		super();
		this.redniBroj = redniBroj;
		this.pocetak = pocetak;
		this.kraj = kraj;
		this.sala = sala;
		this.godina = godina;
		this.ime = ime;
	}

//	public int getRedniBroj() {
//		return redniBroj;
//	}

	public int getPocetak() {
		return pocetak;
	}

	public int getKraj() {
		return kraj;
	}

	public String getSala() {
		return sala;
	}

	public int getGodina() {
		return godina;
	}

//	public String getIme() {
//		return ime;
//	}

	@Override
	public String toString() {
		return "Raspored [redniBroj=" + redniBroj + ", pocetak=" + pocetak + ", kraj=" + kraj + ", sala=" + sala
				+ ", godina=" + godina + ", ime=" + ime + "]";
	}

	@Override
	public int compareTo(Raspored that) {
		// TODO Auto-generated method stub
		int rezultat=0;
		
		if(this.getRedniBroj()>that.getRedniBroj())
			rezultat=1;
		if(this.getRedniBroj()<that.getRedniBroj())
			rezultat=-1;
		
		
		if(rezultat==0) {
			if(this.getPocetak()>that.getPocetak())
				rezultat=1;
			if(this.getPocetak()<that.getPocetak())
				rezultat=-1;
		}
		
		if(rezultat==0) {
			if(this.getKraj()>that.getKraj())
				rezultat=1;
			if(this.getKraj()<that.getKraj())
				rezultat=-1;
		}
		
		if(rezultat==0) {
			rezultat=this.getSala().compareTo(that.getSala());
		}
		
		return rezultat;
	}

	public int getRedniBroj() {
		// TODO Auto-generated method stub
		return redniBroj;
	}

	public String getIme() {
		// TODO Auto-generated method stub
		return ime;
	}
	
	
	
	
	
	
	
	
}
	
	
	


