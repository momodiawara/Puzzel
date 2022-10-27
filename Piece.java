public abstract class Piece{

	protected int coteGauche;
	protected int coteDroite;

	public Piece(int coteG, int coteD){
		this.coteGauche=coteG;
		this.coteDroite=coteD;
	}

	public int getG(){
		return this.coteGauche;
	}

	public int getD(){
		return this.coteDroite;
	}
	public void setG(int g){
		coteGauche=g;
	}
	public void setD(int d){
		coteDroite=d;
	}
	public int getTotal(){
		return getG()+getD();
 	}	
	public abstract String toString();
}