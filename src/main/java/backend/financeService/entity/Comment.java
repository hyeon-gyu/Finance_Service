package backend.financeService.entity;

import backend.financeService.common.TimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
public class Comment extends TimeEntity {

    @Id @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;
    private String content;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @Builder
    public Comment(String nickname, String content, Board board) {
        this.nickname = nickname;
        this.content = content;
        this.board = board;
    }
}
