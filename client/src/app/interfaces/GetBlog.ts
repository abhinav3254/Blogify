export interface Blogs {
    id: number
    title: string
    content: string
    coverImage: string
    postedDate: string
    readTime: number
    likes: number
    user: User
    comments: Comment[]
    tags: string[]
}

export interface User {
    username: string
    profilePic: string
}

export interface Comment {
    id: number
    comment: string
    commentDate: string
    username: string
    like: number
}
