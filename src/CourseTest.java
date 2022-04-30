public class CourseTest {
    public static void main(String[] args) {
        boolean[] MW = {false, true, false, true, false, false, false};
        boolean[] TTh = {false, false, true, false, true, false, false};
        boolean[] F = {false, false, false, false, false, true, false};
        Courses courses = new Courses();
        courses.addCourse(new Course("CS 325", "14:30", "15:45", TTh, 3, "A", 5));
        courses.addCourse(new Course("CS 326", "13:00", "14:15", TTh, 3, "F", 5));
        courses.addCourse(new Course("CS 326", "16:00", "17:15", TTh, 3, "F", 5));
        courses.addCourse(new Course("CS 334", "13:00", "14:15", MW, 3, "G", 5));
        courses.addCourse(new Course("CS 350", "08:30", "09:45", MW, 3, "F", 5));
        courses.addCourse(new Course("CS 350", "11:30", "12:45", TTh, 3, "F", 5));
        courses.addCourse(new Course("CS 370", "13:00", "14:15", MW, 3, "F", 5));
        courses.addCourse(new Course("CS 370", "14:30", "15:45", MW, 3, "F", 5));
        courses.addCourse(new Course("CS 377", "08:30", "09:45", MW, 3, "B", 5));
        courses.addCourse(new Course("CS 385", "14:30", "15:45", MW, 3, "C", 5));
        courses.addCourse(new Course("PHYS 220", "11:30", "12:45", TTh, 3, "D", 5));
        courses.addCourse(new Course("PHYS 361", "13:00", "14:15", MW, 3, "E", 5));
        courses.addCourse(new Course("PHYS 380", "16:00", "17:15", TTh, 3, "F", 5));
        System.out.println(courses.getAllCombinations(4));
    }
}
