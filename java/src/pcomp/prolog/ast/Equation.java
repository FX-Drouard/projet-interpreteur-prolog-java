package pcomp.prolog.ast;

import java.util.List;

import pcomp.prolog.ast.excep.NoSolutionException;

public class Equation {
	
	private Term gauche, droite;
	
	// Constructeur
	///////////////////
	public Equation(Term g, Term d) {
		gauche = g;
		droite = d;
	}
	
	// Regles d'unification
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
				// Décomposition possible!
				List<Term> args1 = p1.getArguments();
				List<Term> args2 = p2.getArguments();
				for (int i = 0;i<args1.size();i++) {
					s.addEquation(new Equation(args1.get(i),args2.get(i)));
				}
				s.removeEquation(this);
				return true;
			} else {
				throw new NoSolutionException("Décomposition impossible sur l'Equation "+toString());
			}
		}
		return false;
	}
	
	// Applique la regle remplacer sur l'Equation courante avec pour reference l'Equation e passee en parametre
	public boolean subst(Systeme s, TermVariable x, Term nouv) {
		// vérification occur_check
		Equation e = new Equation(x,nouv);
		e.occur_check();
		boolean replaced = false;
		if (droite instanceof TermVariable) {
			if (droite.equals(x)) {
				// on remplace
				replaced = true;
				droite = nouv; // creer un setter?
			}
		} else {
			// c'est un TermPredicate
			replaced = ((TermPredicate)droite).subst((TermVariable)x, nouv); // creer des getter?
		}
		if (gauche instanceof TermVariable) {
			if (gauche.equals(x)) {
				// on remplace
				replaced = true;
				gauche = nouv; // creer un setter?
			}
		} else {
			// c'est un TermPredicate
			replaced = ((TermPredicate)gauche).subst((TermVariable)x, nouv);
		}
		return replaced;
	}
	
	private boolean occur_check() throws NoSolutionException {
		// équation est de forme TermVariable = Term
		VisitorVar v = new VisitorVar();
		List<TermVariable> vars = droite.accept(v);
		if (vars.contains(gauche)) {
			throw new NoSolutionException("OccurCheck true : "+this.toString());
		}
		return false;
	}
	
	// vérifie que les termes d'une Equation peut être mise dans un Environnement
	public boolean formatROK() throws NoSolutionException {
		return gauche instanceof TermVariable && !occur_check();
	}
	
	//Getters
	
	public Term getDroite() {
		return droite;
	}
	
	public Term getGauche() {
		return gauche;
	}
	

	// Methodes usuelles
	
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
	
	public Equation copy() {
		return new Equation(this.gauche.copy(),this.droite.copy());
	}

}
