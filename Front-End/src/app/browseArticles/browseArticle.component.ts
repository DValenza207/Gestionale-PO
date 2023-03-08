import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router, RouterLinkWithHref } from '@angular/router';
import { ArticleDialogComponent } from '../articleDialog/articledialog.component';
import { DeleteArticleComponent } from '../deleteADialog/deleteADialog.component';
import { HttpService } from "../http.service";

//interface Article e le colonne saranno quelle della tabella
export interface Article {
  id: number;
  name: string;
  description: string;
  prezzo: number;
}

//interfaccia creata per adattare la mia paginazione in backend al frontend
export interface PageArticle {
  content: Article[];
  totalElements: number;
}

@Component({
  selector: 'app-browseArticles',
  templateUrl: './browseArticle.component.html',
  styleUrls: ['./browseArticle.component.scss']
})
export class BrowseArticleComponent implements OnInit {
  displayedColumns = ["id", "name", "description", "prezzo", "actions"];

  myData: Article[] = []; //quello che mi arriva è un array di Articoli

  constructor(public restApi: HttpService, public router: Router, public dialog: MatDialog) { }
  isLoading = false;
  totalRows = 0;
  pageSize = 5;
  currentPage = 0;
  pageSizeOptions: number[] = [5, 10, 25, 50];

  @Input('Adetails') ADetails = {
    name: '', description: '', prezzo: '', page: "", size: ""
  };

  clickedRow(row: any) {
    //Apro il dialog, ma non richiamo la getPO
    const dialogRef = this.dialog.open(ArticleDialogComponent, {
      width: '1900px',
      data: { id: row.id },
      panelClass: 'Abg-color',
      disableClose: true
    });
    console.log("row", row);
  }

  dataSource = new MatTableDataSource<Article>(this.myData);
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sorting!: MatSort;

  ngAfterViewInit() {
    this.dataSource.sort = this.sorting;
  }

  ngOnInit(): void {
    this.ADetails.page = `${this.currentPage}`;
    this.ADetails.size = `${this.pageSize}`;
    console.log("init", this.ADetails);
    this.browseA(this.ADetails)
  }

  pageChanged(event: PageEvent) {
    console.log("event", { event });
    this.pageSize = event.pageSize;
    this.currentPage = event.pageIndex;
    this.ADetails.page = `${this.currentPage}`;
    this.ADetails.size = `${this.pageSize}`;
    this.browseA(this.ADetails);
  }

  deleteADialog(button: any) {
    const dialogRef = this.dialog.open(DeleteArticleComponent, {
      width: '300px',
      data: { id: button },
      disableClose: true
    });
    console.log("button", button);
  }

  browseA(dataA: any) {
    this.isLoading = true;
    console.log("browseA", dataA);
    this.restApi.browseA(dataA).subscribe((data) => {
      // data è la risposta del backend
      this.dataSource.data = data.content;
      console.log("data", this.dataSource.data);
      this.paginator.length = data.totalElements; // total number of Articles
      this.totalRows = this.paginator.length;
      this.isLoading = false;
    });
  }
}