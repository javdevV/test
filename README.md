# Candidat(e): Développeur Java chez Canal Plus

# language: fr

@adresse @modification

Fonctionnalité: Modifier l'adresse d'un abonné

Plan du scénario: Modification de l'adresse d'un abonné résidant en France sans ou avec date d'effet

Etant donné un abonné avec une adresse principale <active> en France

Lorsque le conseiller connecté à <canal> modifie l'adresse de l'abonné

Alors l'adresse de l'abonné modifiée est enregistrée sur l'ensemble des contrats de l'abonné Et un mouvement de modification d'adresse est créé

Cloner Le projet:
<pre>
git clone https://github.com/javdevV/test
</pre>

Lancer Les tests:
<pre>
cd test 
mvn verify
</pre>
