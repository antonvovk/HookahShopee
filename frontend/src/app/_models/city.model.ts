export class City {

  uuid?: string;
  name?: string;

  constructor(params: City = {} as City) {
    const {
      uuid,
      name
    } = params;

    this.uuid = uuid;
    this.name = name;
  }
}
