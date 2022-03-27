package pcomp.prolog.ast;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class CurrContext {
	// règle choisie
	private DeclAssertion choice;
	// liste des buts à résoudre
	private List<Predicate> goals;
	// liste des règles à explorer
	private List<DeclAssertion> toExplore;
	// environnement
	private Environnement env;
	
	public CurrContext(DeclAssertion choice, List<Predicate> goals, List<DeclAssertion> rules, Environnement env) {
		this.choice = choice;
		// on enregistre que des copies des listes
		this.goals = goals;
		//pas besoin de copier parce que par construction, le goals passé en paramètre est une nouvelle liste (dans solve du Jalon4)
		List<DeclAssertion> r = new ArrayList<>();
		for (DeclAssertion elem : rules) {
			r.add(elem);
		}
		toExplore = r;
		this.env = env.copy();
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
	
	// Fonctions d'affichages
	
	@Override
	public String toString() {
		StringJoiner sj = new StringJoiner("\n");
		sj.add("Choix : "+choice.toString());
		sj.add("Buts à résoudre :");
		for (Predicate goal : goals) {
			sj.add(goal.toString());
		}
		sj.add("Environnement :"+env.toString());
		return sj.toString();
	}
	
	public void afficheChoice() {
		System.out.println(toString());
	}
	
	public static void afficheListChoices(List<CurrContext> choices) {
		int i = 1;
		for (CurrContext c : choices) {
			System.out.println("Choix n°"+i);
			c.afficheChoice();
			i++;
		}
	}
}
