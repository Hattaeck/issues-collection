package ru.netology.issues;

import java.util.Set;

public class Issue {
    int id;
    String name;
    boolean status;
    String author;
    String assignee;
    Set<String> tags;

    public Issue(int id, String name, boolean status, String author, String assignee, Set<String> tags) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.author = author;
        this.assignee = assignee;
        this.tags = tags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }
}
