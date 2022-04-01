package pcomp.Gui;
import pcomp.*;

public class Lanceur {
	private final String textoriginel;
	
	public Lanceur(String text) {
		this.textoriginel=text;
	}
	
	public String run() {
		try{
			return "TODO";
		}catch (Exception e) {
			Question.warn("Une erreur à été detecté!");
			return "Erreur Critique! "+e.getMessage();
		}
	}
}
