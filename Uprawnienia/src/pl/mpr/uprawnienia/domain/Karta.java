package pl.mpr.uprawnienia.domain;

public class Karta {

	int id;
	int numer;
	String typ;
	
	public Karta(int id, int numer, String typ) {
		this.id = id;
		this.numer = numer;
		this.typ = typ;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumer() {
		return numer;
	}

	public void setNumer(int numer) {
		this.numer = numer;
	}

	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}
	
	public void printInfo(){
		System.out.println("   ID: " + id + " Numer: " + numer + " Typ: " + typ);
	}
	
}
