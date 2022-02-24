package pcomp.prolog.ast.excep;

import pcomp.prolog.ast.Equation;

// Exception levee quand on trouve une Equation candidate pour appliquer la regle remplacer
public class FoundRException extends RuntimeException {
	
	private Equation e;
	
	public FoundRException(Equation e) {
		super("Equation a injecter!");
		this.e = e;
	}
	
	public Equation getEquation() {
		return e;
	}

}
