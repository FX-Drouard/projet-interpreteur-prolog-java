package pcomp.prolog.ast;

import java.util.List;

public class Equation {
	
	private Term gauche, droite;
	
	// Constructeur
	///////////////////
	public Equation(Term g, Term d) {
		gauche = g;
		droite = d;
	}
	
	// Règles d'unification
	////////////////////////////
	public boolean effacer(Systeme s) {
		if (gauche.equals(droite)) {
			s.removeEquation(this);
			return true;
		}
		return false;
	}
	
	public boolean orienter(Systeme s) {
		if (droite instanceof TermVariable && gauche instanceof TermPredicate) {
			Equation nouv = new Equation(droite, gauche);
			s.addEquation(nouv);
			s.removeEquation(this);
			return true;
		}
		return false;
	}
	
	public boolean decomposer(Systeme s) {
		//comparaison des symboles si on a des predicats a gauche et a droite
		if (gauche instanceof TermPredicate && droite instanceof TermPredicate) {
			Predicate p1 = ((TermPredicate)gauche).getPredicate();
			Predicate p2 = ((TermPredicate)droite).getPredicate();
			if (p1.getSymbol().equals(p2.getSymbol())) {
				// D�composition possible!
				List<Term> args1 = p1.getArguments();
				List<Term> args2 = p2.getArguments();
				for (int i = 0;i<args1.size();i++) {
					s.addEquation(new Equation(args1.get(i),args2.get(i)));
				}
				s.removeEquation(this);
				return true;
			}
		}
		return false;
	}
	
	// Applique la regle remplacer sur l'Equation courante avec pour reference l'Equation e passee en parametre
	public boolean remplacer(Systeme s, Equation e) {
		// Hypotheses : e est de la forme TermVariable = Term
		if (e.equals(this)) {
			// on n'applique pas la regle sur soi-meme
			return false;
		}
		boolean replaced = false;
		if (droite instanceof TermVariable) {
			if (droite.equals(e.gauche)) {
				// on remplace
				replaced = true;
				droite = e.droite; // creer un setter?
			}
		} else {
			// c'est un TermPredicate
			replaced = ((TermPredicate)droite).remplacer((TermVariable)e.gauche, e.droite); // creer des getter?
		}
		if (gauche instanceof TermVariable) {
			if (gauche.equals(e.gauche)) {
				// on remplace
				replaced = true;
				gauche = e.droite; // creer un setter?
			}
		} else {
			// c'est un TermPredicate
			replaced = ((TermPredicate)gauche).remplacer((TermVariable)e.gauche, e.droite);
		}
		return replaced;
	}
	
	public boolean formatROK() {
		return gauche instanceof TermVariable;
	}
	
	@Override
	public String toString() {
		return gauche.toString() + " = " + droite.toString();
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Equation) {
			Equation other = (Equation)o;
			return (droite.equals(other.droite)) && (gauche.equals(other.gauche));
		}
		return false;
	}

}
