package com.design.smartparking.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * 公告
 */

@Entity
@Table(name = "notice")
public class Notice {

    @Id
    @Column(name = "id", columnDefinition = "int COMMENT '主键，自动生成'")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", columnDefinition = "text COMMENT '内容'")
    private String content;

    @Column
    private String publishUser;

    @Column
    private String title;

    @Column
    private String keyword;

    @CreationTimestamp
    @Column(name = "create_date", columnDefinition = "datetime COMMENT '创建时间'")
    private Date createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublishUser() {
        return publishUser;
    }

    public void setPublishUser(String publishUser) {
        this.publishUser = publishUser;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
