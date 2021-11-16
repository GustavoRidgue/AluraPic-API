package com.ridgue.jangular.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ridgue.jangular.database.entity.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhotoComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String comment;
    private LocalDateTime posted = LocalDateTime.now();
    private String username;
    @OneToOne
    private User user;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "photo_id", nullable = false)
    private Photo photo;

    public PhotoComment(String comment, User user, Photo photo) {
        this.comment = comment;
        this.user = user;
        this.photo = photo;
    }
}
