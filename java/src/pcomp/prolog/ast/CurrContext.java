package pcomp.prolog.ast;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class CurrContext {
	// r�gle choisie
	private DeclAssertion choice;
	// liste des buts � r�soudre
	private List<Predicate> goals;
	// liste des r�gles � explorer
	private List<DeclAssertion> toExplore;
	// environnement
	private Environnement env;
	// liste des choix suivants
	private List<DeclAssertion> nextChoices;
	
	public CurrContext(DeclAssertion choice, List<Predicate> goals, List<DeclAssertion> rules, Environnement env) {
		this.choice = choice;
		//pas besoin de copier parce que par construction, le goals pass� en param�tre est une nouvelle liste (dans solve du Jalon4)
		this.goals = goals;
		toExplore = rules;
		this.env = env.copy();
		nextChoices = new ArrayList<>();
	}
	
	// Getters
	
	public DeclAssertion getChoice() {
		return choice;
	}
	
	public List<Predicate> getGoals() {
		return goals;
	}
	
	public List<DeclAssertion> getRules() {
		return toExplore;
	}
	
	public Environnement getEnv() {
		return env;
	}
	
	public List<DeclAssertion> getNextChoices() {
		return nextChoices;
	}
	
	public void addNextChoice(DeclAssertion choice) {
		nextChoices.add(choice);
	}
	
	// Fonctions d'affichages
	
	@Override
	public String toString() {
		StringJoiner sj = new StringJoiner("\n");
		sj.add("Choix : "+choice.toString());
		sj.add("Buts � r�soudre :");
		for (Predicate goal : goals) {
			sj.add(goal.toString());
		}
		//sj.add("Environnement :"+env.toString());
		return sj.toString();
	}
	
	public void afficheChoice() {
		System.out.println(toString());
	}
	
	public static void afficheListChoices(List<CurrContext> choices) {
		int i = 1;
		for (CurrContext c : choices) {
			System.out.println("Choix n�"+i);
			c.afficheChoice();
			i++;
		}
	}
}
