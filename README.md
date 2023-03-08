# Sviluppo di un'applicazione web per la gestione di ordini d'acquisto ed articoli in ambito portuale e doganale

Linguaggi: Java, Typescript, SQL
Markup: HTML, scss

Tecnologie Utilizzate: 
Back-end: Spring Boot, Apache Maven, MySQL
Front-end: Angular CLI




This project was generated with Angular CLI version 14.2.2.

Development server
Run ng serve for a dev server. Navigate to http://localhost:4200/. The application will automatically reload if you change any of the source files.

Code scaffolding
Run ng generate component component-name to generate a new component. You can also use ng generate directive|pipe|service|class|guard|interface|enum|module.

Build
Run ng build to build the project. The build artifacts will be stored in the dist/ directory.

Running unit tests
Run ng test to execute the unit tests via Karma.

Running end-to-end tests
Run ng e2e to execute the end-to-end tests via a platform of your choice. To use this command, you need to first add a package that implements end-to-end testing capabilities.

Further help
To get more help on the Angular CLI use ng help or go check out the Angular CLI Overview and Command Reference page.




Come Iniziare:

Parte Back-end
Clonare la repository da linea di comando tramite git clone seguito da https://github.com/DValenza207/Gestionale-PO.git,
oppure dal menù del proprio IDE sotto la sezione GIT, selezionare clone o clona ed inserire il link riportato.
Installare Intellij Idea.
Aprire il progetto e, se appaiono errori riguardanti le entità di QueryDSL, dal menù laterale inerente a maven utilizzare i comandi clean, compile e package sotto a lifecycle per generare la cartella target in cui sono contenute le entità di QueryDSL: sono indispensabili per la compilazione ed esecuzione del progetto.
Per compilare ed eseguire il progetto: tasto destro su DemoApplication.java dal menù laterale e cliccare su Run DemoApplication.

Parte Front-end
Clonare la repository da linea di comando tramite git clone seguito da https://github.com/DValenza207/Gestionale-PO.git,
oppure dal menù del proprio IDE sotto la sezione GIT, selezionare clone o clona ed inserire il link riportato.
Installare NodeJS e Visual Studio Code ed aprire il progetto.
Una volta installati si potrà installare Angular CLI o da terminale o dal terminale integrato di Visual Studio Code attraverso il comando
npm install -g @angular/cli.
Per compilare ed eseguire l'applicazione: tasto destro su app(nel menù laterale, sotto a src) -> apri nel terminale integrato e nel terminale digitare il comando ng serve -o.
Per poter eseguire i comandi dal terminale integrato occorre abilitare l'esecuzione di script dalla Windows Powershell(da eseguire come amministratore) oppure da altri terminali(es. git bash): per controllare l'Execution Policy digitare il comando Get-ExecutionPolicy, se appare restricted occore cambiarla in remotesigned utilizzando Set-ExecutionPolicy.

Parte di Database
Utilizzare il file CreateDatabase.sql all'interno della cartella CreateDatabase presente nella cartella del Backend ed utilizzarlo per creare le tabelle.
