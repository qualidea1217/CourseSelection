import java.util.*;

public class Courses {
    private List<Course> courseList;

    public Courses() {
        this.courseList = new ArrayList<>();
    }

    public Courses(Course... courses) {
        this.courseList = List.of(courses);
    }

    public Courses(ArrayList<Course> courses) {
        this.courseList = new ArrayList<>(courses);
    }

    public void addCourse(Course course) {
        this.courseList.add(course);
    }

    public void removeCourse(Course course) {
        this.courseList.remove(course);
    }

    public ArrayList<ArrayList<Course>> getAllCombinations(int size) {
        ArrayList<ArrayList<Course>> combinations = new ArrayList<>();
        ArrayDeque<Course> combination = new ArrayDeque<>();
        getAllCombinationsAux(size, 0, combinations, combination);
        System.out.println(combinations.size());
        return combinations;
    }

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
}
