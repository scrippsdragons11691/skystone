package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Blue Two Skystones", group="Autons")

public class AUTON_Blue_Two_Skystone11691 extends BaseAuton {

    @Override
    public void runOpMode() {

        initialize();
        waitForStart();
        double turnAngle;

        COMPETITION_SIDE competitionSide = COMPETITION_SIDE.BLUE;

        boolean threeStones = true;

        if( competitionSide == COMPETITION_SIDE.RED)
        {
            turnAngle = -5;
        }
        else
        {
            turnAngle = 5;
        }

        while (opModeIsActive()) {

            double distance = runFirstPartOfSkystone(competitionSide, SKYSTONE_FULL.NO,true, true, false, SK_Block11691.SKYSTONE_ARM_LOCATION.Left);

            // Determine which arm to use for the 3rd stone
            SK_Block11691.SKYSTONE_ARM_LOCATION useThisArm
                    = usedSkystoneArm == SK_Block11691.SKYSTONE_ARM_LOCATION.Left ?
                    SK_Block11691.SKYSTONE_ARM_LOCATION.Right : SK_Block11691.SKYSTONE_ARM_LOCATION.Left;

            driveForward(distance + (GlobalSettings11691.StoneLength_inch * 3),1,10);

            waitStep(0.0);
            turn_HighPowerAtEnd(turnAngle,1,3);

            distance = run2ndPartOfSkystone(competitionSide, SKYSTONE_FULL.NO,true);

            if(threeStones) {
                driveForward(distance - (GlobalSettings11691.OneTileLength_inch*0.8)-GlobalSettings11691.StoneLength_inch, 1, 5.5);

                waitStep(0.0);
                turn_HighPowerAtEnd(turnAngle,1,3);

                runFirstPartOfSkystone(competitionSide, SKYSTONE_FULL.NO,true, false, true, useThisArm);
            }

            driveForward(40, 1, 5.5);

            uninitialize();
            sleep(200000);
        }
    }


}    
