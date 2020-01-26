export class ProductListRequest {

  startPrice?: number;
  endPrice?: number;
  manufacturers?: string[];
  cityUUID?: string;
  isOnDiscount?: string;

  constructor(params: ProductListRequest = {} as ProductListRequest) {
    const {
      startPrice,
      endPrice,
      manufacturers,
      cityUUID,
      isOnDiscount
    } = params;

    this.startPrice = startPrice;
    this.endPrice = endPrice;
    this.manufacturers = manufacturers;
    this.cityUUID = cityUUID;
    this.isOnDiscount = isOnDiscount;
  }
}
