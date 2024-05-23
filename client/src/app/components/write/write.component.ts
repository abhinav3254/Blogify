import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-write',
  templateUrl: './write.component.html',
  styleUrls: ['./write.component.scss']
})
export class WriteComponent implements OnInit {

  writeForm: FormGroup;
  uploadImage: string | undefined;
  showPublishDialog = false;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private location: Location,
    private messageService: MessageService
  ) {
    this.writeForm = this.fb.group({
      text: ['', Validators.required],
      blogTitle: ['', Validators.required],
      tags: [[]],
      allowComments: [true]
    });
  }

  ngOnInit() { }

  goBack() {
    this.location.back();
  }

  onBasicUploadAuto(event: any) {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      const file = input.files[0];
      this.uploadImage = URL.createObjectURL(file);
    }
  }

  submitForm() {
    if (this.writeForm.valid) {
      const formData = {
        ...this.writeForm.value,
        uploadImage: this.uploadImage
      };

      console.log('Form Data:', formData);
      this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Blog published successfully!' });

    } else {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Please fill out the form correctly.' });
    }
  }
}
