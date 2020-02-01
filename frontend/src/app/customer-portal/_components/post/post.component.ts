import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Post } from '../../../_models/post.model';
import { PostService } from '../../../_services/post.service';
import { ImageService } from '../../../_services/image.service';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.scss']
})
export class PostComponent implements OnInit {

  post: Post;

  constructor(private activatedRoute: ActivatedRoute,
              private postService: PostService,
              public imageService: ImageService,
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
