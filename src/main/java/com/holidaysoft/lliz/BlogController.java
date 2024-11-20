package com.holidaysoft.lliz;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blogs")
@CrossOrigin(origins = "http://localhost:5173")
public class BlogController {
    private final BlogRepository blogRepository;

    public BlogController(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @PostMapping
    ResponseEntity<Blog> createBlog(@RequestBody Blog blog) {
        Blog savedBlog = blogRepository.save(blog);
        return ResponseEntity.ok(savedBlog);
    }

    @GetMapping
    ResponseEntity<List<Blog>> getBlogs(){
        List<Blog> blogs = blogRepository.findAll();
        return ResponseEntity.ok(blogs);
    }

    @GetMapping("/{id}")
    ResponseEntity<Blog> getBlogById(@PathVariable Long id) {
        Blog blog = blogRepository.findById(id).orElse(null);
        if (blog == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(blog);
    }
}
