import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BlogService {

  constructor(private http: HttpClient) { }

  postBlog(blog: FormData): Observable<any> {
    return this.http.post('blog-service/blogs/create', blog);
  }

  getAllBlogs(): Observable<any> {
    return this.http.get('blog-service/blogs');
  }

}
