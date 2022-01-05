package com.revature.likes;

import com.revature.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "api/like")
public class LikeController {
    private LikeService likeService;

    //constructor
    @Autowired
    public LikeController(LikeService likeService)
    {
        this.likeService = likeService;
    }

    /*  Must be given postID through URL
        Returns the number of likes for identified post
     */
    @GetMapping(path = "/get-number-of-likes/{postId}")
    public ResponseEntity<Integer> getNumberOfLikes(@PathVariable UUID postId)
    {
        System.out.println("POST ID:                               " + postId);
        try {
        	Integer numLikes = likeService.getNumberofLikes(postId);
        	return ResponseEntity.ok(numLikes);
        }
        catch(Exception e)
        {
//            e.printStackTrace();
        }

        return ResponseEntity.internalServerError().build();
    }

    /*  Must be provided postID in URL
        Creates a new like upon identified post.
     */
    @PutMapping(path = "/like-post/{postId}")
    public void likePost(@PathVariable UUID postId, @AuthenticationPrincipal User user)
    {
        try {
            likeService.likePost(postId, user);
            ResponseEntity.ok();
        }
        catch(Exception e)
        {
//            e.printStackTrace();
            ResponseEntity.internalServerError().build();
        }

    }

    /*  Must be provide a postID in the URL
        Returns boolean if logged in user has already liked the post.
     */
    @GetMapping(path = "/check-if-liked/{postId}")
    public ResponseEntity<Boolean> checkIfLiked(@PathVariable UUID postId, @AuthenticationPrincipal User user)
    {
    	try {
    		return ResponseEntity.ok(likeService.checkIfAlreadyLiked(postId, user));
    	} catch (Exception e) {
//    		e.printStackTrace();
    	}
    	return ResponseEntity.internalServerError().build();
    }
}
