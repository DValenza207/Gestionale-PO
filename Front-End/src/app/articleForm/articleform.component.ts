import { Component, OnInit, SimpleChanges, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { HttpService } from '../http.service';
import { BrowseArticleComponent } from '../browseArticles/browseArticle.component';
import { ArticleDialogComponent } from '../articleDialog/articledialog.component';

@Component({
    selector: 'app-articleForm',
    templateUrl: './articleform.component.html',
    styleUrls: ['./articleform.component.scss']
})
export class ArticleFormComponent implements OnInit {
    @ViewChild(BrowseArticleComponent) child!: BrowseArticleComponent;

    constructor(public restApi: HttpService, public dialog: MatDialog) { }
    Adetails = { name: "", description: "", prezzo: "", page: "", size: "" };

    ngOnChanges(changes: SimpleChanges): void {
        console.log("form", this.Adetails);
    }

    ngOnInit(): void { }

    onSubmit(form: NgForm) {
        console.log("submit", form);
        this.child.browseA(this.Adetails);
    }

    newA() {
        //Apro il dialog, ma non richiamo la saveA
        const dialogRef = this.dialog.open(ArticleDialogComponent, {
            width: '1800px',
            data: { id: null },
            disableClose: true
        });
    }


}