_Documentation du rendu de projet,Â  remplir._

# Jalon 1

## Question 1
```
    Comme nous avons choisi le langage Java, nous allons représenter les équations par une classe Equation qui aura pour attributs deux Terme.
Ainsi, on pourra représenter un systême d'équations par une Collection de l'API Java. Ici, on utilisera les List, plus précisément les ArrayList afin de dynamiser le remplissage et améliorer l'efficacité.
	Nous créons donc une classe Systeme qui implémentera les règles d'unification. Chaque méthode correspondant à une règle est de visibilité private car elles sont utilisées uniquement par la méthode unify.
	Pour automatiser l'unification du système d'équations, nous décidons d'essayer d'appliquer chaque règle au système entier dans un ordre arbitraire.
```

## Question 2
```
	Notre fonction occur_check est implémentée dans la classe Equation. Elle est de visibilité private car elle n'est appelée que localement dans la fonction formatROK. Cette fonction renvoie true si l'instance d'Equation est une bonne candidate pour appliquer la règle remplacer. C'est-à-dire que son Term gauche est une instance de TermVariable.
	occur_check utiise un TermVisitor, VisitorVar qui renvoie la liste des variables trouvées dans un Term.
Ainsi, elle lève l'exception NoSolutionException dans le cas où une variable qui se trouve dans le Term gauche est dans le Term droit d'une Equation. Cette Exception est rattrappée le plus tard possible, c'est-à-dire dans la méthode unify de la classe Systeme, ce qui nous permet de mettre fin à la boucle d'unify.
```

## Question 3
```
	Pour l'environnement, nous l'implémentons à l'aide d'une Map Java. Cela nous permet d'associer un TermVariable unique a un Term. Nous ajoutons cet environnement comme attribut à notre classe Systeme afin d'alléger l'écriture des algorithmes suivants.
```

## Question 4
```
	La substitution d'une variable dans une Equation est délégué à la classe Equation qui prend un TermVariable à remplacer et le Term correspondant. Cette méthode secondaire est appelée pour toutes les variables contenues dans l'environnement lié au système passé en paramètre.
```

## Question 5
```
	Notre algorithme unify est une méthode d'instance propre à un objet Systeme contenant des équations et un environnement. Il prend donc implicitement en paramètre ces deux éléments.
Pour appliquer l'algorithme d'unification sur un système d'équation, il faut donc dans un premier temps instancier un Systeme et remplir son environnement et ses équations.
```


## Conclusion :

```
	Dans ce premier jalon, nous avons implanté l'unification d'un système d'équations et le debut d'un affichage graphique.
Dans un premier temps, nous avons choisi d'implémenter la règle subst (remplacer) sans utiliser le Design Pattern Visiteur car nous voulions changer directement les TermVariable sans dans les objets concernés : Equation et TermPredicat en tant qu'argument. Mais son comportement quasi similaire nous fait douter de l'implémentation de cette règle. Nous la modifierons sûrement plus tard dans le projet.
Dans un second temps nous avons créé une interface graphique simple avec une zone d'édition de texte et un systeme de chargement/sauvegarde de la zone d'édition qui utilise un lecteur de fichier simple et utilise le parser donné dans le projet. Nous améliorerons cette interface avec le temps.

	Pour exécuter ce Jalon, il suffit d'exécuter la classe MainUnification qui contient tous les exemples de l'exercice 7 du TD4 concernant l'unification. La vérification du bon fonctionnement des fonctions se base sur les affichages du système d'équations et de l'environnement à chaque règle appliquée.
	Nous avons rencontré des difficultés concernant l'implantation de la règle remplacer. Comme nous l'avons dit précédemment, nous avons des doutes sur l'efficacité de nos méthodes.
```

#Jalon 2

```
	Nous avons renommé notre classe MainUnification qui testait les unifications des équations du TD par `Jalon1` pour correspondre à l'énoncé.
	Nous avons également modifié la classe MainTestRegles qui avait pour but de tester les règles d'unification une à une et de contrôler leurs effets sur le système par l'affichage. Ces mêmes affichages, pour ce Jalon 2, ont été commentées afin de ne pas alourdir l'affichage des interprètes.
	Une classe Environnement a été créée afin de ne pas surcharger la classe Systeme et aléger la signature des méthodes créées pour ce Jalon.
```

```
	Nous plaçons les méthodes des différents interprètes une classe "statique" Interprete.
	Dans nos méthodes, nous vérifions que les programmes vérifient bien les conditions de l'énoncé. Si ce n'est pas le cas, nous levons l'exception IllegalArgumentException.
	On lève cette exception dans :
	-interprete0 quand :
		- une DeclAssertion n'est pas un fait
		- il n'y a pas qu'un seul fait
		- il n'y a pas qu'un seul but
	-interprete1 et interprete2 quand :
		- les DeclAssertion ne sont pas que des faits
		- il y a deux faits avec le même symbole de prédicat
	interprete1 lance en plus l'exception quand il y a trop de buts.
```

```
	Nos fichiers tests .pl se trouvent dans le dossier nommé `tests_jalon_pl`. Dans la classe Jalon1 (du package pcomp.prolog.ast.test), nous avons ajouté les tests avec tous les fichiers .pl que nous fournissons.
	Nous testons aussi les cas où l'interprete rejetterait le Program passé en paramètres.
```

```
	Dans les méthodes d'interprètes, la manière que nous avons de séparer les faits et les buts n'était pas très optimale et était plutôt redondante. Nous avons donc décidé d'implanter un DeclVisitor que l'on appelle VisitorDecl. Il sépare les faits et buts et construit les listes. La valeur de retour ne nous sert pas encore donc nous avons mis des List<Predicate> par défaut.
	Ce visiteur pourra être étoffé s'il faut à l'avenir traiter les DeclAssertion qui ne seraient pas des faits.
```

#Jalon 3
```
	Nous avons déplacé les classes exécutables correspondant aux Jalons dans le package principal pcomp.prolog.ast afin de les mettre davantage en évidence.
```

```
	Pour la méthode rename que l'on place dans la classe DeclAssertion, nous implémentons un autre TermVisitor qui nous renvoie un nouveau Term avec les variables renommées. La méthode principale délègue le renommage des variables au Predicate qui utilise le visiteur décrit précédemment.
```

```
	Notre méthode choose, pour le moment private, est placée dans notre classe statique Interprete car elle sert à l'implantation de la méthode interprete3.
```