package com.ridgue.jangular.database.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String email;

    @ElementCollection(targetClass=Long.class)
    private List<Long> likedPhotosId;

    public void like(Long photoId) {
        likedPhotosId.add(photoId);
    }

    public void dislike(Long photoId) {
        likedPhotosId.remove(photoId);
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
