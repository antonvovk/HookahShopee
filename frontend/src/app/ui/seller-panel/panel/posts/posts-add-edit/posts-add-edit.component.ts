import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Post } from '../../../../../core/model/post.model';
import { ImageService } from '../../../../../services/image.service';
import { FormControl } from '@angular/forms';
import { PostService } from '../../../../../services/post.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-posts-add-edit',
  templateUrl: './posts-add-edit.component.html',
  styleUrls: ['./posts-add-edit.component.scss']
})
export class PostsAddEditComponent implements OnInit {

  @Input()
  post: Post = null;
  @Output()
  returned = new EventEmitter<boolean>();
  title = new FormControl('');
  htmlContent = new FormControl('');

  constructor(private postService: PostService,
              private snackBar: MatSnackBar,
              public imageService: ImageService) {

  }

  ngOnInit() {
    if (this.post != null) {
      this.title.setValue(this.post.name);
      this.htmlContent.setValue(this.post.htmlContent);
    }
  }

  savePost() {
    if (this.post != null) {
      this.post.name = this.title.value;
      this.post.htmlContent = this.htmlContent.value;
      this.postService.update(this.post).subscribe(
        response => {
          this.snackBar.open(response.statusText, 'Відхилити', {duration: 2000,});
        },
        error => {
          this.snackBar.open(error.apierror.message, 'Відхилити', {duration: 2000,});
        }
      );
    } else {
      this.post = new Post({name: this.title.value, htmlContent: this.htmlContent.value});
      this.postService.create(this.post).subscribe(
        response => {
          this.snackBar.open(response.statusText, 'Відхилити', {duration: 2000,});
        },
        error => {
          this.snackBar.open(error.apierror.message, 'Відхилити', {duration: 2000,});
        }
      );
    }
  }

  handleFileInput(files: any) {
    const uploadedFiles = files.target.files;
    this.postService.updateImage(this.post.uuid, uploadedFiles[0]).subscribe(
      response => {
        this.snackBar.open(response.statusText, 'Відхилити', {duration: 2000});
      },
      error => {
        this.snackBar.open(error.apierror.message, 'Відхилити', {duration: 2000});
      }
    );
  }

  deletePost() {
    this.postService.delete(this.post.uuid).subscribe(
      response => {
        this.snackBar.open(response.statusText, 'Відхилити', {duration: 2000});
        this.returned.emit(true);
      },
      error => {
        this.snackBar.open(error.apierror.message, 'Відхилити', {duration: 2000});
      }
    );
  }
}
