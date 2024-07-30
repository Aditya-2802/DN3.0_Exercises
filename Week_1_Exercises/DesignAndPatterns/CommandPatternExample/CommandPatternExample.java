package CommandPatternExample;

public class CommandPatternExample {
    public static void main(String[] args) {
        // Create the receiver
        Light light = new Light();

        // Create commands
        Command lightOn = new LightOnCommand(light);
        Command lightOff = new LightOffCommand(light);

        // Create the invoker
        RemoteControl remoteControl = new RemoteControl();

        // Turn the light on
        remoteControl.setCommand(lightOn);
        remoteControl.pressButton();

        // Turn the light off
        remoteControl.setCommand(lightOff);
        remoteControl.pressButton();
    }
}
