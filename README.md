 ### TicketMaster-Esame di Programmazione ad Oggetti
 ### Introduzione
Il programma permette di vedere tutti gli eventi presenti in un determinato periodo,in un determinato paese.Inserendo una nazione (es. US) il programma effettuerà una chiamata API
e ritornerà tutti gli eventi presenti nella nazione digitata in ingresso. In aggiunta alla nazione, in ingresso, si può aggiungere una parola chiave(es.NFL) o un periodo temporale. Il programma offre anche funzionalità riguardanti il fitraggio per periodo,genere e parola chiave e offre metodi per calcolare le statistiche per genere, per eventi mensili dando in uscita minimo massimo e media degli eventi. Per poter utilizzare correttamente il programma consigliamo l'utilizzo di POSTMAN. 
### UML
**UseCase Diagram**
L'utente si interfaccia al programma per cercare gli eventi in una nazione inserendo il codice identificativo della nazione(non accetta il nome completo: es.Stati uniti errato mentre US ritornerà la risposta del programma), oppure inserendo una parola chiave o un periodo temporale. Inoltre l'utente potrà direttamente applicare un filtraggio in base al genere,al periodo e alla parola chiave e potrà leggere le statistiche riguardati la nazione inserita.

 **Class Diagram**
Il programma è suddiviso in packages:

Il package _Controller_ contiene la classe ControllerClass è all' interno sono presenti i metodi per la chiamata API per il filtraggio e per le statistiche che si occupa di gestire le chiamate GET.

Il package _Service_ contiene la classe APICall. la classe si occupa di gestire le chiamate API prendendo in ingresso le informazioni inserite dall'utente.

Il package _filter_ contiene la classe Filter.La classe si occupa di filtrare in base al genere,al periodo temporale e alla parola chiave e queste informazioni saranno passate dai rispettivi metodi presenti nella classe ControllerClass.

Il package _stats_ contiene la classe Stats. La classe si occupa di calcolare in numero di eventi presenti in una nazione in un determinato periodo temporale.

Il package _Test_ contiene le classi filterTest e statsTest. Si occupano di testare le relative classi.

### ROTTE
Le richieste che l'utente può fare usando Postman devono essere effettuate all'indirizzo localhost:8080

![Annotazione 2021-02-15 172217](https://user-images.githubusercontent.com/75211109/107972892-c7b11600-6fb4-11eb-9847-20678c84a314.png)





![Annotazione 2021-02-15 172218](https://user-images.githubusercontent.com/75211109/107975543-8e7aa500-6fb8-11eb-81bf-c84aa0b39a82.png)
