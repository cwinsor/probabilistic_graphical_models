package cwinsor.us.z.other;


// http://www.javaworld.com/article/2077569/core-java/java-tip-68--learn-how-to-implement-the-command-pattern-in-java.html

class Fan {
        public void startRotate() {
                System.out.println("Fan is rotating");
        }
        public void stopRotate() {
                System.out.println("Fan is not rotating");
        }
}
class Light {
        public void turnOn( ) {
                System.out.println("Light is on ");
        }
        public void turnOff( ) {
                System.out.println("Light is off");
        }
}
class Switch {
        private Command UpCommand, DownCommand;
        public Switch( Command Up, Command Down) {
                UpCommand = Up; // concrete Command registers itself with the invoker 
                DownCommand = Down;
        }
        void flipUp( ) { // invoker calls back concrete Command, which executes the Command on the receiver 
                        UpCommand . execute ( ) ;                           
        }
        void flipDown( ) {
                        DownCommand . execute ( );
        }
}
class LightOnCommand implements Command {
        private Light myLight;
        public LightOnCommand ( Light L) {
                myLight  =  L;
        }
        public void execute( ) {
                myLight . turnOn( );
        }
}
class LightOffCommand implements Command {
        private Light myLight;
        public LightOffCommand ( Light L) {
                myLight  =  L;
        }
        public void execute( ) {
                myLight . turnOff( );
        }
}
class FanOnCommand implements Command {
        private Fan myFan;
        public FanOnCommand ( Fan F) {
                myFan  =  F;
        }
        public void execute( ) {
                myFan . startRotate( );
        }
}
class FanOffCommand implements Command {
        private Fan myFan;
        public FanOffCommand ( Fan F) {
                myFan  =  F;
        }
        public void execute( ) {
                myFan . stopRotate( );
        }
}
public class TestCommand {
                public static void main(String[] args) {
                        Light  testLight = new Light( );
                        LightOnCommand testLOC = new LightOnCommand(testLight);
                        LightOffCommand testLFC = new LightOffCommand(testLight);
                        Switch testSwitch = new Switch( testLOC,testLFC);       
                        testSwitch.flipUp( );
                        testSwitch.flipDown( );
                        Fan testFan = new Fan( );
                        FanOnCommand foc = new FanOnCommand(testFan);
                        FanOffCommand ffc = new FanOffCommand(testFan);
                        Switch ts = new Switch( foc,ffc);
                        ts.flipUp( );
                        ts.flipDown( ); 
                }
}               
