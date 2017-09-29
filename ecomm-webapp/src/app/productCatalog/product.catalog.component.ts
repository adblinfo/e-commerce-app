import {Component, OnInit} from '@angular/core';
import {ProductDto} from './product.dto';
import {ProductCatalogService} from './product.catalog.service';
import {CartService} from '../cart/cart.service';
import {CartItemDto} from '../cart/cart.item.dto';

@Component({
  selector: 'app-product-catalog',
  templateUrl: './product.catalog.component.html'
})
export class ProductCatalogComponent implements OnInit {


  private products: ProductDto[];
  private cartItems: CartItemDto[];
  private count: number;

  constructor(productService: ProductCatalogService, private cartService: CartService) {
    productService.getProductRecomendations()
      .subscribe(
        (products) => this.products = products,
        (err) => console.log(JSON.stringify(err)),
        () => console.log('product recomendation complete')
      );
  }

  ngOnInit() {
    this.cartService.readCart()
      .subscribe(
        (cart) => this.count = cart.items.length,
        (err) => console.log('Error occured while fetching cart.' + err),
        () => console.log('cart fetched successfully.')
      );
  }

  addToCart(productDto: ProductDto) {
    this.cartService.addToCart(productDto)
      .subscribe(
        (cart) => {
          this.cartItems = cart.items;
          console.log(this.cartItems);
          this.count = this.cartItems.length;
          // this.cartService.cartItemOb.next(this.cartItems);
        },
        (err) => console.log('Error'),
        () => console.log('Complete')
      );
  }
}
