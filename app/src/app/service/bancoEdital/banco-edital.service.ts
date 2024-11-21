import { Injectable } from '@angular/core';
import { IService } from '../i-service';
import { BancoEdital } from '../../model/BancoEdital';
import { Observable } from 'rxjs';
import { RequisicaoPaginada } from '../../model/RequisicaoPaginada';
import { RespostaPaginada } from '../../model/RespostaPaginada';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class BancoEditalService implements IService<BancoEdital> {

  constructor(
    private http: HttpClient
  ) { }

  apiUrl: string = environment.API_URL + '/bancoedital/';
  
  get(termoBusca?: string, paginacao?: RequisicaoPaginada): Observable<RespostaPaginada<BancoEdital>> {
    let url = this.apiUrl + "?";
    if (termoBusca) {
      url += "termoBusca=" + termoBusca;
    }
    if (paginacao) {
      url += "&page=" + paginacao.page;
      url += "&size=" + paginacao.size;
      paginacao.sort.forEach(campo => {
        url += "&sort=" + campo;
      });
    } else {
      url += "&unpaged=true";
    }
    return this.http.get<RespostaPaginada<BancoEdital>>(url);
  }
  getById(id: number): Observable<BancoEdital> {
    let url = this.apiUrl + id;
    return this.http.get<BancoEdital>(url);
  }
  save(objeto: BancoEdital): Observable<BancoEdital> {
    let url = this.apiUrl;
    if (objeto.idBancoEdital) {
      return this.http.put<BancoEdital>(url, objeto);
    } else {
      return this.http.post<BancoEdital>(url, objeto);
    }
  }
  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
}