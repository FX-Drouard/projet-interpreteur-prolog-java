vote(X,Y) :- majeur(X),nationalite(X,Y).
majeur(X) :- personne(X).
pays(coree).
nationalite(X,Y) :- personne(X), pays(Y).
personne(chan).

?- vote(X,Y).