package com.revature.comments;

import com.revature.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "api/comment")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService)
    {
        this.commentService = commentService;
    }

    /*
     * Get all of the comments present in the database.
     * no parameters
     * returns List<Comment> */
    @GetMapping(path = "/get-all-comments")
    public ResponseEntity<List<Comment>> getComments()
    {
        return ResponseEntity.ok(commentService.getComments());
    }

    /*
     * Submit a comment to the database.
     * parameters: JSON Comment, Long postId through path variable
     * returns Comment */


    @PostMapping(path = "/submit/{postId}")
    public ResponseEntity<Comment> submitComment(@RequestBody Comment comment, @PathVariable UUID postId, User user)
    {
    	try
        {
            return ResponseEntity.ok(commentService.addNewComment(comment, postId, user));
        }
        catch(Exception e)
        {
//            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }



}
