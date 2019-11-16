package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Color Hue Test", group="Linear Opmode")
//@Disabled
public class AutonColorCheck11691 extends BaseAuton {

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
            /*
            double totalDistanceMoved = get_SK      (10, telemetry);
            //SK.SK_ARM(GlobalSettings11691.skhome);
            waitStep(1);
            totalDistanceMoved = get_SK      (10, telemetry);
            //SK.SK_ARM(GlobalSettings11691.skhome);
            waitStep(1);
            */
            sleep(200000);
        }
    }

    /*
    double get_SK (double timeout,Telemetry tele)
    {
        boolean found = ColorSensor.StoneCheck(tele);
        double distanceToNextStone = -8;
        double distanceToCenter = -3;
        double totalDistanceMoved = 0;
        if (found) { 

        }
        else {
         waitStep(3);
         drive (distanceToNextStone,0.5,10, telemetry);
         totalDistanceMoved += distanceToNextStone;
         found = ColorSensor.StoneCheck(tele);
         if (found){                                    // found block

         }
         else {                                         //did not find block
            waitStep(3);
            drive (distanceToNextStone,0.5,10, telemetry);
            totalDistanceMoved += distanceToNextStone;
         }
        }
        found = ColorSensor.StoneCheck(tele);
        drive (distanceToCenter,0.5,10, telemetry);
        totalDistanceMoved += distanceToCenter;
        //SK.SK_ARM(GlobalSettings11691.skdown);
        
        return totalDistanceMoved * -1;
    }*/
}    
