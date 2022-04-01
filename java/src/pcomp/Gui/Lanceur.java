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
		try{
			//test
			Program prog = PrologParser.parseString(textoriginel);
			try {
				Environnement env = Interprete.interprete4(prog);
				Tools.addText(env.toString());
			} catch (NoSolutionException excep) {
				Tools.addText("Jalon 5 "+excep.getMessage());
			}
			return Tools.getText(true);
		}catch (Exception e) {
			Question.warn("Une erreur à été detecté!");
			return "Erreur Critique! "+e.getMessage();
		}
	}
}
