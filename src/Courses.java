import java.util.*;

public class Courses {
    private final List<Course> courseList;

    public Courses() {
        this.courseList = new ArrayList<>();
    }

    public Courses(Course... courses) {
        this.courseList = new ArrayList<>(List.of(courses));
    }

    public Courses(ArrayList<Course> courses) {
        this.courseList = new ArrayList<>(courses);
    }

    public void addCourse(Course course) {
        this.courseList.add(course);
    }

    public ArrayList<ArrayList<Course>> getAllCombinations(int size) {
        ArrayList<ArrayList<Course>> combinations = new ArrayList<>();
        ArrayDeque<Course> combination = new ArrayDeque<>();
        getAllCombinationsAux(size, 0, combinations, combination);
        System.out.println(combinations.size());
        return combinations;
    }

    // recursive DFS implementation
    private void getAllCombinationsAux(int size, int index, ArrayList<ArrayList<Course>> combinations, ArrayDeque<Course> combination) {
        if (combination.size() == size) {
            combinations.add(new ArrayList<>(combination));
            return;
        }
        for (int i = index; i < courseList.size(); i++) {
            if (!isConflict(combination, courseList.get(i))) {
                combination.addLast(courseList.get(i));
                getAllCombinationsAux(size, i + 1, combinations, combination);
                combination.removeLast();
            }
        }
    }

    private boolean isConflict(Collection<Course> combination, Course course) {
        for (Course c: combination) {
            if (course.isConflict(c)) {
                return true;
            }
        }
        return false;
    }

    public void printCombination(int size) {
        ArrayList<ArrayList<Course>> combinations = getAllCombinations(size);
        int count = 0;
        int totalCredit;
        int totalStars;
        for (var a: combinations) {
            totalCredit = 0;
            totalStars = 0;
            System.out.println("the " + ++count + " combination");
            for (var b: a) {
                totalCredit += b.getCredit();
                totalStars += b.getStars();
                System.out.print(b);
            }
            System.out.println("\ttotal credit: " + totalCredit + ", total stars: " + totalStars);
            System.out.println();
        }
    }
}
