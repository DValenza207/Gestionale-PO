import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest, HttpStatusCode } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { PagePurchaseOrder } from './browse/browse.component';
import { PageArticle } from './browseArticles/browseArticle.component';
import { PurchaseOrderDTO } from './modal_dialog/modaldialog.component';
import { ArticleDTO } from './articleDialog/articledialog.component';
import { AuthenticationService } from './login-dialog/auth.service';
import { LoginResponse } from './login-dialog/login-dialog.component';

@Injectable({
  providedIn: 'root'
})
export class HttpService implements HttpInterceptor {
  apiURL = 'http://localhost:8080'
  constructor(private http: HttpClient, private authenticationService: AuthenticationService) { }


  // Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': '*',
      'Access-Control-Allow-Headers': '*',
      'Access-Control-Allow-Methods': 'POST, GET, PUT, DELETE, OPTIONS'
    }),
  };

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    console.log("token", this.authenticationService.getUserToken());
    if (this.authenticationService.isUserLoggedIn()) {
      console.log("token!", this.authenticationService.getUserToken());
      const authReq = req.clone({
        setHeaders: ({
          Authorization: `Bearer ${this.authenticationService.getUserToken()}`,
        })
      });
      return next.handle(authReq).pipe(retry(0), catchError(this.handleError));
    }
    else {
      return next.handle(req).pipe(retry(0), catchError(this.handleError));
    }
  }

  //Login
  login(UserDTO: any): Observable<LoginResponse> {
    console.log("User!", UserDTO);
    return this.http
      .post<LoginResponse>(
        this.apiURL + '/admin/basicauth',
        JSON.stringify(UserDTO),
        this.httpOptions
      )
    //.pipe(retry(0), catchError(this.handleError))
  }

  // HttpClient API post() method => BrowsePurchaseOrder
  browsePO(PurchaseOrder: any): Observable<PagePurchaseOrder> {
    return this.http
      .post<PagePurchaseOrder>(
        this.apiURL + '/admin/Purchase_Orders/browse',
        JSON.stringify(PurchaseOrder),
        this.httpOptions
      )
      .pipe(retry(0), catchError(this.handleError));
  }

  // HttpClient API post() method => BrowseArticle
  browseA(Article: any): Observable<PageArticle> {
    return this.http
      .post<PageArticle>(
        this.apiURL + '/admin/Articles/browse',
        JSON.stringify(Article),
        this.httpOptions
      )
      .pipe(retry(0), catchError(this.handleError));
  }


  //HttpClient API get() method=> id
  getPO(idPurchaseOrder: number): Observable<PurchaseOrderDTO> {
    return this.http
      .get<PurchaseOrderDTO>(
        this.apiURL + '/admin/Purchase_Orders/get?id=' + idPurchaseOrder,
        this.httpOptions
      )
      .pipe(retry(1), catchError(this.handleError));
  }

  //HttpClient API save() method=> PurchaseOrderDTO
  //Utilizzato per l'update
  savePO(PurchaseOrderDTO: any): Observable<number> {
    return this.http
      .post<number>(
        this.apiURL + '/admin/Purchase_Orders/save',
        JSON.stringify(PurchaseOrderDTO),
        this.httpOptions
      )
      .pipe((retry(1)), catchError(this.handleError));
  }

  getA(id: number): Observable<ArticleDTO> {
    return this.http
      .get<ArticleDTO>(
        this.apiURL + '/admin/Articles/get?id=' + id,
        this.httpOptions
      )
      .pipe((retry(1), catchError(this.handleError)));
  }

  saveA(ArticleDTO: any): Observable<number> {
    return this.http
      .post<number>(
        this.apiURL + '/admin/Articles/save',
        JSON.stringify(ArticleDTO),
        this.httpOptions
      )
      .pipe((retry(1), catchError(this.handleError)));
  }

  firstAvaiableId(): Observable<number> {
    return this.http
      .get<number>(
        this.apiURL + '/admin/Purchase_Orders/getId',
        this.httpOptions
      )
      .pipe((retry(1)), catchError(this.handleError));
  }

  firstAvaiableAId(): Observable<number> {
    return this.http
      .get<number>(
        this.apiURL + '/admin/Articles/getAId',
        this.httpOptions
      )
      .pipe((retry(1), catchError(this.handleError)));
  }

  deletePO(id: number) {
    return this.http
      .delete(
        this.apiURL + '/admin/Purchase_Orders/delete?id=' + id,
        this.httpOptions
      )
      .pipe((retry(1), catchError(this.handleError)));
  }

  deleteA(id: number) {
    return this.http
      .delete(
        this.apiURL + '/admin/Articles/delete/' + id,
        this.httpOptions
      )
      .pipe((retry(1), catchError(this.handleError)));
  }

  // Error handling
  handleError(error: any) {
    let errorMessage = '';
    console.log("err", error.url);
    if (error.error instanceof ErrorEvent) {
      // Get client-side error
      errorMessage = error.error.message;
    }
    else if (HttpStatusCode.Unauthorized) {
      // Get server-side error
      if (error.url == "http://localhost:8080/admin/basicauth") { //Caso in cui le credenziali sono errate
        errorMessage = 'Username o Password errati';
      }
      else { //Caso in cui la sessione scade ed il token non è più valido
        window.location.href = "expired";
      }
    }
    else {
      // Get server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    window.alert(errorMessage);
    return throwError(() => {
      return errorMessage;
    });
  }
}
