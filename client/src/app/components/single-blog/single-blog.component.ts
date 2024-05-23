import { Component, Input } from '@angular/core';
import { Blog } from 'src/app/interfaces/BlogInterface';

@Component({
  selector: 'app-single-blog',
  templateUrl: './single-blog.component.html',
  styleUrls: ['./single-blog.component.scss']
})
export class SingleBlogComponent {

  @Input() blog: Blog | undefined;

}
