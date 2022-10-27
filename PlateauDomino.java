import java.util.LinkedList;
import java.util.*;
public class PlateauDomino implements Plateau{

	protected LinkedList<Piece> plateau;
	protected int jouableAGauche;
	protected int jouableADroite;

	public PlateauDomino(){
		this.plateau=new LinkedList<Piece>();
		jouableADroite=6;
		jouableAGauche=6;
	}

	@Override
	public void ajouterPiece(Piece piece, int index){
		this.plateau.add(index,piece);
		jouableADroite=plateau.getLast().getD();
		jouableAGauche=plateau.getFirst().getG();
	}
	public boolean ajouterPiece(Piece piece){
		if(jouableAGauche(piece)){
			ajouterPiece(piece,0);
			return true;
		}
		if(jouableADroite(piece)){
			ajouterPiece(piece,plateau.size());
			return true;	
		}
		return false;
	}
	public boolean jouableAGauche(Piece piece){
		if(jouableAGauche==piece.getD()){
			return true;
		}
		((Domino)piece).inverser();
		return (jouableAGauche==piece.getD());
	}

	public boolean jouableADroite(Piece piece){
		if(jouableADroite==piece.getG()){
			return true;
		}
		((Domino)piece).inverser();
		return (jouableADroite==piece.getG());
	}

	public boolean jouableDeuxCotes(Piece piece){
		return (jouableADroite(piece) && jouableAGauche(piece));
	}


	@Override
	public boolean estVide(){
		return this.plateau.isEmpty();
	}

	@Override
	public int taille(){
		return this.plateau.size();
	}

	@Override
	public String toString(){
		String reponse="";
		for(int i=0;i<this.plateau.size();i++){
			reponse+=this.plateau.get(i).toString()+" ";
		}
		return reponse;
	}
}