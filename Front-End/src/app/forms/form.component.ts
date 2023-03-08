import { Component, OnInit, SimpleChanges, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { BrowseComponent } from '../browse/browse.component';
import { HttpService } from '../http.service';
import { ModalComponent } from '../modal_dialog/modaldialog.component';

/** @title Simple form field */
@Component({
  selector: 'app-forms',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.scss']
})
export class FormFieldOverviewExample implements OnInit {
  @ViewChild(BrowseComponent) child!: BrowseComponent;

  constructor(public restApi: HttpService, public dialog: MatDialog) { }
  details = { creationDate: "", description: "", type: "", creationDateFrom: "", creationDateTo: "", page: "", size: "" };


  ngOnChanges(changes: SimpleChanges): void {
    console.log("form", this.details);
  }

  ngOnInit(): void { }

  onSubmit(form: NgForm) {
    console.log("submit", form);
    this.child.browsePO(this.details);
  }

  newPO() {
    //Apro il dialog, ma non richiamo la savePO
    const dialogRef = this.dialog.open(ModalComponent, {
      width: '1800px',
      data: { id: null, isNew: true },
      disableClose: true
    });
  }



  //La mia idea è di aprire un dialog da un pulsante + dalla riga dei forms
  //e nel component del dialog chiamare la save dopo aver riempito tutti i campi del PO e aver cliccato salva

}
