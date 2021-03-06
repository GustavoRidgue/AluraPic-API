package com.ridgue.jangular.usecase.photo;

import com.ridgue.jangular.database.entity.Photo;
import com.ridgue.jangular.database.entity.PhotoComment;
import com.ridgue.jangular.database.entity.User;
import com.ridgue.jangular.database.repository.PhotoCommentRepository;
import com.ridgue.jangular.database.repository.PhotoRepository;
import com.ridgue.jangular.database.repository.UserRepository;
import com.ridgue.jangular.exception.ResourceNotFoundException;
import com.ridgue.jangular.exception.UnauthorizedException;
import com.ridgue.jangular.http.util.CommentPhotoForm;
import com.ridgue.jangular.http.util.DeleteCommentForm;
import com.ridgue.jangular.http.util.DeletePhotoForm;
import com.ridgue.jangular.http.util.LikePhotoForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
        photoRepository.save(photo);
        return photo;
    }

    public List<PhotoComment> listComments(long photoId) {
        Photo photo = photoRepository.findById(photoId).orElse(null);

        if (photo == null) throw new ResourceNotFoundException();

        return photo.getComments();
    }

    public void deletePhoto(DeletePhotoForm deletePhotoForm) {
        Photo photo =
                photoRepository.findById(deletePhotoForm.getPhotoId()).orElse(null);

        User user =
                userRepository.findById(deletePhotoForm.getUser().getId()).orElse(null);

        if (photo == null || user == null) throw new ResourceNotFoundException();
        if (photo.getUser().getId() != user.getId() ||
            photo.getUser().getId() != deletePhotoForm.getUser().getId()) throw new UnauthorizedException();



        photoRepository.delete(photo);
    }

    public PhotoComment comment(CommentPhotoForm form) {
        Photo photo = photoRepository.findById(form.getPhotoId()).orElse(null);
        User user = userRepository.findById(form.getUser().getId()).orElse(null);

        if (photo == null || user == null) {
            throw new ResourceNotFoundException();
        }

        PhotoComment comment = new PhotoComment(form.getComment(), user, photo);
        comment.setUsername(comment.getUser().getUsername());
        photo.addComment(comment);

        photoCommentRepository.save(comment);

        return comment;
    }

    public void deleteComment(DeleteCommentForm deleteCommentForm) throws Exception {
        Photo photo =
                photoRepository.findById(deleteCommentForm.getPhotoId()).orElse(null);

        PhotoComment comment =
                photoCommentRepository.findById(deleteCommentForm.getPhotoCommentId()).orElse(null);

        if (photo == null || comment == null) throw new ResourceNotFoundException();
        if (comment.getPhoto().getId() != photo.getId() ||
                comment.getUser().getId() != deleteCommentForm.getUserId()) throw new IllegalArgumentException();

        photo.setNumberOfComments(photo.getNumberOfComments() - 1);

        List<PhotoComment> newComments = photo.getComments();
        newComments.remove(comment);

        photo.setComments(newComments);

        photoCommentRepository.deleteById(comment.getId());
        photoRepository.save(photo);
    }

    public void like(LikePhotoForm form) {
        Photo photo = photoRepository.findById(form.getPhotoId()).orElse(null);
        User user = userRepository.findById(form.getUserId()).orElse(null);

        if (photo == null || user == null) throw new ResourceNotFoundException();

        if (photo.getUsersIdLiked().contains(form.getUserId()) ||
                user.getLikedPhotosId().contains(form.getPhotoId())) throw new IllegalArgumentException();

        photo.like(form.getUserId());
        user.like(form.getPhotoId());

        photoRepository.save(photo);
        userRepository.save(user);
    }

    public void dislike(LikePhotoForm form) {
        Photo photo = photoRepository.findById(form.getPhotoId()).orElse(null);
        User user = userRepository.findById(form.getUserId()).orElse(null);

        if (photo == null || user == null) throw new ResourceNotFoundException();

        if (!photo.getUsersIdLiked().contains(form.getUserId()) ||
                !user.getLikedPhotosId().contains(form.getPhotoId())) throw new IllegalArgumentException();

        photo.dislike(form.getUserId());
        user.dislike(form.getPhotoId());

        photoRepository.save(photo);
        userRepository.save(user);
    }
}
