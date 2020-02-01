export class Manufacturer {

  uuid?: string;
  name?: string;
  imageName?: string;

  constructor(params: Manufacturer = {} as Manufacturer) {
    const {
      uuid,
      name,
      imageName
    } = params;

    this.uuid = uuid;
    this.name = name;
    this.imageName = imageName;
  }
}
