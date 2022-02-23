package pcomp.prolog.ast.excep;

import pcomp.prolog.ast.Equation;

public class FoundRException extends RuntimeException {
	
	private Equation e;
	
	public FoundRException(Equation e) {
		super("Equation � injecter!");
		this.e = e;
	}
	
	public Equation getEquation() {
		return e;
	}

}
