package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Blue Skystone Full", group="Autons")

public class AUTON_Blue_Skystone11691_Full extends BaseAuton {

    @Override
    public void runOpMode() {

        initialize();
        waitForStart();

        while (opModeIsActive())   {              
            
        // Get Skystone
            driveBackward       (6,0.5,2,telemetry);
            straff      (23,0.5,3, telemetry);
            turnLeft        (0,0.5,4, telemetry);
            waitStep(0.1);
            double totalDistanceMoved = get_SkyStone      (20, telemetry);
            waitStep(0.8);
        
        //deliver skystone
            straff      (-6,0.5,2, telemetry);
            turnRight      (90,0.5,3, telemetry);
            waitStep(0.5);
            turnRight      (90,0.25,1.5, telemetry);
            straff       (-52 + totalDistanceMoved,1,6, telemetry);
            
            waitStep(0.8);
            turnRight (90,0.5,4, telemetry);
            
        // Move Foundation
            driveBackward     (8,0.75,3, telemetry);
            foundationDN();
            waitStep(0.8);
            driveForward   (6,0.75,2, telemetry);
            turn_HighPowerAtEnd      (0,1,1, telemetry);
            //SK.SK_ARM(GlobalSettings11691.skhome);
            straff    (-18,0.75,1.5, telemetry);
            turn_HighPowerAtEnd      (0,.5,2, telemetry);
            waitStep(0.2);
            driveBackward     (24,1.0,2.5, telemetry);
            foundationUP();
    
        // drive under bridge
            waitStep(0.2);
            turnLeft      (0,.25,1, telemetry);
            driveForward     (2,1,1, telemetry);
            turnLeft      (0,.25,1, telemetry);
            straff    (-8,0.75,1.5, telemetry);
            driveForward     (40,1,3, telemetry);

        
        sleep (200000);
        }    
            
    }


}

