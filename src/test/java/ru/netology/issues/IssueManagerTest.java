package ru.netology.issues;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class IssueManagerTest {
    private static IssueRepository repository = new IssueRepository();
    private static IssueManager manager = new IssueManager(repository);

    private static Issue issue1;
    private static Issue issue2;
    private static Issue issue3;

    @BeforeAll
    public static void setUp() {
        Set<String> tags1 = new HashSet<>();
        tags1.add("component: Jupiter");
        tags1.add("type: bug");

        Set<String> tags2 = new HashSet<>();
        tags2.add("theme: build");

        issue1 = new Issue(1, "Upgrade versions of 3rd-party libraries", true, "sormuras", "mielecmichal", tags1);
        issue2 = new Issue(2, "Missing test launcher?", false, "ramang22", "sormuras", tags2);
        issue3 = new Issue(3, "Multiple declarations of @TempDir", true, "marcphilipp", "mielecmichal", tags1);

        manager.add(issue1);
        manager.add(issue2);
        manager.add(issue3);
    }

    @Test
    public void shouldFilterWithPredicateAndSortWithComparator() {

        List<Issue> actual = manager.filterAndSort(
                issue -> issue.isStatus(),
                Comparator.comparing(Issue::getAuthor)
        );

        List<Issue> expected = List.of(issue3, issue1);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindOpenIssues() {
        List<Issue> expected = List.of(issue1, issue3);
        List<Issue> actual = manager.findOpen();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindClosedIssues() {
        List<Issue> expected = List.of(issue2);
        List<Issue> actual = manager.findClosed();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldFilterByAuthor() {
        List<Issue> expected = List.of(issue1);
        List<Issue> actual = manager.filterByAuthor("sormuras");

        assertEquals(expected, actual);
    }

    @Test
    public void shouldFilterByAssignee() {
        List<Issue> expected = List.of(issue1, issue3);
        List<Issue> actual = manager.filterByAssignee("mielecmichal");

        assertEquals(expected, actual);
    }

    @Test
    public void shouldFilterByLabels() {
        Set<String> searchTags = Set.of("component: Jupiter", "type: bug");

        List<Issue> expected = List.of(issue1, issue3);
        List<Issue> actual = manager.filterByLabels(searchTags);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldChangeStatusById() {
        manager.changeStatusById(1, false);

        List<Issue> expectedOpen = List.of(issue3);
        assertEquals(expectedOpen, manager.findOpen());

        List<Issue> actualClosed = manager.findClosed();
        assertTrue(actualClosed.contains(issue1));

        manager.changeStatusById(1, true);
    }
}