package com.example.within.dto;

@Getter
@Setter
public class BoardResponseDto {
    private Long boardId;
    private String title;
    private String contents;
    private String image;
    private LocalDateTime createdTime;
    private String category;
    private Long likeCnt;
    private Long sadCnt;
    private Long congratulationCnt;
    private boolean likeCheck = false;
    private boolean sadCheck = false;
    private boolean congratulationCheck = false;
    @OrderBy("createdAt DESC")
    private List<CommentResponseDto> commentResponseDtoList;

    public BoardResponseDto(Board board, List<CommentResponseDto> commentResponseDtoList){
        this.boardId = board.getId();
        this.title = board.getTitle();
        this.contents =  board.getContents();
        this.image = board.getImage();
        this.createdTime = board.getCreatedAt();
        this.category = board.getCategory();
        this.likeCnt = board.getLikeCnt();
        this.sadCnt = board.getSadCnt();
        this.congratulationCnt = board.getCongratulationCnt();
        this.commentResponseDtoList = board.getCommentList()
                .stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());
    }
}
