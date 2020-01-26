import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PostService } from '../../../services/post.service';
import { Post } from '../../../core/model/post.model';
import { ImageService } from '../../../services/image.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.scss']
})
export class PostComponent implements OnInit {

  post: Post;

  constructor(private activatedRoute: ActivatedRoute,
              private postService: PostService,
              private imageService: ImageService,
              private toastrService: ToastrService,
              private router: Router) {
  }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(
      params => {
        this.postService.findByUUID(params.get('uuid')).subscribe(
          post => {
            this.post = post;
          },
          error => {
            this.toastrService.error('Не вийшло завантажити пост', 'Помилка');
            this.router.navigate(['']);
          }
        );
      }
    );
  }
}
