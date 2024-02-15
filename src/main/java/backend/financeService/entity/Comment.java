package backend.financeService.entity;

import backend.financeService.common.TimeEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends TimeEntity {

    @Id @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickname;
    private String password;
    private String content;
    private Boolean blind;

    @ManyToOne
    @JoinColumn(name = "board_id")
    @JsonBackReference
    private Board board;

    @Builder
    public Comment(String nickname, String password, String content, Boolean blind, Board board) {
        this.nickname = nickname;
        this.password = password;
        this.content = content;
        this.blind = blind;
        this.board = board;
    }

    public void setBoard(Board board){
        this.board = board;
        if(board != null && !board.getCommentList().contains(this)){
            board.getCommentList().add(this);
        }
    }
}
