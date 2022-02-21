package pcomp.prolog.ast;

public class Equation {
	
	private Term gauche, droite;
	
	public Equation(Term g, Term d) {
		gauche = g;
		droite = d;
	}
	
	public String toString() {
		return gauche.toString() + " = " + droite.toString();
	}

}
