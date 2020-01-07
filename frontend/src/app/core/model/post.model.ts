export class Post {

  private name: string;
  private imageName: string;
  private htmlContent: string;

  constructor(name: string, imageName: string, htmlContent: string) {
    this.name = name;
    this.imageName = imageName;
    this.htmlContent = htmlContent;
  }

  get getName(): string {
    return this.name;
  }

  set setName(value: string) {
    this.name = value;
  }

  get getImageName(): string {
    return this.imageName;
  }

  set setImageName(value: string) {
    this.imageName = value;
  }

  get getHtmlContent(): string {
    return this.htmlContent;
  }

  set setHtmlContent(value: string) {
    this.htmlContent = value;
  }
}
