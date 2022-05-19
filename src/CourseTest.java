public class CourseTest {
    public static void main(String[] args) throws Exception {
        test();
    }

    public static void test() throws Exception {
        boolean[] MW = {false, true, false, true, false, false, false};
        boolean[] TTh = {false, false, true, false, true, false, false};
        boolean[] F = {false, false, false, false, false, true, false};

        Course CS325 = new Course("CS 325", "14:30", "15:45", TTh, 3, "Steven La Fleur", 3);
        Course CS326_1 = new Course("CS 326", "13:00", "14:15", TTh, 3, "Steven La Fleur", 3);
        Course CS326_2 = new Course("CS 326", "16:00", "17:15", TTh, 3, "Michelangelo Grigni", 4);
        Course CS334 = new Course("CS 334", "13:00", "14:15", MW, 3, "Li Xiong", 5);
        Course CS350_1 = new Course("CS 350", "08:30", "09:45", MW, 3, "Dorian Arnold", 5);
        Course CS350_2 = new Course("CS 350", "11:30", "12:45", TTh, 3, "Kenneth I Mandelberg", 3);
        Course CS370_1 = new Course("CS 370", "13:00", "14:15", MW, 3, "Davide Fossati", 5);
        Course CS370_2 = new Course("CS 370", "14:30", "15:45", MW, 3, "Davide Fossati", 5);
        Course CS377 = new Course("CS 377", "08:30", "09:45", MW, 3, "Nosayba El-Sayed", 4);
        Course CS385 = new Course("CS 385", "14:30", "15:45", MW, 3, "Jinho Choi", 5);
        Course PHYS220 = new Course("PHYS 220", "11:30", "12:45", TTh, 3, "Keith Berland", 4);
        Course PHYS361 = new Course("PHYS 361", "13:00", "14:15", MW, 3, "Minsu Kim", 4);
        Course PHYS380 = new Course("PHYS 380", "16:00", "17:15", TTh, 3, "Alissa Bans", 5);
        Course MATH315_1 = new Course("MATH 315", "14:30", "15:45", TTh, 4, "Talea L. Mayo", 5);
        MATH315_1.addLab("14:30", "15:20", F);
        Course MATH315_2 = new Course("MATH 315", "14:30", "15:45", MW, 4, "Staff", 4);
        MATH315_2.addLab("11:30", "12:20", F);

        Courses courses = new Courses(CS325, CS326_1, CS326_2, CS334, CS350_1, CS350_2, CS370_1, CS370_2, CS377, CS385, PHYS220, PHYS361, PHYS380, MATH315_1, MATH315_2);
        courses.printCombination(5);
    }

    public static void testEqual() throws Exception {
        boolean[] MW = {false, true, false, true, false, false, false};
        boolean[] TTh = {false, false, true, false, true, false, false};
        boolean[] F = {false, false, false, false, false, true, false};
        Courses courses = new Courses();
        Course c1 = new Course("CS 325", "14:30", "15:45", TTh, 3, "A", 5);
        c1.addLab("17:00", "17:45", F);
        Course c2 = new Course("CS 326", "13:00", "14:15", TTh, 3, "F", 5);
        c2.addLab("17:00", "17:45", F);
        System.out.println(c1.isConflict(c2));
    }
}
