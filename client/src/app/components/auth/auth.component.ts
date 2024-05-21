import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { DropdownChangeEvent } from 'primeng/dropdown';
import { AuthService } from './auth.service';
import { HttpErrorResponse } from 'src/app/API-Response-Interface';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.scss']
})
export class AuthComponent {

  constructor(private auth: AuthService, private messageService: MessageService) { }

  isLoginPage: boolean = true;

  genders: Gender[] = [{ name: "Male" }, { name: "Female" }];

  loginForm = new FormGroup({
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required])
  });


  signupForm = new FormGroup({
    name: new FormControl('', [Validators.required]),
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
    mobile: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required]),
    gender: new FormGroup({
      selectedGender: new FormControl('', [Validators.required])
    })
  });


  loginSubmit() {
    this.auth.login(this.loginForm.value).subscribe(
      (response) => {
        console.log(response);
        this.showSuccess();
      },
      (error: HttpErrorResponse) => {
        console.error("Login failed:", error.status === 404)

        if (error.status == 404) {
          this.showUserNotFound();
        } else if (error.status == 400) {
          this.showUserWrongPassword();
        } else {
          this.showInternalError();
        }
      }
    );
  }


  // success toast
  showSuccess() {
    this.messageService.add({ severity: 'success', summary: 'Login Successful', detail: 'Welcome back!' });
  }

  showUserNotFound() {
    this.messageService.add({ severity: 'error', summary: 'Login Failed', detail: 'User not found. Please check your username and try again.' });
  }

  showUserWrongPassword() {
    this.messageService.add({ severity: 'error', summary: 'Login Failed', detail: 'Incorrect password. Please try again.' });
  }

  showInternalError() {
    this.messageService.add({ severity: 'info', summary: 'Info', detail: 'Oops! Something went wrong try later' });
  }

}

interface Gender {
  name: string
}