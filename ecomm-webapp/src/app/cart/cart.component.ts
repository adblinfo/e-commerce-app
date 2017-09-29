import {CartItemDto} from './cart.item.dto';
import {CartService} from './cart.service';
import {Component} from '@angular/core';
import {CartDto} from './cart.dto';

@Component({
  selector: 'app-cart',
  templateUrl:  './cart.component.html'
})
export class CartComponent {
  private cart: CartDto;
  constructor (private cartService: CartService) {
    // cartService.cartItemOb.subscribe((cartItems) => console.log(cartItems));
    cartService.readCart()
      .subscribe(
        (cart) => this.cart = cart,
        (err) => console.log('Error occured while fetching cart.' + err),
        () => console.log('cart fetched successfully.')
      );
  }


}
