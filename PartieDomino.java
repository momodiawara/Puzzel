import java.util.LinkedList;
import java.util.*;
public class PartieDomino extends Game{

	protected static int passeTour=0;
	protected static Scanner action=new Scanner(System.in);

	public PartieDomino(int n,Paquet paquet, Plateau plateau){
		super(n,paquet,plateau);
		for (int i=1;i<n;i++) {
			joueur[i]=new JoueurDomino();
		}
		System.out.println("cest quoi ton pseudo ... ?");
		String nom=action.nextLine();
		joueur[0]=new JoueurDomino(nom);
	}

	@Override
	public void distribution(){
		for(int i=0;i<joueur.length;i++){
			joueur[i].setMain(paquet.distribuer());
		}
		System.out.println("Pieces distribuees");
	}

	@Override
	public void ordreDeJeu(){
		System.out.println("le joueur qui possede le plus grand double commencera");
		for(int i=1;i<joueur.length;i++){
			if(((JoueurDomino)joueur[i]).possedePlusGrandDouble()){
				//joueur[i].main
				Joueur ancienPremier=joueur[0];
				joueur[0]=joueur[i];
				joueur[i]=ancienPremier;
			}
		}
		System.out.println("c est "+joueur[0].pseudo+" qui commence");
	}


	public LinkedList<Piece> toutesPiecesJouables(Joueur joueur){
		LinkedList<Piece> piecesAJouer=new LinkedList<Piece>();
		for(Piece x:joueur.main) if(((PlateauDomino)plateau).jouableAGauche(x) || ((PlateauDomino)plateau).jouableADroite(x) || ((PlateauDomino)plateau).jouableDeuxCotes(x)) piecesAJouer.add(x);

		return piecesAJouer;
	}

	public void afficher(LinkedList<Piece>p){
		for(Piece x:p) System.out.print( " -- "+x+" -- " );
	}
	
	public boolean noWinner(){
		for(Joueur j:joueur) if(((JoueurDomino)j).score>=500) return false;

		return true;
	}
	
	public Joueur getWinner(){
		for(Joueur j:joueur) if(((JoueurDomino)j).score>=500) return j;

		return null;
	}	

	public Joueur getLowHand(){
		Joueur low=joueur[0];
		for(Joueur x:joueur) if(((JoueurDomino)x).compterMain()<((JoueurDomino)low).compterMain()) low=x;

		return low;
	}
	
	@Override
	public void botAction(int i){
		action.nextLine();
		LinkedList<Piece> piecesJouables=new LinkedList<Piece>();
		piecesJouables=toutesPiecesJouables(joueur[i]);

		System.out.println("bot te reste "+joueur[i].tailleMain()+" pieces dans la Main :");
		
		if(piecesJouables.size()==0){
			System.out.println("LE BOT PASSE !!! Miskine");
			passeTour++;
		}else{
				/*Si la liste possede plus d'un Domino */
			if(piecesJouables.size()>1){
				/*Trier la liste du plus petit au plus grand domino (nombre de points)*/
				for(int p=0;p<piecesJouables.size()-1;p++){
					for(int j=p+1;j<piecesJouables.size();j++){ 
						if/*?*/( /*!*/(piecesJouables.get(p).getTotal()==piecesJouables.get(j).getTotal() && 
							/***/( ((Domino)piecesJouables.get(p)).isDouble() && !((Domino)piecesJouables.get(j)).isDouble() )/***/ )/**/ ||
							(piecesJouables.get(p).getTotal()>piecesJouables.get(j).getTotal())
						)/*?*/{
							int xp=piecesJouables.get(p).getG();
							int yp=piecesJouables.get(p).getD();
							int xj=piecesJouables.get(j).getG();
							int yj=piecesJouables.get(j).getD();

							piecesJouables.get(p).setG(xj);
							piecesJouables.get(p).setD(yj);

							piecesJouables.get(j).setG(xp);
							piecesJouables.get(j).setD(yp);
						}
					}
				}

			}
				/* et placer la piece la plus grande */
			if( ((PlateauDomino)plateau).jouableDeuxCotes(piecesJouables.getLast())){
				if(joueur[i].main.remove(piecesJouables.getLast())){
					((PlateauDomino)plateau).ajouterPiece(piecesJouables.removeLast(),0);
				}
			}else{
				if(joueur[i].main.remove(piecesJouables.getLast())){
					((PlateauDomino)plateau).ajouterPiece(piecesJouables.removeLast());
				}
			}
		}
	}

