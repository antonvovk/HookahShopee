export class Page<T> {

  items?: T[];
  totalElements?: number;

  constructor(params: Page<T> = {} as Page<T>) {
    const {
      items = [],
      totalElements = 0
    } = params;

    this.items = items;
    this.totalElements = totalElements;
  }
}
