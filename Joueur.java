import java.util.LinkedList;
import java.util.*;
public abstract class Joueur{

	protected static int botNum;
	protected boolean humain;
	protected String pseudo;
	protected LinkedList<Piece> main;

	public Joueur(){
		botNum++;
		pseudo="bot "+String.valueOf(botNum);
		humain=false;
		main=new LinkedList<Piece>();
	}
	public Joueur(String nom){
		pseudo=nom;
		humain=true;
		main=new LinkedList<Piece>();
	}
	public void setPseudo(String nPseudo){
		pseudo=nPseudo;
	}

	public abstract Piece jouer(Piece p);
	public abstract void setMain(LinkedList<Piece> main);
	public abstract int tailleMain();
	public abstract void voirMain();





}