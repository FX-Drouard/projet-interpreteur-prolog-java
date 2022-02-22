package pcomp.prolog.ast;

import java.util.ArrayList;
import java.util.List;

public class Systeme {
	
	private List<Equation> eqs = new ArrayList<>();
	
	public void addEquation(Equation e) {
		eqs.add(e);
	}
	
	public void removeEquation(Equation e) {
		eqs.remove(e);
	}
	
	//Renvoie une copie en surface de la liste eqs
	private List<Equation> copie() {
		List<Equation> res = new ArrayList<>();
		for (Equation e : eqs) {
			res.add(e);
		}
		return res;
	}
	
	public void unification() {
		// regleapp sert de condition d'arrêt de notre boucle d'unification
		boolean regleapp = true;
		// liste contiendra le nouveau système issu d'une boucle dans l'unification
		List<Equation> nouvSys;
		while (regleapp) {
			regleapp = false; //aucune règle n'a été appliquée sur le système pendant ce tour
			nouvSys = copie();
			
			// Application des règles
			
			eqs = nouvSys;
		}
	}

}
