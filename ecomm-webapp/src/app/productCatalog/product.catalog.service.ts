import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import {ProductDto} from './product.dto';

@Injectable()
export class ProductCatalogService {

  private baseUrl: String = 'http://localhost:38080/product-api/api/v1/products/';
  constructor(private http: Http) { }

  getProductRecomendations (): Observable<ProductDto[]> {
    return this.http.get(this.baseUrl + 'recomendations')
      .map(response => response.json());
  }
}
