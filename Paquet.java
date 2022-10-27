import java.util.LinkedList;
public interface Paquet{

	public abstract LinkedList<Piece> distribuer();
	public abstract boolean estVide();
	public abstract void initialiser();
	public abstract void melanger();
	public abstract Piece piocher();
	public abstract int taille();
}