package ru.netology.issues;

import java.util.Comparator;

public class IssueByAuthorComparator implements Comparator<Issue> {

    @Override
    public int compare(Issue o1, Issue o2) {
        return o1.getAuthor().compareTo(o2.getAuthor());
    }
}