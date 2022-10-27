import java.util.LinkedList;
import java.util.Collections;

public class PaquetDomino implements Paquet{

	protected LinkedList<Piece> paquet;
	protected boolean melange;

	public PaquetDomino(){
		super();
		this.paquet=new LinkedList<Piece>();
		this.melange=false;
		initialiser();
		melanger();
	}

	@Override
	public LinkedList<Piece> distribuer(){
		LinkedList<Piece> aDistribuer=new LinkedList<Piece>();
		for(int i=0;i<7;i++){
			aDistribuer.add(this.paquet.remove());
		}
		return aDistribuer;
	}

	@Override
	public boolean estVide(){
		return (paquet.size()==0);
	}

	@Override
	public void initialiser(){
		for(int i=0;i<7;i++){
			for(int j=i;j<7;j++){
				paquet.add(new Domino(i,j,"6.png"));
			}
		}
	}

	@Override
	public void melanger(){
		Collections.shuffle(paquet);
		melange=true;
	}

	@Override
	public int taille(){
		return paquet.size();
	}

	@Override
	public Piece piocher(){
		if(!estVide()){
			return this.paquet.remove();
		}
		return null;
	}

	public void affiche(){
		for(int i=0;i<taille();i++){
			if(i%7==0){
				System.out.println();
			}
			System.out.print(this.paquet.get(i)+" // ");
		}
	}


}