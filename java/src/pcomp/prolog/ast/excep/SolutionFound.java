package pcomp.prolog.ast.excep;

import pcomp.prolog.ast.CurrContext;

public class SolutionFound extends RuntimeException {
	private CurrContext finalChoice;
	
	public SolutionFound(CurrContext ch) {
		super("1 solution trouvée !");
		finalChoice = ch;
	}
	
	public CurrContext getFinalChoice() {
		return finalChoice;
	}

}
