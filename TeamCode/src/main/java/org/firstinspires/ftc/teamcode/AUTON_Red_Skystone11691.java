package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Autonomous(name="Red Skystone", group="Autons")

public class AUTON_Red_Skystone11691 extends BaseAuton {


    int blockCount = 0;
    
    @Override
    public void runOpMode() {

        initialize();
        waitForStart();

        while (opModeIsActive()) {
            

            straff    (23,.75,6, telemetry);
            turn      (0,0.5,1, telemetry);
            waitStep(0.1);
            double totalDistanceMoved = get_SK      (20, telemetry);
            waitStep(0.8);
            straff      (-6,1,3, telemetry);
            turn      (-90,0.75,4, telemetry);
            waitStep(0.2);
            turn      (-90,0.25,1, telemetry);
            straff       (45 + totalDistanceMoved,1,4.5, telemetry);
            SK.SK_ARM(GlobalSettings11691.skhome);
            turn      (-90,0.5,1, telemetry);
            waitStep(0.8);
            straff      (-20,1,3, telemetry);
           
           
            
            sleep(200000);
        }
    }
    


    


    double get_SK (double timeout,Telemetry tele){
        boolean found = ColorSensor.StoneCheck(tele);
        double distanceToNextStone = -8;
        double distanceToCenter = -1;
        double totalDistanceMoved = 0;
        if (found) { 
         //SK.SK_ARM(GlobalSettings11691.skdown);
        }
        else {
            waitStep(0.2);
         driveForward (distanceToNextStone,0.75,2, telemetry);
         totalDistanceMoved += distanceToNextStone;
         found = ColorSensor.StoneCheck(tele);
         if (found){ 
           // SK.SK_ARM(GlobalSettings11691.skdown);
         }
         else {
             waitStep(0.2);
            driveForward (distanceToNextStone,0.75,2, telemetry);
            totalDistanceMoved += distanceToNextStone;
            //SK.SK_ARM(GlobalSettings11691.skdown);
         }
         }
        found = ColorSensor.StoneCheck(tele);
        driveForward (distanceToCenter,0.5,2, telemetry);
        totalDistanceMoved += distanceToCenter;
        SK.SK_ARM(GlobalSettings11691.skdown);
        
        return totalDistanceMoved * -1;
    }
}    
