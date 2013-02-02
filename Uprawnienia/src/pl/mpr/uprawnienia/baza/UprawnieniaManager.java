package pl.mpr.uprawnienia.baza;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;


import pl.mpr.uprawnienia.domain.Karta;
import pl.mpr.uprawnienia.domain.Pracownik;

public class UprawnieniaManager {

	private Scanner in = null;
	private DBManager dbm = null;
	private ArrayList<Pracownik> pracownikList = null;
	private int lastPracownikID = 0;
	private int lastKartaID = 0;
	
	public UprawnieniaManager() {
		
		in = new Scanner(System.in); 
		
		try {
			
			dbm = new DBManager("uprawnienia"); 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		pracownikList = dbm.wczytaj(); 
		
		for (Pracownik pracownik: pracownikList) {
			
			if (pracownik.getId() > lastPracownikID) {
				lastPracownikID = pracownik.getId();
			}
			ArrayList<Karta> kartaList = pracownik.getKarty_dostepu();
			
			for (Karta karta: kartaList) {
				if (karta.getId() > lastKartaID) {
					lastKartaID = karta.getId();
				}
			}
		}
		
	}
	
	public void wyswietl() {
		
		for (Pracownik pracownik: pracownikList) {
			pracownik.printInfo();
			ArrayList<Karta> kartaList = pracownik.getKarty_dostepu();
			
			for (Karta karta: kartaList) {
				karta.printInfo();
			}
		}
		
	}
	
	public void czyscBaze() {
		
		dbm.czyscBaze(); 
		pracownikList.clear(); 
		
	}
	
	public void dodajPracownika() {
		
		System.out.println("Podaj imie: ");
		String imie = in.next();
		System.out.println("Podaj nazwisko: ");
		String nazwisko = in.next();
		System.out.println("Podaj stanowisko: ");
		String stanowisko = in.next();
		
		lastPracownikID++; 
		Pracownik pracownik = new Pracownik(lastPracownikID, nazwisko, imie, stanowisko);
		
		pracownikList.add(pracownik); 
		dbm.zapiszPracownika(pracownik); 
		
	}
	
	public void usunPracownika() {
		
		for (Pracownik pracownik: pracownikList) {
			pracownik.printInfo();
		}
		
		System.out.println("Podaj ID pracownika do usuniecia: ");
		int id = in.nextInt();
		
		for (Pracownik pracownik: pracownikList) {
			if (id == pracownik.getId()) {
				dbm.usunPracownika(pracownik); 
				pracownikList.remove(pracownik); 
				break; 
			}
		}
		
	}
	
	public void dodajKarte() {
		
		for (Pracownik pracownik: pracownikList) {
			pracownik.printInfo();
		}
		
		System.out.println("Podaj ID pracownika dla którego przypisać nową kartę: ");
		int id = in.nextInt();
		
		for (Pracownik pracownik: pracownikList) {
			if (id == pracownik.getId()) {
				System.out.println("Podaj numer dla nowej karty: ");
				int numer = in.nextInt();
				System.out.println("Podaj typ karty: ");
				String typ = in.next();
				
				lastKartaID++; 
				Karta karta = new Karta(lastKartaID, numer, typ);
				
				dbm.zapiszKarte(karta, pracownik); 
				ArrayList<Karta> tmp = pracownik.getKarty_dostepu();
				tmp.add(karta); 
				
				break;
			}
		}
		
	}
	
	public void usunKarte() {
		
		for (Pracownik pracownik: pracownikList) {
			ArrayList<Karta> kartaList = pracownik.getKarty_dostepu();
			
			for (Karta karta: kartaList) {
				karta.printInfo();
			}
		}
		
		System.out.println("Podaj ID karty do usuniecia: ");
		int id = in.nextInt();
		
		for (Pracownik pracownik: pracownikList) {
			ArrayList<Karta> kartaList = pracownik.getKarty_dostepu();
			
			for (Karta karta: kartaList) {
				if (id == karta.getId()) {
					dbm.usunKarte(karta);
					ArrayList<Karta> tmp = pracownik.getKarty_dostepu();
					tmp.remove(karta); 
					
					break;
				}
				
			}
			
		}
		
	}
	
}
