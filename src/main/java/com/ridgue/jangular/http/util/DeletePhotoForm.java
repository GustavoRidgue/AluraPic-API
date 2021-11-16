package com.ridgue.jangular.http.util;

import com.ridgue.jangular.database.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeletePhotoForm {
    private User user;
    private Long photoId;
}
