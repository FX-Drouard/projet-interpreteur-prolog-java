/*
 * PCOMP (LU3IN032), Licence 3, Sorbonne Université
 * année 2021-2022
 *
 * Projet Prolog
 */

package pcomp.prolog.ast;

/*
 * AST : classe des termes qui sont des prédicats.
 *
 */
public class TermPredicate implements Term {

	// Attributs
	////////////////

	private final Position pos;
	private final Predicate pred;


	// Constructeur
	/////////////////////////

	public TermPredicate(Predicate pred, Position pos) {
		this.pred = pred;
		this.pos = pos;
	}


	// Getters
	///////////////

	public Predicate getPredicate() {
		return pred;
	}

	public Position getPosition() {
		return pos;
	}

	
	// Égalité structurelle (récursive)
	///////////////////////////////////////////////////

	@Override public boolean equals(Object o) {
		// la position n'est pas prise en compte !
		if (this == o) return true;
		if (!(o instanceof TermPredicate)) return false;
		TermPredicate p = (TermPredicate)o;
		return p.pred.equals(pred);
	}

	@Override public int hashCode() {
		// la position n'est pas prise en compte !
		return pred.hashCode();
	}

	// Conversion en chaîne
	/////////////////////////////////////

	@Override public String toString() {
		return pred.toString();
	}


	// Visiteur
	///////////////

	@Override public <T> T accept(TermVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
