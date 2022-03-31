package pcomp.prolog.ast.excep;

import pcomp.prolog.ast.CurrContext;

public class NoSolutionException extends RuntimeException {
	
	public NoSolutionException(String mess) {
		super(mess);
	}
	
	public NoSolutionException(String mess, CurrContext c) {
		super(mess+" "+c.toString());
	}

}
