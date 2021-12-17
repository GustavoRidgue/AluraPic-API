package com.ridgue.jangular.usecase;

import com.ridgue.jangular.database.entity.Photo;
import com.ridgue.jangular.database.entity.User;
import com.ridgue.jangular.database.entity.PhotoComment;
import com.ridgue.jangular.database.repository.PhotoCommentRepository;
import com.ridgue.jangular.database.repository.PhotoRepository;
import com.ridgue.jangular.database.repository.UserRepository;
import com.ridgue.jangular.exception.ResourceNotFoundException;
import com.ridgue.jangular.exception.UnauthorizedException;
import com.ridgue.jangular.http.util.CommentPhotoForm;
import com.ridgue.jangular.http.util.DeletePhotoForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PhotoUseCase {
    private final PhotoRepository photoRepository;
    private final UserRepository userRepository;
    private final PhotoCommentRepository photoCommentRepository;

    public List<Photo> listAll() {
        return photoRepository.findAll();
    }

    public Photo findById(long id) {
        Photo photo = photoRepository.findById(id).orElse(null);
        if (photo == null) throw new ResourceNotFoundException();

//        if (!photo.getComments().isEmpty()) System.out.println("has comments: " + photo.getComments());
//        if (photo.getComments().isEmpty()) System.out.println("hasn't comments");
        return photo;
    }

    public List<Photo> findByDescription(String description) {
        if (photoRepository.findByDescription(description) == null) throw new ResourceNotFoundException();
        return photoRepository.findByDescription(description);
    }

    public List<Photo> listAllFromUser(Long id) {
        return photoRepository.findByUserId(id);
    }

    public Photo save(Photo photo) {
//        User user = new User("jfknrfkr", "nfdfjekf", "nckjrsniu@Nujfrnk");
//        photo.setUser(user);
        photoRepository.save(photo);
        return photo;
    }

    public List<PhotoComment> listComments(long photoId) {
        Photo photo = photoRepository.findById(photoId).orElse(null);

        if (photo == null) throw new ResourceNotFoundException();

        return photo.getComments();
    }

    public PhotoComment comment(CommentPhotoForm form) {
        Photo photo = photoRepository.findById(form.getPhotoId()).orElse(null);
        User user = userRepository.findById(form.getUser().getId()).orElse(null);

        if (photo == null || user == null) {
            throw new ResourceNotFoundException();
        }

        PhotoComment comment = new PhotoComment(form.getComment(), user, photo);
        comment.setUsername(comment.getUser().getUsername());
        photoCommentRepository.save(comment);

        photo.setNumberOfComments(photo.getNumberOfComments() + 1);
        photo.addComment(comment);

        photoRepository.save(photo);

        return comment;
    }

    public void deletePhoto(Long id) {
//        Photo photo = photoRepository.findById(deletePhotoForm.getPhotoId()).orElse(null);

        if (photoRepository.findById(id).orElse(null) == null)
            throw new ResourceNotFoundException();
//        if (photo.getUser().getId() != deletePhotoForm.getUser().getId()) throw new UnauthorizedException();

        photoRepository.deleteById(id);
    }
}
