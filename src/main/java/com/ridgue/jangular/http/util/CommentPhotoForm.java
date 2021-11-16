package com.ridgue.jangular.http.util;

import com.ridgue.jangular.database.entity.User;
import lombok.*;

//@Data
@Getter
@Setter
//@AllArgsConstructor
//@NoArgsConstructor
public class CommentPhotoForm {
    private String comment;
    private Long photoId;
    private User user;
}
