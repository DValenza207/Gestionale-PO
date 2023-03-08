import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router, RouterLinkWithHref } from '@angular/router';
import { ArticleDialogComponent } from '../articleDialog/articledialog.component';
import { DeletePOComponent } from '../deletePODialog/deletePODialog.component';
import { HttpService } from "../http.service";
import { ModalComponent } from '../modal_dialog/modaldialog.component';

//interface PurchaseOrder e le colonne saranno quelle della tabella
export interface PurchaseOrder {
  id: number;
  customerName: string;
  creationDate: Date;
  description: string;
  supplierName: string;
  type: string;
  priority: string;
  budgetCode: number;
}

//interfaccia creata per adattare la mia paginazione in backend al frontend
export interface PagePurchaseOrder {
  content: PurchaseOrder[];
  totalElements: number;
}

@Component({
  selector: 'app-browse',
  templateUrl: './browse.component.html',
  styleUrls: ['./browse.component.scss']
})
export class BrowseComponent implements OnInit {
  displayedColumns = ["id", "customerName", "creationDate", "description", "supplierName", "type", "priority", "budgetCode", "actions"];

  myData: PurchaseOrder[] = []; //quello che mi arriva è un array di Purchase Orders


  constructor(public restApi: HttpService, public router: Router, public dialog: MatDialog) { }
  isLoading = false;
  totalRows = 0;
  pageSize = 5;
  currentPage = 0;
  pageSizeOptions: number[] = [5, 10, 25, 50];

  @Input('details') poDetails = {
    creationDate: '', type: '', description: '', creationDateFrom: "", creationDateTo: "",
    page: "", size: ""
  };


  clickedRow(row: any) {
    //Apro il dialog, ma non richiamo la getPO
    const dialogRef = this.dialog.open(ModalComponent, {
      width: '1900px',
      data: { id: row.id, isNew: false },
      panelClass: 'PObg-color',
      disableClose: true
    });
    console.log("row", row);
  }

  dataSource = new MatTableDataSource<PurchaseOrder>(this.myData);
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sorting!: MatSort;




  ngAfterViewInit() {
    this.dataSource.sort = this.sorting;
  }

  ngOnInit(): void {
    this.poDetails.page = `${this.currentPage}`;
    this.poDetails.size = `${this.pageSize}`;
    console.log("init", this.poDetails);
    this.browsePO(this.poDetails)
  }

  pageChanged(event: PageEvent) {
    console.log("event", { event });
    this.pageSize = event.pageSize;
    this.currentPage = event.pageIndex;
    this.poDetails.page = `${this.currentPage}`;
    this.poDetails.size = `${this.pageSize}`;
    this.browsePO(this.poDetails);
  }

  deleteDialog(button: any) {
    const dialogRef = this.dialog.open(DeletePOComponent, {
      width: '300px',
      data: { id: button },
      disableClose: true
    });
    console.log("button", button);
  }

  browsePO(dataPO: any) {
    this.isLoading = true;
    console.log("browsePo", dataPO);

    this.restApi.browsePO(dataPO).subscribe((data) => {
      // data è risposta del backend
      this.dataSource.data = data.content;
      this.paginator.length = data.totalElements; // total number of POs
      this.totalRows = this.paginator.length;
      this.isLoading = false;
    });
  }
}
