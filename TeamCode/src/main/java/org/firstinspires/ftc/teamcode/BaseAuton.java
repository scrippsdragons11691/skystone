package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

public class BaseAuton extends LinearOpMode{


    @Override
    public void runOpMode() throws InterruptedException {

    }

    HardwareMap11691        hMap;
        AutonDrive11691         autonDrive;
        AutonTurn11691          autonTurn;
        ElapsedTime runtime;
        Intake_11691            intake;
        MoveArm11691            movearm;
        GrabBlock11691          grab;
        Foundation11691         autonFoundation;
        LIFT11691               lift;
        Pusher11691             pusher;
        public SK_Block11691           SK_Grab_Right;
        public SK_Block11691           SK_Grab_Left;
        ColorSensor11691   leftColorSensor;
        ColorSensor11691   rightColorSensor;
        TapeMeasure11691        TapeMea;
        //AutonColorSensor11691   AutonSKDetect;
        public void BaseAuton() {
        }

        protected void initialize(){
            hMap            = new HardwareMap11691(hardwareMap);
            autonDrive      = new AutonDrive11691(hMap);
            autonTurn       = new AutonTurn11691(hMap);
            runtime         = new ElapsedTime();
            intake          = new Intake_11691(hMap);
            movearm         = new MoveArm11691(hMap);
            grab            = new GrabBlock11691 (hMap);
            lift            = new LIFT11691(hMap);
            pusher          = new Pusher11691(hMap);
            SK_Grab_Right   = new SK_Block11691(hMap, SK_Block11691.SKYSTONE_ARM_LOCATION.Right);
            SK_Grab_Left    = new SK_Block11691(hMap, SK_Block11691.SKYSTONE_ARM_LOCATION.Left);
            leftColorSensor     = new ColorSensor11691(hMap, ColorSensor11691.SKYSTONE_COLOR_SENSOR_LOCATION.Left);
            rightColorSensor     = new ColorSensor11691(hMap, ColorSensor11691.SKYSTONE_COLOR_SENSOR_LOCATION.Right);
            autonFoundation = new Foundation11691(hMap);
            TapeMea         = new TapeMeasure11691(hMap);
            //AutonSKDetect   = new AutonColorSensor11691(hMap);
        }

        protected void delay (double timeoutc) {
            double timer = runtime.time();
            while (opModeIsActive() && runtime.time() - timer < timeoutc){
                telemetry.addData("actual angle","degrees= %.2f", hMap.imu.getAngularOrientation(AxesReference.INTRINSIC , AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle);
                telemetry.addData("error", autonTurn.error);
                telemetry.addData("angle", autonTurn.targetAngle);
                telemetry.addData("is_moving turn", autonTurn.is_moving);
                telemetry.addData("is_moving drive", autonDrive.is_moving);
                telemetry.addData("actualangle", autonTurn.actualangle);
                telemetry.addData("LR encoder","positione=", hMap.LR.getCurrentPosition());
                telemetry.addData("Runtime",runtime.time());
                telemetry.addData("Timeout", timeoutc);
                telemetry.update();
            }
        }

        protected void waitStep(double wait_in_sec)
        {
            double timer = runtime.time();
            while (opModeIsActive() && (runtime.time() - timer < wait_in_sec))
            {
            }
        }

        protected void turnLeft (double angle, double powerturn, double timeoutc, Telemetry tele){
            autonTurn.AutonTurn (angle * -1, powerturn, timeoutc, tele);
    }

        protected void turnRight (double angle, double powerturn, double timeoutc, Telemetry tele){
            autonTurn.AutonTurn (angle, powerturn, timeoutc, tele);
    }

        protected void driveForward (double dist, double power, double timeouta, Telemetry tele){
            autonDrive.encoderDriveAuton (dist, power,timeouta, tele);
        }

        protected void driveBackward (double dist, double power, double timeouta, Telemetry tele){
            autonDrive.encoderDriveAuton (dist * -1, power,timeouta, tele);
    }

        protected void turn_HighPowerAtEnd (double angle, double powerturn, double timeoutc, Telemetry tele){
            autonTurn.AutonTurn_HighPowerAtEnd (angle, powerturn, timeoutc, tele);
        }

    protected void turn_HighPowerAtEnd (double angle, double powerturn, double deltaPower, double timeoutc, Telemetry tele){
        autonTurn.AutonTurn_HighPowerAtEnd (angle, powerturn, deltaPower, timeoutc, tele);
    }

        protected void straff (double dist, double power, double timeoutb,Telemetry tele){
            autonDrive.Auton_Straff (dist,power,timeoutb, tele);
        }

        protected void Move_Tape (int target, double timeout, Telemetry tele){
            TapeMea.AutonTape (target, timeout, tele);
        }

        protected void foundationUP()     { autonFoundation.Foundation (GlobalSettings11691.foundationHome); }
        protected void foundationDN()     { autonFoundation.Foundation (GlobalSettings11691.foundationHook); }
        protected void grabOPEN()         { grab.Grab         (GlobalSettings11691.grabRelease);   }
        protected void grabCLOSE()        { grab.Grab         (GlobalSettings11691.grabBlock);  }
        protected void intakeIN()         { intake.intake     (GlobalSettings11691.intakeInpos);}
        protected void intakeOUT()        { intake.intake     (GlobalSettings11691.intakeOutpos);}
        protected void movearmHOME()      { movearm.MoveArm   (GlobalSettings11691.armHome);}
        protected void movearmNINETY()    { movearm.MoveArm   (GlobalSettings11691.armNinety);}
        protected void movearmONEEIGHTY() { movearm.MoveArm   (GlobalSettings11691.armOneEighty);}
        protected void pusherHOME()       { pusher.Pusher     (GlobalSettings11691.pushHome);}
        protected void pusherPUSH()       { pusher.Pusher     (GlobalSettings11691.pushBlock);}


    double get_SkyStone (double timeout,Telemetry tele){
        double distanceToNextStone = 7;
        double distanceToCenter = -1;
        double totalDistanceMoved = 0;

            leftColorSensor.StoneCheck();
            if (leftColorSensor.StoneCheck()){
                SK_Grab_Left.GrabSkystone();
            }
            else if(rightColorSensor.StoneCheck()){

                SK_Grab_Right.GrabSkystone();
               /* waitStep(0.2);
                driveForward (distanceToNextStone,0.75,2, telemetry);
                totalDistanceMoved += distanceToNextStone;
                //SK.SK_ARM(GlobalSettings11691.skdown);*/
            }else {
                straff(distanceToNextStone,0.5,1, tele);
                waitStep(0.5);
                SK_Grab_Right.GrabSkystone();
                totalDistanceMoved += distanceToNextStone;
            }


        return totalDistanceMoved * -1;
    }

    }


    //todo Pass the LinearOpMode instance to autonDrive and to autonTurn so that we can call opModeIsActive() and isStopRequested() from within