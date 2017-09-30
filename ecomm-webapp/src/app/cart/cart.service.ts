
import {Injectable} from '@angular/core';
import {Headers, Http} from '@angular/http';
import {ProductDto} from '../productCatalog/product.dto';
import {Observable} from 'rxjs/Observable';
import {CartItemDto} from './cart.item.dto';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';
import {CartDto} from './cart.dto';

@Injectable()
export class CartService {

  private baseUrl: String = 'http://localhost:58080/cart-service/api/v1/cart/';
  cartItemOb:  BehaviorSubject<CartItemDto[]> = new BehaviorSubject(null);
  constructor(private http: Http) { }

  addToCart(productDTO: ProductDto): Observable<CartDto> {

    return this.http.put(this.baseUrl + '123', JSON.stringify(this.convertProductToCartItem(productDTO)), {headers: this.getHeaders()})
      .map(response => response.json());
  }

  readCart (): Observable<CartDto> {
    return this.http.get(this.baseUrl + '123')
      .map(response => response.json());
  }

  private convertProductToCartItem (productDTO: ProductDto): CartItemDto {
    const cartItem = new CartItemDto();
    cartItem.name = productDTO.name;
    cartItem.currency = productDTO.currency;
    cartItem.price = productDTO.price;
    return cartItem;
  }

  private getHeaders() {
    const headers = new Headers();
    headers.append('Accept', 'application/json');
    headers.append('Content-Type', 'application/json');
    return headers;
  }

}
