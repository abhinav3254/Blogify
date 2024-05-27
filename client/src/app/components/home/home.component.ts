import { Component, OnInit } from '@angular/core';
// import Blogs from '../../../assets/blogs.json'
import { Blog } from 'src/app/interfaces/BlogInterface';
import { BlogService } from '../blog.service';
import { Blogs } from 'src/app/interfaces/GetBlog';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  blogs: Blogs[] = [];

  constructor(private blogService: BlogService) { }

  ngOnInit(): void {
    this.blogService.getAllBlogs().subscribe(
      (res) => {
        this.blogs = res as Blogs[];
        console.log(res);

      }
    );
  }

}
