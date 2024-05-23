import { Component } from '@angular/core';
import Blogs from '../../../assets/blogs.json'
import { Blog } from 'src/app/interfaces/BlogInterface';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {

  blogs: Blog[] = Blogs;

}
