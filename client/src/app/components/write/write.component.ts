import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Location } from '@angular/common'

@Component({
  selector: 'app-write',
  templateUrl: './write.component.html',
  styleUrls: ['./write.component.scss']
})
export class WriteComponent {

  text: string | undefined;


  constructor(private router: Router, private location: Location) { }

  goBack() {
    // go back to route from where I came doesn't matter i.e. is home or profile
    this.location.back();
  }

}
