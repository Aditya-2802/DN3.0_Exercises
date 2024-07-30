package MVCPatternExample;

public class MVCPatternExample {
    public static void main(String[] args) {
        // Create the model
        Student model = new Student("1", "John Doe", "A");

        // Create the view
        StudentView view = new StudentView();

        // Create the controller
        StudentController controller = new StudentController(model, view);

        // Update view
        controller.updateView();

        // Update model data
        controller.setStudentName("Jane Doe");
        controller.setStudentGrade("B");

        // Update view again
        controller.updateView();
    }
}
