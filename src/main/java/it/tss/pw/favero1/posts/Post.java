/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.pw.favero1.posts;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

/**
 *
 * @author oscar.favero
 */
@Entity
@Table(name = "post")
public class Post extends AbstractEntity {
    @NotEmpty
    @Column(name = "title", nullable = false)
    private String title;
    @NotEmpty
    @Column(name = "body", nullable = false)
    private String body;
 //   @ManyToOne(optional = false)
    private String owner;
    @Column(name = "end_date")
    private LocalDate endDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Post{" + "title=" + title + ", body=" + body + ", owner=" + owner + ", endDate=" + endDate + '}';
    }
}
