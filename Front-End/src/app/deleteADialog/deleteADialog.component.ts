import { Component, OnInit, Inject, ViewChild } from "@angular/core";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material/dialog";
import { MatTable, MatTableDataSource } from "@angular/material/table";
import { Router } from "@angular/router";
import { HttpService } from "../http.service";

export class ArticleDTO {
    id!: number;
    name!: string;
    description!: string;
    prezzo!: number;
}

@Component({
    selector: 'app-deleteADialog',
    templateUrl: './deleteADialog.component.html',
    styleUrls: ['./deleteADialog.component.scss']
})
export class DeleteArticleComponent implements OnInit {
    id!: number;

    dataSource = new MatTableDataSource<ArticleDTO>();



    constructor(public restApi: HttpService, public router: Router,
        public dialogRef: MatDialogRef<DeleteArticleComponent>,
        @Inject(MAT_DIALOG_DATA) public data: ArticleDTO) { this.id = data.id }

    ngOnInit(): void { }

    deleteA(id: number) {
        this.restApi.deleteA(id).subscribe(() => {
            this.data.id = id;
            this.dataSource.data.pop();
            this.closeDialog();
            window.location.reload();
        })
    }


    closeDialog() {
        this.dialogRef.close();
    }

}
