package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Autonomous(name="Blue Skystone", group="Autons")

public class AUTON_Blue_Skystone11691 extends BaseAuton {

    @Override
    public void runOpMode() {

        initialize();
        waitForStart();

        while (opModeIsActive()) {

            driveBackward       (7,0.5,2,telemetry);
            straff      (23,0.5,3, telemetry);
            turnLeft        (0,0.5,4, telemetry);
            waitStep(0.1);
            double totalDistanceMoved = get_SK      (20, telemetry);
            waitStep(0.8);
            straff      (-8.5,0.5,2, telemetry);
            turnLeft      (90,0.5,4, telemetry);
            turnLeft      (90,0.5,4, telemetry);
            straff       (44 + totalDistanceMoved,1,4, telemetry);
            //SK.SK_ARM(GlobalSettings11691.skhome);
            
            waitStep(0.8);
            turnLeft (90,0.5,4, telemetry);
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
         driveForward (distanceToNextStone,0.5,10, telemetry);
         totalDistanceMoved += distanceToNextStone;
         found = ColorSensor.StoneCheck(tele);
         if (found){ 
           // SK.SK_ARM(GlobalSettings11691.skdown);
         }
         else {
             waitStep(0.2);
            driveForward (distanceToNextStone,0.5,10, telemetry);
            totalDistanceMoved += distanceToNextStone;
            //SK.SK_ARM(GlobalSettings11691.skdown);
         }
         }
        found = ColorSensor.StoneCheck(tele);
        driveBackward (distanceToCenter,0.5,10, telemetry);
        totalDistanceMoved += distanceToCenter;
        //SK.SK_ARM(GlobalSettings11691.skdown);
        
        return totalDistanceMoved * 1;
    }
}    
