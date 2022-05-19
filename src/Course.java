import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Course {
    private final String courseCode;
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final boolean[] days; // boolean array with size = 7, days[0] is Sunday and days[6] is Saturday
    private final List<Course> labs; // lab sections, with same course code but probably different days and time, can also be used for other different time
    private final int credit;
    private final String instructor;
    private final int stars; // self evaluation (like) about this course, 0-5

    public Course(String courseCode, LocalTime startTime, LocalTime endTime, boolean[] days, List<Course> labs, int credit, String instructor, int stars) {
        this.courseCode = courseCode;
        this.startTime = startTime;
        this.endTime = endTime;
        this.days = days;
        this.labs = labs;
        this.credit = credit;
        this.instructor = instructor;
        this.stars = stars;
    }

    public Course(String courseCode, String startTime, String endTime, boolean[] days, List<Course> labs, int credit, String instructor, int stars) {
        this(courseCode, LocalTime.parse(startTime), LocalTime.parse(endTime), days, labs, credit, instructor, stars);
    }

    public Course(String courseCode, String startTime, String endTime, boolean[] days, int credit, String instructor, int stars) {
        this(courseCode, LocalTime.parse(startTime), LocalTime.parse(endTime), days, new ArrayList<>(), credit, instructor, stars);
    }

    public void addLab(String startTime, String endTime, boolean[] days, String instructor) throws Exception {
        Course lab = new Course(this.courseCode, startTime, endTime, days, 0, instructor, this.stars);
        if (this.isTimeAndDayConflict(lab)) {
            throw new Exception("lab section is conflicted with main section");
        } else {
            this.labs.add(lab);
        }
    }

    public void addLab(String startTime, String endTime, boolean[] days) throws Exception {
        addLab(startTime, endTime, days, this.instructor);
    }

    // time will be considered as conflicted if start time and end time equals to each other
    private boolean isTimeConflict(Course other) {
        return !(this.startTime.isAfter(other.endTime) || this.endTime.isBefore(other.startTime));
    }

    private boolean isDayConflict(Course other) {
        for (int i = 0; i < 7; i++) {
            if (this.days[i] && other.days[i]) {
                return true;
            }
        }
        return false;
    }

    private boolean isTimeAndDayConflict(Course other) {
        return isDayConflict(other) && isTimeConflict(other);
    }

    // check if this course's lab section is conflict with other itself (not including other's lab section)
    private boolean isLabsConflictWithOther(Course other) {
        for (Course c: this.labs) {
            if (c.isTimeAndDayConflict(other)) {
                return true;
            }
        }
        return false;
    }

    private boolean isSameCourse(Course other) {
        return this.courseCode.equals(other.courseCode);
    }

    public boolean isConflict(Course other) {
        if (this.isSameCourse(other)) {
            return true;
        }
        if (this.labs.isEmpty() && other.labs.isEmpty()) { // check the two course themselves are conflicted
            return this.isTimeAndDayConflict(other);
        } else if (!this.labs.isEmpty() && other.labs.isEmpty()) {
            if (this.isTimeAndDayConflict(other)) {
                return true;
            } else {
                return this.isLabsConflictWithOther(other);
            }
        } else if (this.labs.isEmpty() && !other.labs.isEmpty()) {
            if (this.isTimeAndDayConflict(other)) {
                return true;
            } else {
                return other.isLabsConflictWithOther(this);
            }
        } else {
            if (this.isTimeAndDayConflict(other)) {
                return true;
            } else if (this.isLabsConflictWithOther(other)) {
                return true;
            } else if (other.isLabsConflictWithOther(this)) {
                return true;
            } else {
                for (Course c1: this.labs) {
                    for (Course c2: other.labs) {
                        if (c1.isTimeAndDayConflict(c2)) {
                            return true;
                        }
                    }
                }
                return false;
            }
        }

    }

    @Override
    public boolean equals(Object o) { // default equals that every field needs to be the same
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return credit == course.credit && stars == course.stars && Objects.equals(courseCode, course.courseCode) &&
                Objects.equals(startTime, course.startTime) && Objects.equals(endTime, course.endTime) &&
                Arrays.equals(days, course.days) && Objects.equals(labs, course.labs) && Objects.equals(instructor, course.instructor);
    }

    @Override
    public int hashCode() { // default hashcode that every field needs to be the same
        int result = Objects.hash(courseCode, startTime, endTime, labs, credit, instructor, stars);
        result = 31 * result + Arrays.hashCode(days);
        return result;
    }

    private String getDaysString() {
        String[] daysConverter = {"Su", "M", "T", "W", "Th", "F", "Sa"};
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < this.days.length; i++) {
            if (this.days[i]) {
                builder.append(daysConverter[i]);
            }
        }
        return builder.toString();
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("Course Code='" + courseCode + '\'' +
                ", start time=" + startTime +
                ", end time=" + endTime +
                ", days=" + getDaysString() +
                ", credit=" + credit +
                ", instructor='" + instructor + '\'' +
                ", stars=" + stars + "\n");
        if (!this.labs.isEmpty()) {
            for (Course c: this.labs) {
                output.append("labs for ").append(courseCode).append(": ").append("start time=").append(startTime).
                        append(", end time=").append(endTime).append(", days=").append(getDaysString()).
                        append(", instructor='").append(instructor).append("\n");
            }
        }
        return output.toString();
    }

    public String getCourseCode() {
        return courseCode;
    }

    public int getCredit() {
        return credit;
    }

    public int getStars() {
        return stars;
    }
}
