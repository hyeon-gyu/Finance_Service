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
    private String content;

    @ManyToOne
    @JoinColumn(name = "board_id")
    @JsonBackReference
    private Board board;

    @Builder
    public Comment(String content, Board board) {
        this.content = content;
        this.board = board;
    }

    public void setBoard(Board board){
        this.board = board;
        if(board != null && !board.getCommentList().contains(this)){
            board.getCommentList().add(this);
        }
    }
}
