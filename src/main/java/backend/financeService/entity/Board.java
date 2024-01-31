package backend.financeService.entity;


import backend.financeService.common.TimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Entity
@Getter
@NoArgsConstructor
public class Board extends TimeEntity {

    @Id @Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String nickname;
    private String content;
    private String pwd; // 게시글 작성시 비밀번호 입력 (6자리 이상, 문자 하나 필수!)
    private int commentCnt; //댓글 갯수 수집

    @Builder
    public Board(String title, String nickname, String content, String pwd, int commentCnt) {
        this.title = title;
        this.nickname = nickname;
        this.content = content;
        this.pwd = pwd;
        this.commentCnt = commentCnt;
    }

    public void updateBoard(String title, String content){
        this.title = title;
        this.content = content;
    }
}
