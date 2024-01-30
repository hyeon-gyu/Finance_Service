package backend.financeService.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
public class Comment {

    @Id @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nickname;
    private String content;
    private String createdDate;
    private String modifiedDate;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @Builder
    public Comment(String nickname, String content, String createdDate, String modifiedDate, Board board) {
        this.nickname = nickname;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.board = board;
    }
}
