import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { DropdownChangeEvent } from 'primeng/dropdown';
import { AuthService } from './auth.service';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.scss']
})
export class AuthComponent {

  constructor(private auth: AuthService) { }

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
    this.auth.login(this.loginForm.value).subscribe((response) => {
      console.log(response);
    })
  }

}

interface Gender {
  name: string
}