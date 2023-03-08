import { Component, Inject, OnInit, ViewChild } from '@angular/core';

import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { HttpService } from '../http.service';

export class ArticleDTO {
  id!: number;
  name!: string;
  description!: string;
  prezzo!: number;
}
export class POArticleDTO {
  aDTO: ArticleDTO = new ArticleDTO();
  quantity!: number;
}
export class PurchaseOrderDTO {
  id!: number;
  customerName!: string;
  creationDate!: Date;
  description!: string;
  supplierName!: string;
  type!: string;
  priority!: string;
  budgetCode!: number;
  poArticleDTOS!: Array<POArticleDTO>;
  isNew: boolean = false;
}


@Component({
  selector: 'app-modal_dialog',
  templateUrl: './modaldialog.component.html',
  styleUrls: ['./modaldialog.component.scss']
})

export class ModalComponent implements OnInit {
  displayedColumns = ["id", "description", "prezzo", "quantity", "totale", "actions"];
  id!: number;
  creationDate!: Date;
  isNew: boolean = false;

  dataSource = new MatTableDataSource<POArticleDTO>();

  ngAfterViewInit() {
  }

  constructor(public restApi: HttpService, public router: Router,
    public dialogRef: MatDialogRef<ModalComponent>,
    @Inject(MAT_DIALOG_DATA) public data: PurchaseOrderDTO, @Inject(MAT_DIALOG_DATA) public dataA: ArticleDTO) { this.id = data.id, this.isNew = data.isNew }

  @ViewChild(MatTable) table!: MatTable<ArticleDTO>;

  ngOnInit(): void {
    if (!this.isNew && this.id != null) //il Purchase Order non è nuovo: caso della modifica
      this.getPO(this.id);
    else //il Purchase Order è nuovo: caso della creazione
      this.firstAvaiableId(); //valorizzazione dell'id
    console.log("getPO", this.id);
    //o qui o nella save: distinzione di new se true o false in base a dove si apre il dialog
  }

  onSubmit2() {
    if (this.creationDate == null) //data non valorizzata:caso della creazione
      this.data.creationDate = new Date; //valorizzazione della data
    this.data.poArticleDTOS = this.dataSource.data;
    this.savePO(this.data);
    this.closeDialog();
    window.location.reload();
  }
  closeDialog() {
    this.dialogRef.close();
  }

  addRow(): void {
    this.dataSource.data.push(new POArticleDTO());
    this.table.renderRows();
  }

  removeRow() {
    this.dataSource.data.pop();
    this.table.renderRows();
  }

  getPO(id: number) {
    this.restApi.getPO(id).subscribe((data) => {
      this.data = data; this.dataSource.data = data.poArticleDTOS; //Qui assegno i dati
      console.log("data", data);
      console.log("data2", this.dataSource.data);
    })

  }

  getA(i: number, id: number) {
    console.log("getA - index", i);
    console.log("getA", id);
    this.restApi.getA(id).subscribe((data) => {
      this.dataA = data;
      this.dataSource.data.forEach((dat, index) => {
        console.log("index", index);
        if (index == i) {
          dat.aDTO.description = data.description;
          dat.aDTO.prezzo = data.prezzo;
        }
      })
      console.log("dataA", this.dataA);
      console.log("descr", this.dataA.description);
      console.log("prezzo", this.dataA.prezzo);
    })
  }

  savePO(PurchaseOrderDTO: any) {
    this.restApi.savePO(PurchaseOrderDTO).subscribe((data) => {
      this.data.id = data;
      this.data.isNew = false;
      console.log("data3", data);
    })
  }
  firstAvaiableId() {
    this.restApi.firstAvaiableId().subscribe((data) => {
      this.data.id = data;
      this.data.isNew = true;
      console.log("data4", data);
    })
  }
}