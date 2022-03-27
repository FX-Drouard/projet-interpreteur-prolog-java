package pcomp.prolog.ast.excep;

import pcomp.prolog.ast.Environnement;

public class SolutionFound extends RuntimeException {
	private Environnement env;
	
	public SolutionFound(Environnement env) {
		super("1 solution trouvée !");
		this.env = env;
	}
	
	public Environnement getEnv() {
		return env;
	}

}
