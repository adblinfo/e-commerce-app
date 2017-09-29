import {CartItemDto} from './cart.item.dto';

export class CartDto {
  id: String;
  items: CartItemDto[];
  total: number;
  currency: String;
}
