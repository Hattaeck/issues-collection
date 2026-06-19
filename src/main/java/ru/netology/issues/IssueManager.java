package ru.netology.issues;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Comparator;
import java.util.function.Predicate;

public class IssueManager {
    private IssueRepository repository;

    public IssueManager(IssueRepository repository) {
        this.repository = repository;
    }

    public void add(Issue issue) {
        repository.save(issue);
    }

    public List<Issue> filterAndSort(Predicate<Issue> condition, Comparator<Issue> comparator) {
        List<Issue> result = new ArrayList<>();

        for (Issue issue : repository.findAll()) {
            if (condition.test(issue)) {
                result.add(issue);
            }
        }

        result.sort(comparator);

        return result;
    }

    public List<Issue> findOpen() {
        List<Issue> result = new ArrayList<>();
        for (Issue issue : repository.findAll()) {
            if (issue.isStatus()) {
                result.add(issue);
            }
        }
        return result;
    }

    public List<Issue> findClosed() {
        List<Issue> result = new ArrayList<>();
        for (Issue issue : repository.findAll()) {
            if (!issue.isStatus()) {
                result.add(issue);
            }
        }
        return result;
    }

    public List<Issue> filterByAuthor(String author) {
        List<Issue> result = new ArrayList<>();
        for (Issue issue : repository.findAll()) {
            if (issue.getAuthor().equals(author)) {
                result.add(issue);
            }
        }
        return result;
    }

    public List<Issue> filterByAssignee(String assignee) {
        List<Issue> result = new ArrayList<>();
        for (Issue issue : repository.findAll()) {
            if (issue.getAssignee().equals(assignee)) {
                result.add(issue);
            }
        }
        return result;
    }

    public List<Issue> filterByLabels(Set<String> labels) {
        List<Issue> result = new ArrayList<>();
        for (Issue issue : repository.findAll()) {
            if (issue.getTags().containsAll(labels)) {
                result.add(issue);
            }
        }
        return result;
    }

    public void changeStatusById(int id, boolean newStatus) {
        for (Issue issue : repository.findAll()) {
            if (issue.getId() == id) {
                issue.setStatus(newStatus);
                break;
            }
        }
    }
}

