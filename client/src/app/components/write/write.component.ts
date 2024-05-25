import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { MessageService } from 'primeng/api';
import { Blog } from 'src/app/interfaces/BlogInterface';
import { BlogService } from '../blog.service';

@Component({
  selector: 'app-write',
  templateUrl: './write.component.html',
  styleUrls: ['./write.component.scss']
})
export class WriteComponent implements OnInit {

  writeForm: FormGroup;
  uploadImage: File | undefined;

  // this variable is for displaying image in the dialog box
  uploadImageUrl: string | undefined;
  showPublishDialog = false;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private location: Location,
    private messageService: MessageService,
    private blogService: BlogService
  ) {
    this.writeForm = this.fb.group({
      blog: ['', Validators.required],
      title: ['', Validators.required],
      tags: [],
      allowComments: [true],
      coverImage: ['', Validators.required]
    });
  }

  ngOnInit() { }

  goBack() {
    this.location.back();
  }

  // onBasicUploadAuto(event: any) {
  //   const input = event.target as HTMLInputElement;
  //   if (input.files && input.files.length > 0) {
  //     const file = input.files[0];
  //     this.uploadImage = URL.createObjectURL(file);
  //   }
  // }

  onBasicUploadAuto(event: any) {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      const file = input.files[0];
      this.uploadImage = file;

      // Read the contents of the file and convert it to a data URL
      const reader = new FileReader();
      reader.onload = (e) => {
        this.uploadImageUrl = e.target?.result as string;
      };
      reader.readAsDataURL(file);
    }
  }

  submitForm() {
    if (this.writeForm.valid) {
      const formData: FormData = new FormData();
      formData.append('title', this.writeForm.value.title);
      formData.append('blog', this.writeForm.value.blog);
      formData.append('coverImage', this.uploadImage as File);
      formData.append('tags', this.writeForm.value.tags);
      formData.append('allowComments', this.writeForm.value.allowComments);

      this.submitBlogToBackend(formData);
    } else {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Please fill out the form correctly.' });
    }
  }

  submitBlogToBackend(blog: FormData) {
    try {
      this.blogService.postBlog(blog).subscribe((res) => {
        console.log(res);
        this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Blog published successfully!' });
      });
    } catch (error) {
      console.error('Error submitting blog:', error);
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Failed to publish blog. Please try again later.' });
    }
  }

}
