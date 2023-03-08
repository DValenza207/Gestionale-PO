import { Component, Inject, OnInit, ViewChild } from '@angular/core';

import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { HttpService } from '../http.service';

export class ArticleDTO {
  id!: number;
  name!: string;
  description!: string;
  prezzo!: number;
  isNew: boolean = true;
}


@Component({
  selector: 'app-newPOdialog',
  templateUrl: './articledialog.component.html',
  styleUrls: ['./articledialog.component.scss']
})


export class ArticleDialogComponent implements OnInit {
  displayedColumns = ["id", "name", "description", "prezzo"];
  id!: number;
  isNew: boolean = true;

  constructor(public restApi: HttpService, public router: Router,
    public dialogRef: MatDialogRef<ArticleDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: ArticleDTO) { this.id = data.id, this.isNew = data.isNew }

  ngOnInit(): void {
    if (!this.isNew && this.id != null) //L'articolo non è nuovo: caso della modifica
      this.getA(this.id);
    else //articolo nuovo : caso della creazione
      this.firstAvaiableAId(); //valorizzazione dell'id
    console.log("getA2", this.id);
  }

  onSubmit() {
    this.saveA(this.data);
    this.closeDialog();
  }
  closeDialog() {
    this.dialogRef.close();
  }


  getA(id: number) {
    console.log("getA", id);
    this.restApi.getA(id).subscribe((data) => {
      console.log("data?", data);
      this.data = data;
    })
  }

  saveA(ArticleDTO: any) {
    this.restApi.saveA(ArticleDTO).subscribe((data) => {
      this.data.id = data;
      this.data.isNew = false;
      console.log("dataArticle", data);
      window.location.reload();
    })
  }

  firstAvaiableAId() {
    this.restApi.firstAvaiableAId().subscribe((data) => {
      this.data.id = data;
      this.data.isNew = true;
      console.log("dataAID", data);
    })
  }

}