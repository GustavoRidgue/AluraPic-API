package com.ridgue.jangular.http.ws;
import com.ridgue.jangular.database.entity.Photo;
import com.ridgue.jangular.database.entity.PhotoComment;
import com.ridgue.jangular.exception.ResourceNotFoundException;
import com.ridgue.jangular.exception.UnauthorizedException;
import com.ridgue.jangular.http.util.CommentPhotoForm;
import com.ridgue.jangular.http.util.DeleteCommentForm;
import com.ridgue.jangular.http.util.DeletePhotoForm;
import com.ridgue.jangular.http.util.LikePhotoForm;
import com.ridgue.jangular.usecase.photo.PhotoUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/")
@AllArgsConstructor
public class PhotoWS {
    private final PhotoUseCase photoUserCase;

    @GetMapping("photo")
    public ResponseEntity<List<Photo>> getPhotos() {
        return ResponseEntity.ok(photoUserCase.listAll());
    }

    @GetMapping("photo/user/{id}")
    public ResponseEntity<List<Photo>> getPhotosFromUser(@PathVariable(name = "id") Long id) {
        try {
            return ResponseEntity.ok(photoUserCase.listAllFromUser(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("photo/id/{id}")
    public ResponseEntity<Photo> getPhotoById(@PathVariable(name = "id") Long id) {
        try {
            return ResponseEntity.ok(photoUserCase.findById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("photo/description/{description}")
    public ResponseEntity<List<Photo>> getPhotoByDescription(@PathVariable(name = "description") String description) {
        try {
            return ResponseEntity.ok(photoUserCase.findByDescription(description));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("photo/upload")
    public ResponseEntity<?> UploadFile(@RequestBody Photo photo) {
        try {
            return ResponseEntity.ok(photoUserCase.save(photo));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("photo/delete")
    public ResponseEntity<?> deletePhoto(@RequestBody DeletePhotoForm deletePhotoForm) {
        try {
            photoUserCase.deletePhoto(deletePhotoForm);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(403).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("photo/{id}/comments")
    public ResponseEntity<List<PhotoComment>> getPhotoComments(@PathVariable(name = "id") Long id) {
        try {
            return ResponseEntity.ok(photoUserCase.listComments(id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("photo/comment")
    public ResponseEntity<?> commentPhoto(@RequestBody CommentPhotoForm form) {
        try {
            return ResponseEntity.ok(photoUserCase.comment(form));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("photo/comment/delete")
    public ResponseEntity<?> deleteComment(@RequestBody DeleteCommentForm deleteCommentForm) throws Exception {
        try {
            photoUserCase.deleteComment(deleteCommentForm);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("photo/like")
    public ResponseEntity<?> likePhoto(@RequestBody LikePhotoForm likePhotoForm) {
        try {
            photoUserCase.like(likePhotoForm);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("photo/dislike")
    public ResponseEntity<?> dislikeComment(@RequestBody LikePhotoForm dislikePhotoForm) throws Exception {
        try {
            photoUserCase.dislike(dislikePhotoForm);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
