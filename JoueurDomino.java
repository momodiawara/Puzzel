import java.util.*;
public class JoueurDomino extends Joueur{
	protected int score;

	public JoueurDomino(String nom){
		super(nom);
		score=0;
	}
	public JoueurDomino(){
		super();
		score=0;
	}

	public Piece jouer(Piece p){
		Piece aJouer=p;
		if(main.remove(p)){
			return aJouer;
		}
		return null;
	}

	public boolean possedePlusGrandDouble(){
		for(int i=0;i<tailleMain();i++){
			if(((Domino)main.get(i)).isDouble() && main.get(i).getG()==6){
				return true;
			}
		}
		return false;
	}

	public Piece jouePlusGrandDouble(){
		for(int i=0;i<tailleMain();i++){
			if(((Domino)main.get(i)).isDouble() && main.get(i).getG()==6){
				main.remove(i);
			}
		}
		Piece doubleSix=new Domino(6,6,"6.png");
		return doubleSix;

	} 

	public void setMain(LinkedList<Piece> main){
		this.main=main;
	}

	public int tailleMain(){
		return main.size();
	}

	public void voirMain(){
		System.out.println("la main de: "+this.pseudo);
		for(int i=0;i<tailleMain();i++){
			System.out.print(main.get(i)+" - ");
		}
	}

	public int compterMain(){
		int scoreReponse=0;
		for(Piece x:main) scoreReponse+=x.getG()+x.getD();

		return scoreReponse;
	}
}