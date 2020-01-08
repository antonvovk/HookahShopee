export class Page<T> {

  items: T[] = [];
  totalElements: number = 0;

  constructor(items?: T[], totalElements?: number) {
    this.items = items;
    this.totalElements = totalElements;
  }
}
