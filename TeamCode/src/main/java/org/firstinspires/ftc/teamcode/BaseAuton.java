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
        SK_Block11691           SK;
        AutonColorSensor11691   ColorSensor;
        TapeMeasure11691        TapeMea;
        AutonColorSensor11691   AutonSKDetect;
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
            SK              = new SK_Block11691(hMap);
            ColorSensor     = new AutonColorSensor11691(hMap);
            autonFoundation = new Foundation11691(hMap);
            TapeMea         = new TapeMeasure11691(hMap);
            AutonSKDetect   = new AutonColorSensor11691(hMap);
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

        protected void turn (double angle, double powerturn, double timeoutc, Telemetry tele){
            autonTurn.AutonTurn (angle, powerturn, timeoutc, tele);
        }

        protected void drive (double dist, double power, double timeouta, Telemetry tele){
            autonDrive.encoderDriveAuton (dist, power,timeouta, tele);
        }

        protected void turn_HighPowerAtEnd (double angle, double powerturn, double timeoutc, Telemetry tele){
            autonTurn.AutonTurn_HighPowerAtEnd (angle, powerturn, timeoutc, tele);
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


    }
