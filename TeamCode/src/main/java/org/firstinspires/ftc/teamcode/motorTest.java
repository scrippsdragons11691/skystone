package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Basic: motorTest", group="Linear Opmode")
//@Disabled
public class motorTest extends BaseAuton {

    @Override
    public void runOpMode() {

        initialize();
        waitForStart();

        while (opModeIsActive()) {
            
            /*
            core functions
                passed variables
                    drive - distance, power, timeout, telemetry pass
                    straff - distance, power, timeout,telemetry pass
                    delay - timeout - used to display information to phone
                    foundationUP - foundation servo up
                    foundationDN - foundation servo down - grabs the foundation
                    grabOPEN - graber to open position
                    grabCLOSE - grabber to close position
                    TapeMeasure - encoder count target, telemetry pass - extends tapemeasure.
                    get_SK - timeout - boolean that will change to true when SK is detected.
                no passed variables
                    intakeIN - pull blocks in
                    intakeOUT - push blocks out
                    movearmHOME - moves arm to home posiiton
                    movearmNINETY - moves arm to 90 deg position
                    movearmONEEIGHTY - moves arm to 180 position
                    pusherHOME - moves arm to HOME position
                    pusherPUSH - moves arm to block in position.
                    
            */
            
            autonDrive.displayPositionTolerance(telemetry);
            waitStep(5);
            autonDrive.setPositionTolerance(20);
            waitStep(5);
            autonDrive.displayPositionTolerance(telemetry);
            
            if(false)
            {
                turnRight (90,0.75,5, telemetry);
                /*
                straff (15,0.5,10, telemetry);
                
                turn (-90,0.5,5, telemetry);
                waitStep(1);
                turn (-180,0.5,5, telemetry);
                waitStep(1);
                turn (-270,0.5,5, telemetry);
                waitStep(1);
                turn (0,0.5,5, telemetry);
                
                drive     (-29.5,0.5,10, telemetry); 
                foundationDN();
                waitStep(2);
                
                drive     (28,0.5,10, telemetry);
                foundationUP();
                waitStep(1);
    
                straff      (-20,1,10, telemetry);   
    
                turn      (45,1,5, telemetry);*/        
            }
            /*else if(false)
            {
                
              intakeIN();
              waitStep(5);
              intakeOUT();
                
            }else{
                
                for(int i = 0; i < 2; i++){
                pusherHOME();
                waitStep(5);
                pusherPUSH();
                waitStep(2);
                }
            }*/
            //delay(20);
            sleep(200000);
        }
    }
}
