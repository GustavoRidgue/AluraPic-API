package com.ridgue.jangular.http.util;

import com.ridgue.jangular.database.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteCommentForm {
    private Long userId;
    private Long photoId;
    private Long photoCommentId;
}
