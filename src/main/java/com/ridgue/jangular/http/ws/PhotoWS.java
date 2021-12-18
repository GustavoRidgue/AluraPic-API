package com.ridgue.jangular.http.ws;
import com.ridgue.jangular.database.entity.Photo;
import com.ridgue.jangular.database.entity.PhotoComment;
import com.ridgue.jangular.exception.ResourceNotFoundException;
import com.ridgue.jangular.exception.UnauthorizedException;
import com.ridgue.jangular.http.util.CommentPhotoForm;
import com.ridgue.jangular.http.util.DeletePhotoForm;
import com.ridgue.jangular.usecase.PhotoUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

    @GetMapping("photo/{id}/comments")
    public ResponseEntity<List<PhotoComment>> getPhotos(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(photoUserCase.listComments(id));
    }

    @PostMapping("photo/comment")
    public ResponseEntity<?> commentPhoto(@RequestBody CommentPhotoForm form) {
        try {
            return ResponseEntity.ok(photoUserCase.comment(form));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("photo/delete/{id}")
    public ResponseEntity<?> commentPhoto(@PathVariable(name = "id") Long id) {
        try {
            photoUserCase.deletePhoto(id);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(403).build();
        }
    }
}
