import java.util.*;
public abstract class Game{

	protected Joueur[] joueur;
	protected Paquet paquet;
	protected Plateau plateau;

	public Game(int n,Paquet paquet, Plateau plateau){
		joueur=new Joueur[n];
		this.paquet=paquet;
		this.plateau=plateau;
	}
	public abstract void play();
	public abstract void botAction(int i);
	public abstract void distribution();
	public abstract void ordreDeJeu();

}