### **TicketMaster-Esame di Programmazione ad Oggetti**
### **Introduzione**
Il programma permette di vedere tutti gli eventi presenti in un determinato periodo,in un determinato paese.Inserendo una nazione (es. US) il programma effettuerà una chiamata API e ritornerà tutti gli eventi presenti nella nazione digitata in ingresso. In aggiunta alla nazione, in ingresso, si può aggiungere una parola chiave(es.NFL) o un periodo temporale. Il programma offre anche funzionalità riguardanti il fitraggio per periodo,genere e parola chiave e offre metodi per calcolare le statistiche per genere, per eventi mensili dando in uscita minimo massimo e media degli eventi. Per poter utilizzare correttamente il programma consigliamo l'utilizzo di POSTMAN.

### **UML**
**UseCase Diagram**

![Annotazione 2021-02-15 172220](https://user-images.githubusercontent.com/75211109/108225688-7469cf80-713c-11eb-9840-9be0b629de46.png)


 L'utente si interfaccia al programma per cercare gli eventi in una nazione inserendo il codice identificativo della nazione(non accetta il nome completo: es.Stati uniti errato mentre US ritornerà la risposta del programma), oppure inserendo una parola chiave o un periodo temporale. Inoltre l'utente potrà direttamente applicare un filtraggio in base al genere,al periodo e alla parola chiave e potrà leggere le statistiche riguardati la nazione inserita.

**Class Diagram**

 Il programma è suddiviso in packages:

![Class Diagram](https://user-images.githubusercontent.com/75218985/108222881-7d0cd680-7139-11eb-8fbd-84cd0534d24f.PNG)

Il package _Controller_ contiene la classe ControllerClass è all' interno sono presenti i metodi per la chiamata API per il filtraggio e per le statistiche che si occupa di gestire le chiamate GET.

Il package _Service_ contiene la classe APICall. la classe si occupa di gestire le chiamate API prendendo in ingresso le informazioni inserite dall'utente.

Il package _Filter_ contiene la classe Filter.La classe si occupa di filtrare in base al genere,al periodo temporale e alla parola chiave e queste informazioni saranno passate dai rispettivi metodi presenti nella classe ControllerClass.

Il package _Stats_ contiene la classe Stats. La classe si occupa di calcolare in numero di eventi presenti in una nazione in un determinato periodo temporale.
Il package _Exception_ contiene le classi paeseException e mesiException. Si occupano di stampare un messaggio di errore nel caso i mesi o il paese siano errati.

Il package _Test_ contiene le classi filterTest e statsTest. Si occupano di testare le relative classi.

### ROTTE
Le richieste che l'utente può fare usando Postman devono essere effettuate all'indirizzo localhost:8080

![Annotazione 2021-02-15 172217](https://user-images.githubusercontent.com/75211109/108226433-3325ef80-713d-11eb-9ec1-be59d3ad4c4a.png)

![Annotazione 2021-02-15 172218](https://user-images.githubusercontent.com/75211109/108226558-4e90fa80-713d-11eb-9724-a9e95a998301.png)


Invece per le date :

![Annotazione 2021-02-15 172219](https://user-images.githubusercontent.com/75211109/108226620-594b8f80-713d-11eb-8887-98b639c5cc1f.png)


Attenzione: il programma accetta solo date scritte in formato ISO 8601 (yyyy-mm-ggT00:00:00Z).

**Eccezioni**

Abbiamo gestito le eccezioni creando due nuove classi: paeseException e mesiException. l'obbiettivo di tali classi e stampare un messaggio visibile su postman. Nel caso l'ID della nazione non è presente nel file .txt ritornerà una stringa e allo stesso modo funziona la classe mesiException che stamperà un messaggio quando i mesi inseriti nel filtraggio è superiore a 12.

**Test**

Abbiamo implementato due unità di test: FilterTest per testare la classe Filters. Viene testato il metodo che gestisce il filtraggio temporale. StatsTest per testare la classe Stats. Viene testato il metodo che gestisce le statistiche temporali.

### Autori

Il programma è stato creato da Filippo Polidori e Walter Nigito.
