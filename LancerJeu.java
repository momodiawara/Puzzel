import java.util.*;
public class LancerJeu{

	protected static Game game;

	public static void main(String []args){
		Scanner quelJeu=new Scanner(System.in);
		int jeu=0;
		while(jeu<1){
			System.out.println(" BONJOUR !");
			System.out.println("Quel jeu jouer ??");
			System.out.println(" 1- Domino :");
			System.out.println(" 2- Puzzle");
			System.out.println(" 3- Gommette");
			System.out.println(" 4- Saboteur");
			jeu=quelJeu.nextInt();
		}
		if(jeu==1){
			game=new PartieDomino(4,new PaquetDomino(),new PlateauDomino());
		}
		if(jeu==2){

		}
		boolean jouer=true;
		while(jouer){
			game.play();
			System.out.println("rejouer ?");
			jouer=false;
		}
	}










}