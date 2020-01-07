import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Post} from "../../../../../core/model/post.model";
import {ImageService} from "../../../../../services/image.service";
import {FormControl} from "@angular/forms";
import {PostService} from "../../../../../services/post.service";
import {MatSnackBar} from "@angular/material/snack-bar";

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
              private imageService: ImageService) {

  }

  ngOnInit() {
    if (this.post != null) {
      this.title.setValue(this.post.getName);
      this.htmlContent.setValue(this.post.getHtmlContent);
    }
  }

  savePost() {
    if (this.post != null) {
      const oldName = this.post.getName;
      this.post.setName = this.title.value;
      this.post.setHtmlContent = this.htmlContent.value;
      this.postService.update(oldName, this.post).subscribe(
        response => {
          this.snackBar.open(response.statusText, 'Відхилити', {duration: 2000,});
        },
        error => {
          this.snackBar.open(error.apierror.message, 'Відхилити', {duration: 2000,});
        }
      );
    } else {
      this.post = new Post(this.title.value, null, this.htmlContent.value);
      this.postService.insert(this.post).subscribe(
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
    this.postService.updateImage(this.post.getName, uploadedFiles[0]).subscribe(
      response => {
        this.snackBar.open(response.statusText, 'Відхилити', {duration: 2000,});
      },
      error => {
        this.snackBar.open(error.apierror.message, 'Відхилити', {duration: 2000,});
      }
    );
  }

  deletePost() {
    this.postService.delete(this.post.getName).subscribe(
      response => {
        this.snackBar.open(response.statusText, 'Відхилити', {duration: 2000,});
        this.returned.emit(true);
      },
      error => {
        this.snackBar.open(error.apierror.message, 'Відхилити', {duration: 2000,});
      }
    );
  }
}
