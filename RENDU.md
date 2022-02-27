_Documentation du rendu de projet,  remplir._

# Jalon 1

## Question 1
```
    Comme nous avons choisi le langage Java, nous allons représenter les équations par une classe Equation qui aura pour attributs deux Terme.
Ainsi, on pourra représenter un système d'équations par une Collection de l'API Java. Ici, on utilisera les List, plus précisément les ArrayList afin de dynamiser le remplissage et améliorer l'efficacité.
	Nous créons donc une classe Systeme qui implémentera les rêgles d'unification. Chaque méthode correspondant à une règle est de visibilité private car elles sont utilisées uniquement par la méthode unify.
	Pour automatiser l'unification du systême d'équations, nous décidons d'essayer d'appliquer chaque règle au système entier dans un ordre arbitraire.
```

## Question 2
```
	Notre fonction occur_check est implémentée dans la classe Equation. Elle est de visibilité private car elle n'est appelée que localement dans la fonction formatROK. Cette fonction renvoie true si l'instance d'Equation est une bonne candidate pour appliquer la règle remplacer. C'est-à-dire que son Term gauche est une instance de TermVariable.
	occur_check utiise un TermVisitor, VisitorVar qui renvoie la liste des variables trouvées dans un Term.
Ainsi, elle lève l'exception NoSolutionException dans le cas où une variable se trouve dans le terme gauche et droit d'une Equation. Cette Exception est rattrappée le plus tard possible, c'est-à-dire dans la méthode unify de la classe Systeme, ce qui nous permet de mettre fin à la boucle d'unify.
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
Dans un premier temps, nous avons choisi d'implémenter la règle subst (remplacer) sans utiliser le Design Pattern Visiteur car nous voulions changer directement les TermVariable sans dans les objets concernés : Equation et TermPredicat en tant qu'argument. Mais son comportement quasi similaire nous fait douter de l'implémentation de cette règle. Nous la modifierons peut-être plus tard dans le projet.
Dans un second temp nous avons créé une interface graphique simple avec une zone d'édition de texte et un system de chargement/sauvegarde de la zone d'édition qui utilise un lecteur de fichier simple et utilise le parser donné dans le projet. Nous améliorerons cette interface avec le temps.
```
