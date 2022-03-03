_Documentation du rendu de projet,Â  remplir._

# Jalon 1

## Question 1
```
    Comme nous avons choisi le langage Java, nous allons reprÃ©senter les Ã©quations par une classe Equation qui aura pour attributs deux Terme.
Ainsi, on pourra reprÃ©senter un systÃ¨me d'Ã©quations par une Collection de l'API Java. Ici, on utilisera les List, plus prÃ©cisÃ©ment les ArrayList afin de dynamiser le remplissage et amÃ©liorer l'efficacitÃ©.
	Nous crÃ©ons donc une classe Systeme qui implÃ©mentera les rÃªgles d'unification. Chaque mÃ©thode correspondant Ã  une rÃ¨gle est de visibilitÃ© private car elles sont utilisÃ©es uniquement par la mÃ©thode unify.
	Pour automatiser l'unification du systÃªme d'Ã©quations, nous dÃ©cidons d'essayer d'appliquer chaque rÃ¨gle au systÃ¨me entier dans un ordre arbitraire.
```

## Question 2
```
	Notre fonction occur_check est implÃ©mentÃ©e dans la classe Equation. Elle est de visibilitÃ© private car elle n'est appelÃ©e que localement dans la fonction formatROK. Cette fonction renvoie true si l'instance d'Equation est une bonne candidate pour appliquer la rÃ¨gle remplacer. C'est-Ã -dire que son Term gauche est une instance de TermVariable.
	occur_check utiise un TermVisitor, VisitorVar qui renvoie la liste des variables trouvÃ©es dans un Term.
Ainsi, elle lÃ¨ve l'exception NoSolutionException dans le cas oÃ¹ une variable qui se trouve dans le Term gauche est dans le Term droit d'une Equation. Cette Exception est rattrappÃ©e le plus tard possible, c'est-Ã -dire dans la mÃ©thode unify de la classe Systeme, ce qui nous permet de mettre fin Ã  la boucle d'unify.
```

## Question 3
```
	Pour l'environnement, nous l'implÃ©mentons Ã  l'aide d'une Map Java. Cela nous permet d'associer un TermVariable unique a un Term. Nous ajoutons cet environnement comme attribut Ã  notre classe Systeme afin d'allÃ©ger l'Ã©criture des algorithmes suivants.
```

## Question 4
```
	La substitution d'une variable dans une Equation est dÃ©lÃ©guÃ© Ã  la classe Equation qui prend un TermVariable Ã  remplacer et le Term correspondant. Cette mÃ©thode secondaire est appelÃ©e pour toutes les variables contenues dans l'environnement liÃ© au systÃ¨me passÃ© en paramÃ¨tre.
```

## Question 5
```
	Notre algorithme unify est une mÃ©thode d'instance propre Ã  un objet Systeme contenant des Ã©quations et un environnement. Il prend donc implicitement en paramÃ¨tre ces deux Ã©lÃ©ments.
Pour appliquer l'algorithme d'unification sur un systÃ¨me d'Ã©quation, il faut donc dans un premier temps instancier un Systeme et remplir son environnement et ses Ã©quations.
```


## Conclusion :

```
	Dans ce premier jalon, nous avons implantÃ© l'unification d'un systÃ¨me d'Ã©quations et le debut d'un affichage graphique.
Dans un premier temps, nous avons choisi d'implÃ©menter la rÃ¨gle subst (remplacer) sans utiliser le Design Pattern Visiteur car nous voulions changer directement les TermVariable sans dans les objets concernÃ©s : Equation et TermPredicat en tant qu'argument. Mais son comportement quasi similaire nous fait douter de l'implÃ©mentation de cette rÃ¨gle. Nous la modifierons sûrement plus tard dans le projet.
Dans un second temps nous avons crÃ©Ã© une interface graphique simple avec une zone d'Ã©dition de texte et un system de chargement/sauvegarde de la zone d'Ã©dition qui utilise un lecteur de fichier simple et utilise le parser donnÃ© dans le projet. Nous amÃ©liorerons cette interface avec le temps.

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
	Dans nos méthodes, nous vérifions que les programmes vérifient bien les conditions de l'énoncé. Si ce n'est pas le cas, nous levons l'exception FormatASTNotOK.
	On lève cette exception dans :
	-interprete0 quand :
		- il y a trop de déclarations
		- le but est placé avant le fait (ou il n'y a pas de fait) // est-ce que cela génère vraiment une erreur?
		- il n'y a pas qu'un seul but
	-interprete1 et interprete2 quand :
		- les DeclAssertion ne sont pas que des faits
		- il y a deux faits avec le même symbole de prédicat
	interprete1 lance en plus l'exception quand il y a trop de buts.
```

```
	Nos fichiers tests .pl se trouvent dans le dossier nommé `tests_jalon_pl`. Dans la classe Jalon1, nous avons ajouté les tests avec tous les fichiers .pl que nous fournissons.
```

```
	Dans les méthodes d'interprètes, la manière que nous avons de séparer les faits et les buts n'a pas l'air très optimale et est plutôt redondantes. Nous cherchons un meilleur moyen de le faire. Cela pourrait inclure le design pattern Visiteur.
```