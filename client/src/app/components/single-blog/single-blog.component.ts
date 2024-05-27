import { Component, Input } from '@angular/core';
import { Blogs } from 'src/app/interfaces/GetBlog';

@Component({
  selector: 'app-single-blog',
  templateUrl: './single-blog.component.html',
  styleUrls: ['./single-blog.component.scss']
})
export class SingleBlogComponent {

  @Input() blog: Blogs | undefined;

  renderBase64Image(url: string): string {
    return "data:image/png;base64, " + url;
  }
}
