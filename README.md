# TicketMaster-Esame di Programmazione ad Oggetti
# Introduzione
Il programma permette di vedere tutti gli eventi presenti in un determinato periodo,in un determinato paese.Inserendo una nazione (es. US) il programma effettuerà una chiamata API
e ritornerà tutti gli eventi presenti nella nazione digitata in ingresso. In aggiunta alla nazione, in ingresso, si può aggiungere una parola chiave(es.NFL) o un periodo temporale. Il programma offre anche funzionalità riguardanti il fitraggio per periodo,genere e parola chiave e offre metodi per calcolare le statistiche per genere, per eventi mensili dando in uscita minimo massimo e media degli eventi. Per poter utilizzare correttamente il programma consigliamo l'utilizzo di POSTMAN. 
# UML
UseCase Diagram
L'utente si interfaccia al programma per cercare gli eventi in una nazione inserendo il codice identificativo della nazione(non accetta il nome completo: es.Stati uniti errato mentre US ritornerà la risposta del programma), oppure inserendo una parola chiave o un periodo temporale. Inoltre l'utente potrà direttamente applicare un filtraggio in base al genere,al periodo e alla parola chiave e potrà leggere le statistiche riguardati la nazione inserita.

 Class Diagram
Il programma è suddiviso in packages:

Il package Controller contiene la classe ControllerClass è all' interno sono presenti i metodi per la chiamata API per il filtraggio e per le statistiche che si occupa di gestire le chiamate GET.

Il package Service contiene la classe APICall. la classe si occupa di gestire le chiamate API prendendo in ingresso le informazioni inserite dall'utente.

Il package filter contiene la classe Filter.La classe si occupa di filtrare in base al genere,al periodo temporale e alla parola chiave e queste informazioni sarranno passate dai rispettivi metodi prensenti nella classe ControllerClass.

Il package stats contiene la classe Stats. La classe si occupa di calcolare in numero di eventi prensenti in una nazione in un determinato periodo temporale.

Il package Test contiene le classi filterTest e statsTest.Si occupano di testare le relavite classi.

# ROTTE
Le richieste che l'utente può fare usando postman


![Annotazione 2021-02-15 172216](https://user-images.githubusercontent.com/75211109/107971525-e0203100-6fb2-11eb-921a-df5b11c8fcfd.png)
