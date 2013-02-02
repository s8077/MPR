package pl.mpr.uprawnienia.main;

import java.util.Scanner;

import pl.mpr.uprawnienia.baza.UprawnieniaManager;

public class Start {

	public static void main(String[] args) {
		
		System.out.println("Witaj w programie do zarzadzania kartami pracownikow");
		
		UprawnieniaManager um = new UprawnieniaManager();
		Scanner in = new Scanner(System.in); 
		
		while (true) { 
			
			System.out.println("Wybierz numer:\n 0. Koniec\n 1.Dodaj pracownika\n 2.Dodaj karte\n 3.Wyswietl wszystkich\n 4. Usun pracownika\n 5. Usun karte\n 6. Wyczysc cala baze");
			
			switch (in.nextInt()) { 
			case 0:System.out.println("Koniec");return; 
			case 1:um.dodajPracownika();break;
			case 2:um.dodajKarte();break;
			case 3:um.wyswietl();break;
			case 4:um.usunPracownika();break;
			case 5:um.usunKarte();break;
			case 6:um.czyscBaze();break;
			default:continue; 
			}
			
		}

	}

}
