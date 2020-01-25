export class Post {

  uuid?: string;
  name?: string;
  imageName?: string;
  htmlContent?: string;

  constructor(params: Post = {} as Post) {
    const {
      uuid,
      name,
      imageName,
      htmlContent
    } = params;

    this.uuid = uuid;
    this.name = name;
    this.imageName = imageName;
    this.htmlContent = htmlContent;
  }
}
