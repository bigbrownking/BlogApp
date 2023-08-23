package com.example.blog.controller;

import com.example.blog.entity.Post;
import com.example.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class BlogController {
    private final PostService postService;

    @Autowired
    public BlogController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/secured")
    public String secured(){
        return "hello secured";
    }
    @GetMapping("/posts")
    public List<Post> posts(){
        return postService.getAllPost();
    }
    @PostMapping("/post")
    public void publishPost(@RequestBody Post post){
        if(post.getDateCreated() == null) post.setDateCreated(new Date());
        postService.insert(post);
    }


}
