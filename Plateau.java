import java.util.*;
public interface Plateau{

	public void ajouterPiece(Piece piece, int index);
	public boolean estVide();
	public int taille();
	public String toString();
}