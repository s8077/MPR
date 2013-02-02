package pl.mpr.uprawnienia.baza;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import pl.mpr.uprawnienia.domain.Karta;
import pl.mpr.uprawnienia.domain.Pracownik;


public class DBManagerTest {

	private static String IMIE = "Jan";
	private static String NAZWISKO = "Kowalski";
	private static String STANOWISKO = "Lopatolog";
	
	private static int NUMER = 23;
	private static String TYP = "xxx";
	
	private static DBManager dbm;
	
	@BeforeClass
	public static void connectionTest(){
		
		try {
			
			dbm = new DBManager("uprawnienia");
			
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Moze zapomnialas uruchomic serwer.");
		}
	}
	
	@Test
	public void usunWszytskoTest() {
		dbm.czyscBaze();
		ArrayList<Pracownik> pracownicy = dbm.wczytaj();
		assertEquals(0, pracownicy.size()); 
	}
	
	@Test
	public void dodajPracownikaTest() {
		
		dbm.czyscBaze();
		Pracownik pracownik = new Pracownik(0, NAZWISKO, IMIE, STANOWISKO); 
		dbm.zapiszPracownika(pracownik); 
		
		ArrayList<Pracownik> pracownicy = dbm.wczytaj();
		assertEquals(1, pracownicy.size()); 
	}
	
	@Test
	public void usunPracownikaTest() {
		
		dbm.czyscBaze();
		Pracownik pracownik = new Pracownik(0, NAZWISKO, IMIE, STANOWISKO);
		dbm.zapiszPracownika(pracownik);
		dbm.usunPracownika(pracownik); 
		
		ArrayList<Pracownik> pracownicy = dbm.wczytaj();
		assertEquals(0, pracownicy.size()); 
	}
	
	@Test
	public void dodajKarteTest() {
		
		Pracownik pracownik = new Pracownik(0, NAZWISKO, IMIE, STANOWISKO);
		dbm.zapiszPracownika(pracownik);
		
		Karta karta = new Karta(0, NUMER, TYP); 
		dbm.zapiszKarte(karta, pracownik);
		
		ArrayList<Pracownik> pracownicy = dbm.wczytaj();
		ArrayList<Karta> karty = pracownicy.get(0).getKarty_dostepu(); 
		assertEquals(1, karty.size());
	}
	
	@Test
	public void usunKarteTest() {
		
		dbm.czyscBaze();
		Pracownik pracownik = new Pracownik(0, NAZWISKO, IMIE, STANOWISKO);
		dbm.zapiszPracownika(pracownik);
		
		Karta karta = new Karta(0, NUMER, TYP);
		dbm.zapiszKarte(karta, pracownik);
		dbm.usunKarte(karta); 
		
		ArrayList<Pracownik> pracownicy = dbm.wczytaj();
		ArrayList<Karta> karty = pracownicy.get(0).getKarty_dostepu();
		assertEquals(0, karty.size());
		
	}
	
	@Test
	public void poprawnoscDanychTest() {
		dbm.czyscBaze();
		Pracownik pracownik = new Pracownik(0, NAZWISKO, IMIE, STANOWISKO);
		dbm.zapiszPracownika(pracownik);
		
		Karta karta = new Karta(0, NUMER, TYP);
		dbm.zapiszKarte(karta, pracownik);
		
		ArrayList<Pracownik> pracownicy = dbm.wczytaj();
		assertEquals(IMIE, pracownicy.get(0).getImie()); 
		assertEquals(NAZWISKO, pracownicy.get(0).getNazwisko());
		assertEquals(STANOWISKO, pracownicy.get(0).getStanowisko());
		
		ArrayList<Karta> karty = pracownicy.get(0).getKarty_dostepu();
		assertEquals(NUMER, karty.get(0).getNumer());
		assertEquals(TYP, karty.get(0).getTyp());
	}
	
}
