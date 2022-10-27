public class Domino extends Piece{

	protected boolean estDouble;
	protected String source;
	
	public Domino(int coteG, int coteD, String src){
		super(coteG,coteD);
		this.estDouble=(coteG==coteD);
		source=src;
	}

	public boolean isDouble(){
		return this.estDouble;
	}
		
	public void inverser(){
		if(!isDouble()){
			int temp=coteGauche;
			coteGauche=coteDroite;
			coteDroite=temp;
		}
	}

	@Override
	public String toString(){
	
		return "[ "+this.coteGauche+" | "+this.coteDroite+" ]"; 
	}
}