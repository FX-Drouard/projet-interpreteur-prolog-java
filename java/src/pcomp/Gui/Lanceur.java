package pcomp.Gui;
import pcomp.*;
import pcomp.prolog.ast.CurrContext;
import pcomp.prolog.ast.Decl;
import pcomp.prolog.ast.Environnement;
import pcomp.prolog.ast.Interprete;
import pcomp.prolog.ast.Program;
import pcomp.prolog.ast.VisitorDecl;
import pcomp.prolog.ast.excep.NoSolutionException;
import pcomp.prolog.parser.PrologParser;

public class Lanceur {
	private final String textoriginel;
	
	public Lanceur(String text) {
		this.textoriginel=text;
	}
	
	public String run() {
		StringBuilder res= new StringBuilder();
		try{
			//test
			Program prog = PrologParser.parseString(textoriginel);
			res.append(prog);
			res.append("\nSolution: ");
			try {
				Environnement env = Interprete.interprete4(prog);
				res.append(env);
			} catch (NoSolutionException excep) {
				res.append("Jalon 5 "+excep.getMessage());
			}
			return res.toString();
		}catch (Exception e) {
			Question.warn("Une erreur à été detecté!");
			return "Erreur Critique! "+e.getMessage();
		}
	}
}