	@Override
	public void play(){
		System.out.println("------------------ Bienvenu dans une partie de Domino ------------");
		LinkedList<Piece> piecesJouables=new LinkedList<Piece>();
		distribution();
		ordreDeJeu();
		while(noWinner()){
			int k=0;
			for(Joueur x:joueur) System.out.println(x.pseudo+" "+((JoueurDomino)x).score);
			if(((PlateauDomino)plateau).estVide()){
				((PlateauDomino)plateau).plateau.add(((JoueurDomino)joueur[0]).jouePlusGrandDouble());
				k=1;
			}
			System.out.println(plateau);

			for(int i=k;i<4;i++){
				if(!joueur[i].humain){
					System.out.println("!---------------------------Entrer pour que "+joueur[i].pseudo+" joue---!");
					action.nextLine();
					System.out.println("!-BOT JOUUUUUUUUUUUUUUUUUUUE-----------------------------!");
					System.out.println("!---------------------------------------------------------------------------!");
					System.out.println("!#################### --PLATEAU AVANT COURP DU BOT --#####################################!");					
					System.out.println();
					System.out.println(plateau);
					System.out.println();
					System.out.println("!---------------------------------------------------------------------------!");
					botAction(i);
					System.out.println("!#################### --PLATEAU APres COURP DU BOT --#####################################!");
					System.out.println();
					System.out.println(plateau);
					System.out.println();
					System.out.println("!---------------------------------------------------------------------------!");
					System.out.println("!---------------------------Entrer pour continuer---!");
					action.nextLine();
				}else{
					System.out.println("LA PLATEAU :");
					System.out.println("!---------------------------------------------------------------------------!");
					System.out.println();
					System.out.println(plateau);
					System.out.println();
					System.out.println("!---------------------------------------------------------------------------!");
					System.out.println("!#########################################################################!");
					System.out.println(" il te reste "+joueur[i].tailleMain()+" pieces dans la Main :");
					joueur[i].voirMain();
					System.out.println("!#########################################################################!");
					piecesJouables=toutesPiecesJouables(joueur[i]);
					
					if(piecesJouables.size()==0){
						System.out.println("Aucune piece jouable, dommage "+joueur[i].pseudo+" passe son Tour : "+passeTour);
						passeTour++;
						if(passeTour>4){
							int scorePartie=0;
							for(Joueur x:joueur){
								scorePartie+=((JoueurDomino)x).compterMain();
							}

							((JoueurDomino)getLowHand()).score+=scorePartie;
							System.out.println("!---------------------------Entrer pour continuer---!");
							action.nextLine();
							passeTour=0;
							break;
						}
					}else{
						System.out.println("!-----------------------------!");
						System.out.println("les pieces jouables :");
						afficher(piecesJouables);
						int index=-1;
						while(index<0 || index>piecesJouables.size()-1){
							System.out.println("Quelle piece voulez vous jouer ?");
							index=action.nextInt();
						}
						if( ((PlateauDomino)plateau).jouableDeuxCotes(piecesJouables.get(index))){
							int cote=-1;
							while(cote<0 || cote >1){
								System.out.println("de Quel coté jouer ? : 0 pour Gauche ---- 1 pour droite");
								cote=action.nextInt();
							}
							if(cote==0){
								if(joueur[i].main.remove(piecesJouables.get(index))){
									((PlateauDomino)plateau).ajouterPiece(piecesJouables.get(index),0);
								}
								
							}
							if(cote==1){
								if(joueur[i].main.remove(piecesJouables.get(index))){
									((PlateauDomino)plateau).ajouterPiece(piecesJouables.get(index),plateau.taille()-1);
								}
							}
						}else{
							if(joueur[i].main.remove(piecesJouables.get(index))){
								((PlateauDomino)plateau).ajouterPiece(piecesJouables.get(index));
							}
						}
						System.out.println("!---------------------------------------------------------------------------!");
						System.out.println();
						System.out.println(plateau);
					}
					if(joueur[i].tailleMain()==0){
						int scorePartie=0;
						for(Joueur x:joueur) scorePartie+=((JoueurDomino)x).compterMain();
						((JoueurDomino)joueur[i]).score+=scorePartie;

						break;
					}
				}if(i==3 && noWinner()){
					k=0;
					i=-1;
					System.out.println(" JE REEEEEEEEEEEEEEEEEEEEEEEEEEEEEMER i a 000000000000000000000000000000 i: "+i+" k: "+k);
				}

			}//Fin de Boucle (i)
			paquet=new PaquetDomino();
			plateau=new PlateauDomino();
			distribution();
			ordreDeJeu();
		}//Fin de while
		System.out.println("nous avons un Winner ! "+getWinner().pseudo+" a gagne, Bravo ");
		for(Joueur x:joueur) System.out.println(x.pseudo+" "+((JoueurDomino)x).score);
		paquet=new PaquetDomino();
		plateau=new PlateauDomino();
		passeTour=0;
	}//fin play()

}