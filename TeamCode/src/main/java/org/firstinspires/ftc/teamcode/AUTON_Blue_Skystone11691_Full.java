package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Autonomous(name="Blue Skystone Full", group="Autons")

public class AUTON_Blue_Skystone11691_Full extends BaseAuton {

    @Override
    public void runOpMode() {

        waitForStart();

        while (opModeIsActive())   {              
            
        // Get Skystone
            drive       (-6,0.5,2,telemetry);
            straff      (23,0.5,3, telemetry);
            turn        (0,0.5,4, telemetry);
            waitStep(0.1);
            double totalDistanceMoved = get_SK      (20, telemetry);
            waitStep(0.8);
        
        //deliver skystone
            straff      (-6,0.5,2, telemetry);
            turn      (-90,0.5,3, telemetry);
            waitStep(0.5);
            turn      (-90,0.25,1.5, telemetry);
            straff       (-52 + totalDistanceMoved,1,6, telemetry);
            
            waitStep(0.8);
            turn (-90,0.5,4, telemetry);
            
        // Move Foundation
            drive     (-8,0.75,3, telemetry); 
            foundationDN();
            waitStep(0.8);
            drive   (6,0.75,2, telemetry); 
            turn_HighPowerAtEnd      (0,1,1, telemetry);
            SK.SK_ARM(GlobalSettings11691.skhome);
            straff    (-18,0.75,1.5, telemetry);
            turn_HighPowerAtEnd      (0,.5,2, telemetry);
            waitStep(0.2);
            drive     (-24,1.0,2.5, telemetry);
            foundationUP();
    
        // drive under bridge
            waitStep(0.2);
            turn      (0,.25,1, telemetry);
            drive     (2,1,1, telemetry);
            turn      (0,.25,1, telemetry);
            straff    (-8,0.75,1.5, telemetry);
            drive     (40,1,3, telemetry);

        
        sleep (200000);
        }    
            
    }

    double get_SK (double timeout,Telemetry tele){
        boolean found = ColorSensor.StoneCheck(tele);
        double distanceToNextStone = 8;
        double distanceToCenter = 2;
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
        
        return totalDistanceMoved *-1;
    }
}

