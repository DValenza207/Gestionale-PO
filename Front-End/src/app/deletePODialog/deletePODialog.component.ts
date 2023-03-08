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
    selector: 'app-deletePODialog',
    templateUrl: './deletePODialog.component.html',
    styleUrls: ['./deletePODialog.component.scss']
})
export class DeletePOComponent implements OnInit {
    id!: number;

    dataSource = new MatTableDataSource<PurchaseOrderDTO>();


    constructor(public restApi: HttpService, public router: Router,
        public dialogRef: MatDialogRef<DeletePOComponent>,
        @Inject(MAT_DIALOG_DATA) public data: PurchaseOrderDTO) { this.id = data.id }

    ngOnInit(): void { }

    deletePO(id: number) {
        this.restApi.deletePO(id).subscribe(() => {
            //console.log("table", this.tablePO);
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