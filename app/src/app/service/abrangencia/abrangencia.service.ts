import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IService } from '../i-service';
import { Abrangencia } from '../../model/Abrangencia';
import { Observable } from 'rxjs';
import { RequisicaoPaginada } from '../../model/RequisicaoPaginada';
import { RespostaPaginada } from '../../model/RespostaPaginada';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AbrangenciaService implements IService<Abrangencia> {

  constructor(
    private http: HttpClient
  ) { }

   apiUrl: string = environment.API_URL + '/abrangencias/'
   
  get(termoBusca?: string, paginacao?: RequisicaoPaginada): Observable<RespostaPaginada<Abrangencia>> {
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
    return this.http.get<RespostaPaginada<Abrangencia>>(url);
  }
  getById(id: number): Observable<Abrangencia> {
    let url = this.apiUrl + id;
    return this.http.get<Abrangencia>(url);
  }
  save(objeto: Abrangencia): Observable<Abrangencia> {
    let url = this.apiUrl;
    if (objeto.idAbrangencia) {
      return this.http.put<Abrangencia>(url, objeto);
    } else {
      return this.http.post<Abrangencia>(url, objeto);
    }
  }
  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
}