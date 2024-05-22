import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { DropdownChangeEvent } from 'primeng/dropdown';
import { AuthService } from './auth.service';
import { HttpErrorResponse } from 'src/app/API-Response-Interface';
import { MessageService } from 'primeng/api';
import { Router } from '@angular/router';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.scss']
})
export class AuthComponent {

  constructor(private auth: AuthService, private messageService: MessageService, private router: Router) { }

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


  loginResponse: JwtMessage | undefined;
  loginSubmit() {
    this.auth.login(this.loginForm.value).subscribe(
      (response) => {
        this.loginResponse = response as JwtMessage;
        localStorage.setItem('token', this.loginResponse.response);
        this.showSuccess();
        this.router.navigate(['home']);
      },
      (error: HttpErrorResponse) => {
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

interface JwtMessage {
  response: string
  date: string
}