package ru.netology.issues;

import java.util.ArrayList;
import java.util.List;

public class IssueRepository {
    private List<Issue> issues = new ArrayList<>();

    public void save(Issue issue) {
        issues.add(issue);
    }

    public List<Issue> findAll() {
        return issues;
    }
}