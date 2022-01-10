package com.ridgue.jangular.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private boolean allowComments;

    @Lob @Basic(fetch=FetchType.LAZY)
    @Column(nullable = false)
//    @JsonIgnore
    private String url;

    @JsonIgnore
    @OneToMany(
            mappedBy = "photo",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<PhotoComment> comments;

    private Long numberOfComments;

    private Long numberOfLikes;

    @ElementCollection(targetClass=Long.class)
    private List<Long> usersIdLiked;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime created;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void addComment(PhotoComment comment) {
        comments.add(comment);
        comment.setPhoto(this);

        numberOfComments = Long.parseLong(String.valueOf(comments.size()));
    }

    public void like(Long userId) {
        usersIdLiked.add(userId);
        numberOfLikes = Long.parseLong(String.valueOf(usersIdLiked.size()));
    }

    public void dislike(Long userId) {
        usersIdLiked.remove(userId);
        numberOfLikes = Long.parseLong(String.valueOf(usersIdLiked.size()));
    }

    public Photo(String description, boolean allowComments, String url, User user) {
        this.description = description;
        this.allowComments = allowComments;
        this.url = url;
        this.user = user;
    }

    public Photo(String description, String url) {
        this.description = description;
        this.url = url;
    }

//    public Photo(String description, String url, PhotoComment photoComment) {
//        this.setComments(Collections.singletonList(photoComment));
//        this.description = description;
//        this.url = url;
//    }
}
