package org.generation.italy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int sommaTotale=100, puntata, carta, i;
		float punteggio, punteggioCpu;
		ArrayList<Integer> mazzo=new ArrayList<Integer>();
		String seme=new String();
		String figura=new String();
		String risposta=new String();
		
		Scanner sc=new Scanner(System.in);
		Random r=new Random();
		System.out.println("Benvenuto nel gioco di 7 e mezzo!\n");
		System.out.println("Il denaro a disposizione è: "+sommaTotale+" euro.\n");
		do {	//ciclo per ogni puntata
			mazzo.clear();
			System.out.println("Generazione nuovo mazzo...\n\n");
			
			punteggio=0;
			punteggioCpu=0;
			/*genero le carte nel mazzo:
			 *  - da 1 a 10 sono bastoni;
			 *  - da 11 a 20 sono denari;
			 *  - da 21 a 30 sono spade;
			 *  - da 31 a 40 sono coppe.
			 */
			for (i=0; i<40; i++) {
				do {
					carta= r.nextInt(40)+1;
				} while(mazzo.contains(carta));
				mazzo.add(carta);
			}
			System.out.println("Nuovo mazzo generato!\n");
			//chiedo al giocatore quanto vuole puntare
			System.out.println("Quanto vuoi puntare?");
			puntata=sc.nextInt();
			sc.nextLine();
			while (puntata>sommaTotale) {
				System.out.println("Mi dispiace, non hai abbastanza soldi per puntare così tanto. Inserisci un nuovo valore: ");
				puntata=sc.nextInt();
				sc.nextLine();
			}
			while (puntata<=0) {
				System.out.println("Inserimento non valido! Inserire nuovo valore: ");
				puntata=sc.nextInt();
				sc.nextLine();
			}
			
			i=0;
			
			do { //ciclo estrazioni carte dell'utente
				System.out.print("Estrazione carta n° "+(i+1)+": ");
				//controllo la carta estratta
				carta=mazzo.get(i);
				if (carta<=10) {
					seme="bastoni";
				}else if (carta<=20) {
					seme="denari";
					carta=carta-10;
				}else if (carta<=30) {
					seme="spade";
					carta=carta-20;
				}else {
					seme="coppe";
					carta=carta-30;
				}
				if (carta==1) {
					figura="asso";
				}else if (carta==8) {
					figura="donna";
				}else if (carta==9) {
					figura="cavallo";
				}else if (carta==10) {
					figura="re";
				}
				i++;
				//mostro la carta estratta all'utente e il punteggio
				if (carta==1||carta>=8) {
					System.out.println("La carta estratta è "+figura+" di "+seme+".");
					punteggio=punteggio+1f;
					if (carta!=1)
						punteggio=punteggio-0.5f;
				}else {
					System.out.println("La carta estratta è "+carta+" di "+seme+".");
					punteggio=punteggio+carta;
				}
				System.out.println("Il punteggio attuale è: "+punteggio);
				if (punteggio>7.5f) 
					//se il punteggio supera il 7.5 esco direttamente dal ciclo di estrazione carte
					break;
				
				System.out.println("Estrarre una nuova carta?");
				risposta=sc.nextLine().toLowerCase();
				while (!(risposta.equals("si"))&&!(risposta.equals("sì"))&&!(risposta.equals("no"))) {
					System.out.println("Scusa, non ho capito. Puoi ripetere?");
					risposta=sc.nextLine().toLowerCase();
				}
				
			} while (risposta.substring(0, 1).equals("s"));
			System.out.println("Il punteggio totalizzato è: "+punteggio);
			
			if (punteggio<=7.5) {
				//adesso estraggo le carte del computer cercando di superare quelle del giocatore
				System.out.print("Estrazione carte del banco: ");
				do { //ciclo estrazioni carte del banco
					//controllo la carta estratta
					carta=mazzo.get(i);
					if (carta<=10) {
						seme="bastoni";
					}else if (carta<=20) {
						seme="denari";
						carta=carta-10;
					}else if (carta<=30) {
						seme="spade";
						carta=carta-20;
					}else {
						seme="coppe";
						carta=carta-30;
					}
					if (carta==1) {
						figura="asso";
					}else if (carta==8) {
						figura="donna";
					}else if (carta==9) {
						figura="cavallo";
					}else if (carta==10) {
						figura="re";
					}
					i++;
					//mostro la carta estratta all'utente e il punteggio
					if (carta==1||carta>=8) {
						System.out.println("La carta estratta dal banco è "+figura+" di "+seme+".");
						punteggioCpu=punteggioCpu+1f;
						if (carta!=1)
							punteggioCpu=punteggioCpu-0.5f;
					}else {
						System.out.println("La carta estratta è "+carta+" di "+seme+".");
						punteggioCpu=punteggioCpu+carta;
					}
					System.out.println("Il punteggio attuale è: "+punteggioCpu);
					if (punteggioCpu>7.5f) 
						//se il punteggio supera il 7.5 esco direttamente dal ciclo di estrazione carte
						break;
					System.out.println("Continuare?");
					sc.nextLine();
				}while(punteggioCpu<punteggio);
				if (punteggioCpu<=7.5f) {
					//condizione per cui il computer non ha superato il 7 e mezzo
					if (punteggioCpu>=punteggio) {
						//condizione di vittoria del computer
						System.out.println("Ha vinto il banco!");
						sommaTotale=sommaTotale-puntata;
					}else {
						//condizione di vittoria del giocatore
						System.out.println("Complimenti! Hai vinto!!");
						sommaTotale=sommaTotale+puntata;
					}
				}else {
					//il computer ha superato il 7 e mezzo, quindi ha perso
					System.out.println("Complimenti! Hai vinto!!");
					sommaTotale=sommaTotale+puntata;
				}
			}else {
				//condizione per cui il giocatore ha superato il 7 e mezzo
				//ha perso ha prescindere senza estrazione del banco
				System.out.println("Ha vinto il banco!");
				sommaTotale=sommaTotale-puntata;
			}
			
			if (sommaTotale<0) 
				break;
			
			System.out.println("Ti sono rimasti in totale "+sommaTotale+" euro.");
			
			if (sommaTotale>0) {
				System.out.println("Vuoi continuare a giocare?");
				risposta=sc.nextLine().toLowerCase();
				while (!(risposta.equals("si"))&&!(risposta.equals("sì"))&&!(risposta.equals("no"))) {
					System.out.println("Scusa, non ho capito. Puoi ripetere?");
					risposta=sc.nextLine().toLowerCase();
				}
			}else {
				risposta="no";
			}
			
		} while (risposta.substring(0, 1).equals("s"));
		
		if(sommaTotale==0) {
			System.out.println("Peccato, hai perso tutto...");
		} else {
			if (sommaTotale>100) {
				System.out.println("Complimenti! Hai guadagnato "+(sommaTotale-100)+" euro!");
			}else if (sommaTotale<100&&sommaTotale>0) {
				System.out.println("In totale hai perso "+(100-sommaTotale)+" euro...");
			}else if (sommaTotale==100) {
				System.out.println("Non vinto né perso nulla.");
			}else {
				System.out.println("Bene, ora sei in debito di "+(0-sommaTotale)+" euro...");
			}
		}
		
		sc.close();
	}

}
