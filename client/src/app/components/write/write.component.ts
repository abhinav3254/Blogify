import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Location } from '@angular/common'
import { MessageService } from 'primeng/api';

interface UploadEvent {
  originalEvent: Event;
  files: File[];
}

@Component({
  selector: 'app-write',
  templateUrl: './write.component.html',
  styleUrls: ['./write.component.scss']
})
export class WriteComponent {

  constructor(private router: Router, private location: Location, private messageService: MessageService) { }

  text: string | undefined;
  showPublishDialog = true;
  uploadImage: string | undefined;
  maxTags: number = 5;
  allowComments: boolean = true;
  tags: any;
  blogTitle: string = '';

  goBack() {
    // go back to route from where I came doesn't matter i.e. is home or profile
    this.location.back();
  }

  onBasicUploadAuto(event: any) {
    const input = event.target as HTMLInputElement;
    console.log(input);

    if (input.files && input.files.length > 0) {
      const file = input.files[0];
      this.uploadImage = URL.createObjectURL(file);
      // console.log(file);
    }
  }

  submitForm() {
    const blogPost = {
      text: this.text,
      image: this.uploadImage,
      tags: this.tags,
      title: this.blogTitle,
      allowComments: this.allowComments
    };

    console.log('Blog Post Data:', blogPost);
    this.messageService.add({ severity: 'success', summary: 'Form Submitted', detail: 'Your blog post has been submitted successfully.' });
  }

}
