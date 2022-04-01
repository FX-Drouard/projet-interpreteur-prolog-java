package pcomp.prolog.ast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Environnement {
	
	private Map<TermVariable,Term> env = new HashMap<>();
	
	// Methodes pour gerer l'environnement
	/////////////////////////////////////////////
		
	public void addEnv(Equation e) {
		TermVariable key = (TermVariable)e.getGauche();
		env.put(key, e.getDroite());
	}

	public Map<TermVariable,Term> getEnv() {
		return env;
	}
	
	public void clear() {
		env.clear();
	}
	
	public boolean isEmpty() {
		return env.isEmpty();
	}
	
	public void nettoieEnv(List<TermVariable> list) {
		Map<TermVariable, Term> nouvEnv = new HashMap<>();
		for (TermVariable key : env.keySet()) {
			if (list.contains(key)) {
				Term p = env.get(key);
				while (p instanceof TermVariable) {
					p = env.get(p);
				}
				nouvEnv.put(key, p);
				
			}
		}
		env = nouvEnv;
	}

	public String toString() {
		return "Environnement : "+env.toString();
	}
	
	public void afficherEnv() {
		for (TermVariable key : env.keySet()) {
			System.out.println(key + " -> "+ env.get(key));
		}
		if (env.isEmpty()) {
			System.out.println("Pas d'environnement");
		}
	}
	
	// renvoie un nouvel environnement avec les mêmes termes
	public Environnement copy() {
		Environnement res = new Environnement();
		for (TermVariable var : env.keySet()) {
			res.addEnv(new Equation(var,env.get(var)));
		}
		return res;
	}
}
