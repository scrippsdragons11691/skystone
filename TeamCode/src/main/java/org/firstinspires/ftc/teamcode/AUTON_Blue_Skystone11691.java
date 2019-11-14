package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Autonomous(name="Blue Skystone", group="Autons")

public class AUTON_Blue_Skystone11691 extends BaseAuton {

    @Override
    public void runOpMode() {
        
        waitForStart();

        while (opModeIsActive()) {
            
            /*
            core functions
                passed variables
                    drive - distance, power, timeout, telemetry pass
                    straff - distance, power, timeout,telemetry pass(left = postitive, right = negative)
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
            drive       (-7,0.5,2,telemetry);
            straff      (23,0.5,3, telemetry);
            turn        (0,0.5,4, telemetry);
            waitStep(0.1);
            double totalDistanceMoved = get_SK      (20, telemetry);
            waitStep(0.8);
            straff      (-8.5,0.5,2, telemetry);
            turn      (90,0.5,4, telemetry);
            turn      (90,0.5,4, telemetry);
            straff       (44 + totalDistanceMoved,1,4, telemetry);
            SK.SK_ARM(GlobalSettings11691.skhome);
            
            waitStep(0.8);
            turn (90,0.5,4, telemetry);
            straff      (-18,1,3, telemetry);
            

              sleep(200000);
        }
    }

    double get_SK (double timeout,Telemetry tele){
        boolean found = ColorSensor.StoneCheck(tele);
        double distanceToNextStone = 8;
        double distanceToCenter = 0;
        double totalDistanceMoved = 0;
        if (found) { 
         //SK.SK_ARM(GlobalSettings11691.skdown);
        }
        else {
            waitStep(0.2);
         drive (distanceToNextStone,0.5,10, telemetry);
         totalDistanceMoved += distanceToNextStone;
         found = ColorSensor.StoneCheck(tele);
         if (found){ 
           // SK.SK_ARM(GlobalSettings11691.skdown);
         }
         else {
             waitStep(0.2);
            drive (distanceToNextStone,0.5,10, telemetry);
            totalDistanceMoved += distanceToNextStone;
            //SK.SK_ARM(GlobalSettings11691.skdown);
         }
         }
        found = ColorSensor.StoneCheck(tele);
        drive (distanceToCenter * -1,0.5,10, telemetry);
        totalDistanceMoved += distanceToCenter;
        SK.SK_ARM(GlobalSettings11691.skdown);
        
        return totalDistanceMoved * 1;
    }
}    
