package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Red Foundation FWD", group="Autons")

public class Auton_Red_Foundation_FWD_11691 extends BaseAuton {

    @Override
    public void runOpMode() {

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
                    wait_step - time in seconds
                no passed variables
                    intakeIN - pull blocks in
                    intakeOUT - push blocks out
                    movearmHOME - moves arm to home posiiton
                    movearmNINETY - moves arm to 90 deg position
                    movearmONEEIGHTY - moves arm to 180 position
                    pusherHOME - moves arm to HOME position
                    pusherPUSH - moves arm to block in position.
                    
            */  
                drive     (-30,.75,3, telemetry);
                
                foundationDN();
                waitStep(0.5);
                
                turn_HighPowerAtEnd      (-90,1,5, telemetry);
                waitStep(0.5);
                straff    (20,1,3, telemetry);
                turn_HighPowerAtEnd      (-90,1,5, telemetry);
                drive     (-18,1,3, telemetry);
                foundationUP();
                turn      (-90,1,5, telemetry);
                waitStep(0.5);
                
                drive     (38,1,3.5, telemetry);
                
                sleep(200000);             
        }
    }

    




    
   /* void get_SK (double timeout){
        boolean found = ColorSensor.StoneCheck();
        time = runtime.time();
        while (opModeIsActive() && !found && (runtime.time()-time < timeout)) {
            autonDrive.basicDrive(0.5);
        }
        SK.SK_ARM(GlobalSettings11691.skdown);
    }*/
}    
