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
			Question.warn("Une erreur � �t� detect�!");
			return "Erreur Critique! "+e.getMessage();
		}
	}
}
