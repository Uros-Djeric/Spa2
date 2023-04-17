package sortiranje;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.Scanner;

public class Sortiranje {
	
	
	
	public static Raspored[] ucitaj(String fajl) throws IOException {
		
		BufferedReader br=new BufferedReader(new FileReader(fajl));
		
		int brLinija=Integer.parseInt(br.readLine());
		
		Raspored[]niz=new Raspored[brLinija];
		
		for(int i=0;i<niz.length;i++) {
			br.readLine();
			int redniBroj=Integer.parseInt(br.readLine());
			int pocetak=Integer.parseInt(br.readLine());
			int kraj=Integer.parseInt(br.readLine());
			String sala=br.readLine();
			int godina=Integer.parseInt(br.readLine());
			String ime=br.readLine();
			niz[i]=new Raspored(redniBroj,pocetak,kraj,sala,godina,ime);
			
		}
		br.close();
		return niz;
		
		
		
	}
	
	public static void snimi(Raspored[]niz,String fajl) throws IOException {
		PrintWriter pr=new PrintWriter(fajl);
		
		for(int i=0;i<niz.length;i++) {
			pr.println();
			pr.println(niz[i].getRedniBroj());
			pr.println(niz[i].getPocetak());
			pr.println(niz[i].getKraj());
			pr.println(niz[i].getSala());
			pr.println(niz[i].getGodina());
			pr.println(niz[i].getIme());
			
		}
		pr.close();
	}
	
	public static void stampaj(Raspored[]niz) {
		for(Raspored r:niz) {
			System.out.println(r);
		}
	}
	
	public static void sortirajBuble(Raspored[]niz,Comparator<Raspored>s) {
		Raspored tmp;
		
		for(int i=niz.length-1;i>0;i--) {
			for(int j=0;j<j;j++) {
				if(s.compare(niz[j], niz[i])>0) {
					tmp=niz[j];
					niz[j]=niz[i];
					niz[i]=tmp;
					
				}
			}
		}
	}
	
	public static void sortirajInsertion(Raspored[]niz,Comparator<Raspored>s) {
		Raspored tekuci;
		for(int i=1;i<niz.length;i++) {
			if(s.compare(niz[i], niz[i-1])<0) {
				tekuci=niz[0];
				int j=i-1;
				while(j>=0 && s.compare(niz[j], tekuci)>0) {
					niz[j+1]=niz[j];
					j--;
				}
				niz[j+1]=tekuci;
			}
		}
		
	}
	
	public static void sortirajSelection(Raspored[]niz,Comparator<Raspored>s) {
		Raspored tmp;
		for(int i=niz.length-1;i>=1;i--) {
			int maxIndex=0;
			for(int j=1;j<=i;j++) {
				if(s.compare(niz[maxIndex], niz[j])<0)
					maxIndex=j;
			}
			
			if(maxIndex!=i) {
				tmp=niz[i];
				niz[i]=niz[maxIndex];
				niz[maxIndex]=tmp;
			}
		}
	}
	
	public static void main(String[]args) throws IOException {
		
		Scanner skener=new Scanner(System.in);
		Raspored[]niz=ucitaj("res/r.txt/");
		
		if(niz!=null) {
			System.out.println("Opcija 1: Po imenu predmeta");
		    System.out.println("Opcija 2: Po godini studija, danu u nedelji, satima");
		    System.out.println("Opcija 3: Po salama, po danu, po satu,");
		    System.out.println("Opcija 4: Po duzini predavanja,");
		    System.out.println("Za ostale opcije niz nece biti sortiran");
		    System.out.print("Unesite opciju 1-3:");
		    int opcija=skener.nextInt();
		    skener.close();
		    
		    switch(opcija){
		    case 1:
				sortirajBuble(niz, new KomparatorPoImenuPredmeta());
				break;
			case 2:
				sortirajBuble(niz, new KomparatorPoGodiniDanuISatima());
				break;
			case 3:
				//sortirajNiz(niz, new KomparatorPoSalamaPoDanuPoSatu());
				break;
			case 4:
				//sortirajNiz(niz, new KomparatorPoDuziniPredavanja());
				break;
			default:
				System.out.println("Pogresna opcija! Niz nece biti sortiran.");
		    	
		    }
		    stampaj(niz);
		    
		    snimi(niz,"ratluk.txt");
		}
	}
	
	
	

}

class KomparatorPoImenuPredmeta implements Comparator<Raspored>{

	@Override
	public int compare(Raspored ovo, Raspored ono) {
		// TODO Auto-generated method stub
		int rez=ovo.getIme().compareTo(ono.getIme());
		return rez;
	}
	
	
}


class KomparatorPoDanu implements Comparator<Raspored>{

	@Override
	public int compare(Raspored ovo, Raspored ono) {
		// TODO Auto-generated method stub
		return ovo.getRedniBroj()-ono.getRedniBroj();
	}
	
}

class KomparatorPoSatima implements Comparator<Raspored>{

	@Override
	public int compare(Raspored ovo, Raspored ono) {
		// TODO Auto-generated method stub
		return ovo.getPocetak()-ono.getPocetak();
	}
	
}

class KomparatorPoGodini implements Comparator<Raspored>{

	@Override
	public int compare(Raspored ovo, Raspored ono) {
		// TODO Auto-generated method stub
		return ovo.getGodina()-ono.getGodina();
	}
	
}

class KomparatorPoSatimaKraj implements Comparator<Raspored>{

	@Override
	public int compare(Raspored ovo, Raspored ono) {
		// TODO Auto-generated method stub
		return ovo.getKraj()-ono.getKraj();
	}
	
}

class KompozitniKomparator implements Comparator<Raspored>{
	
	private final Comparator<Raspored> primarni;
	private final Comparator<Raspored> sekundarni;
	
	public KompozitniKomparator(Comparator<Raspored> primarni, Comparator<Raspored> sekundarni) {
		super();
		this.primarni = primarni;
		this.sekundarni = sekundarni;
	}

	@Override
	public int compare(Raspored ovo, Raspored ono) {
		// TODO Auto-generated method stub
		int rez=primarni.compare(ovo,ono);
		if(rez==0) {
			rez=sekundarni.compare(ovo, ono);
		}
		return rez;
	}
	
	
	
	
	
}

class KomparatorPoDanuISatima extends KompozitniKomparator{

	public KomparatorPoDanuISatima() {
		super(new KomparatorPoGodini(), new KomparatorPoSatima());
		// TODO Auto-generated constructor stub
	}
	
}

class KomparatorPoGodiniDanuISatima extends KompozitniKomparator{

	public KomparatorPoGodiniDanuISatima() {
		super(new KomparatorPoDanuISatima(), new KomparatorPoGodini());
		// TODO Auto-generated constructor stub
	}
	
}


