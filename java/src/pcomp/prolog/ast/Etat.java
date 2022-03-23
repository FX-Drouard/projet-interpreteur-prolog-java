package pcomp.prolog.ast;

import java.util.ArrayList;
import java.util.List;

import pcomp.prolog.ast.excep.AlreadyInListException;
import pcomp.prolog.ast.excep.NotInListException;

public class Etat {
	private Environnement env;
	private List<String> state;
	public Etat(Environnement env) {
		this.env=env.copy();
		this.state=new ArrayList<String>();
	}
	
	public void addState(String x) throws AlreadyInListException{
		for (int i=0; i<state.size();i++) {
			if (state.get(i).equals(x)) {
				throw new AlreadyInListException("Element : | "+x+" | deja dans State a la place : "+i+" \n");
			}
		}
		state.add(x);
	}
	
	public void delState(String x) throws NotInListException {
		int i;
		for ( i=0; i<state.size();i++) {
			if (state.get(i).equals(x)) {
				state.remove(i);
				return; 
			}
		}
		throw new NotInListException("Parmis les "+i+" element de State il n'y a pas de | "+x+" | !\n");
	}
	
	public String getOneState() {
		if (state.size()==0) {
			return "N";
		}
		else {
			String res=state.get(state.size()-1);
			state.remove(state.size()-1);
			return res;
		}
	}
	
	public Environnement getEnv() {
		return env.copy();
	}
	
}
