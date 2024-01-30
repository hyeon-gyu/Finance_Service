package backend.financeService.entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class Board {

    @Id @Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String nickname;
    private String content;
    private String pwd; // 게시글 작성시 비밀번호 입력 (6자리 이상, 문자 하나 필수!)
    private String createDate;
    private String modifiedDate;
    private int commentCnt; //댓글 갯수 수집

    @Builder
    public Board(String title, String nickname, String content, String pwd, String createDate, String modifiedDate, int commentCnt) {
        this.title = title;
        this.nickname = nickname;
        this.content = content;
        this.pwd = pwd;
        this.createDate = createDate;
        this.modifiedDate = modifiedDate;
        this.commentCnt = commentCnt;
    }
}
