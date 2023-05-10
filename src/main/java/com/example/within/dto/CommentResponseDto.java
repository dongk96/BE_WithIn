package com.example.within.dto;

import com.example.within.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CommentResponseDto {
    private Long commentId;
    private String comment;
    private String username;
    private LocalDateTime createdAt;
    private Long likeCnt;
    private Long sadCnt;
    private Long congratulationCnt;
    private boolean likeCheck = false;
    private boolean sadCheck = false;
    private boolean congratulationCheck = false;


    public CommentResponseDto(Comment comment){
        this.commentId = comment.getId();
        this.comment = comment.getComment();
        this.username = comment.getUser().getUsername();
        this.createdAt = comment.getCreatedAt();
        this.likeCnt = comment.getLikeCnt();
        this.sadCnt = comment.getSadCnt();
        this.congratulationCnt = comment.getCongratulationCnt();
    }
}
