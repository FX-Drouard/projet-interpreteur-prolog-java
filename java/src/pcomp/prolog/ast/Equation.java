package pcomp.prolog.ast;

import java.util.List;

public class Equation {
	
	private Term gauche, droite;
	
	public Equation(Term g, Term d) {
		gauche = g;
		droite = d;
	}
	
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
		//comparaison des symboles si on a des prédicats à gauche et à droite
		if (gauche instanceof TermPredicate && droite instanceof TermPredicate) {
			Predicate p1 = ((TermPredicate)gauche).getPredicate();
			Predicate p2 = ((TermPredicate)droite).getPredicate();
			if (p1.getSymbol().equals(p2.getSymbol())) {
				// Décomposition possible!
				List<Term> args1 = p1.getArguments();
				List<Term> args2 = p2.getArguments();
				for (int i = 0;i<args1.size();i++) {
					s.addEquation(new Equation(args1.get(i),args2.get(i)));
				}
				s.removeEquation(this);
				return true;
			}
			System.out.println("Cas où les symboles des prédicats non-compatibles : on arrête tout?");
		}
		return false;
	}
	
	public boolean remplacer(Systeme s, Equation e) {
		// Hypothèses : e est de la forme TermVariable = Term
		boolean replaced = false;
		if (droite instanceof TermVariable) {
			if (droite.equals(e.gauche)) {
				// on remplace
				replaced = true;
				droite = e.droite;
			}
		} else {
			// c'est un TermPredicate
			replaced = ((TermPredicate)droite).remplacer((TermVariable)e.gauche, e.droite);
		}
		if (gauche instanceof TermVariable) {
			if (gauche.equals(e.gauche)) {
				// on remplace
				replaced = true;
				gauche = e.droite;
			}
		} else {
			// c'est un TermPredicate
			replaced = ((TermPredicate)gauche).remplacer((TermVariable)e.gauche, e.droite);
		}
		return false;
	}
	
	public boolean formatROK() {
		return gauche instanceof TermVariable;
	}
	
	public String toString() {
		return gauche.toString() + " = " + droite.toString();
	}

}
