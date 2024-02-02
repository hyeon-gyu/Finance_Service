package backend.financeService.entity;


import backend.financeService.common.TimeEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

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


    @JsonManagedReference
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Comment> commentList;


    @Builder
    public Board(String title, String nickname, String content, String pwd, int commentCnt) {
        this.title = title;
        this.nickname = nickname;
        this.content = content;
        this.pwd = pwd;
        this.commentCnt = commentCnt;
        this.commentList = new ArrayList<>();
    }


    // 양방향 연관관계 편의 메소드
    public void addComment(Comment comment){
        commentList.add(comment);
        comment.setBoard(this);
    }

    public void updateBoard(String title, String content){
        this.title = title;
        this.content = content;
    }
}
