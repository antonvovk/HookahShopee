import { Component, OnInit } from '@angular/core';
import { PostService } from '../../../../services/post.service';
import { Post } from '../../../../core/model/post.model';
import { ImageService } from '../../../../services/image.service';

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.scss']
})
export class PostsComponent implements OnInit {

  posts: Post[] = [];
  selectedPost: Post = null;
  add_editComponentOpened: boolean = false;

  constructor(private postService: PostService, public imageService: ImageService) {

  }

  ngOnInit() {
    this.postService.getAll().subscribe(
      posts => {
        this.posts = posts;
      }
    );
  }

  add_editComponentOpen(post: Post) {
    this.selectedPost = post;
    this.add_editComponentOpened = true;
  }

  onReturn() {
    this.postService.getAll().subscribe(
      posts => {
        this.posts = posts;
        this.add_editComponentOpened = false;
      }
    );
  }
}
