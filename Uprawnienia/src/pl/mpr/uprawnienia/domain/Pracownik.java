package pl.mpr.uprawnienia.domain;

import java.util.ArrayList;

public class Pracownik {

	//pola przechowujące poszczególne dane danego rekordu
	int id;
	String nazwisko;
	String imie;
	String stanowisko;
	ArrayList<Karta> karty_dostepu;
	
	//tworząc obiekt podajemy dane przez konstruktor
	public Pracownik(int id, String nazwisko, String imie, String stanowisko) {
		this.id = id;
		this.nazwisko = nazwisko;
		this.imie = imie;
		this.stanowisko = stanowisko;
		karty_dostepu = new ArrayList<Karta>();
	}

	//gettery i settery do póxniejszego odczytu i edycji pól - stworzone przez PPM -> Source -> Generate Getters and Setters...
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getStanowisko() {
		return stanowisko;
	}

	public void setStanowisko(String stanowisko) {
		this.stanowisko = stanowisko;
	}

	public ArrayList<Karta> getKarty_dostepu() {
		return karty_dostepu;
	}

	public void setKarty_dostepu(ArrayList<Karta> karty_dostepu) {
		this.karty_dostepu = karty_dostepu;
	}
	
	//wyswietlenie danych
	public void printInfo(){
		System.out.println("ID: " + id + " Imie: " + imie + " Nazwisko: " + nazwisko + " Stanowisko: " + stanowisko);
	}
	
}
