package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

public class BaseAuton extends LinearOpMode{

    public enum PARK_POSITION {NEXT_TO_WALL, NEXT_TO_CENTER_BRIDGE}
    public enum COMPETITION_SIDE {RED, BLUE}
    public enum SKYSTONE_FULL {YES, NO}

    @Override
    public void runOpMode() throws InterruptedException {

    }

    HardwareMap11691        hMap;
    private AutonDrive11691         autonDrive;
    private AutonTurn11691          autonTurn;
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

    public SK_Block11691.SKYSTONE_ARM_LOCATION  usedSkystoneArm;

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
        while (opModeIsActive() && !isStopRequested() && (runtime.time() - timer < wait_in_sec))
        {
        }
    }

    protected void DriveByBumperSwitches(double speeda, double timeout)
    {
        autonDrive.DriveByBumperSwitches( speeda, timeout, this);
    }

    protected void driveForward (double dist, double power, double timeouta){
        autonDrive.encoderDriveAuton (dist, power,timeouta, this);
    }

    protected void driveBackward (double dist, double power, double timeouta){
        autonDrive.encoderDriveAuton (dist * -1, power,timeouta, this);
    }

    protected void turn_HighPowerAtEnd (double angle, double powerturn, double timeoutc){
        autonTurn.AutonTurn_HighPowerAtEnd (angle, powerturn, timeoutc, this);
    }

    protected void turn_HighPowerAtEnd (double angle, double powerturn, double deltaPower, double timeoutc){
        autonTurn.AutonTurn_HighPowerAtEnd (angle, powerturn, deltaPower, timeoutc, this);
    }

    protected void straff (double dist, double power, double timeoutb){
        autonDrive.Auton_Straff (dist,power,timeoutb, this);
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
        double totalDistanceMoved = 0;

        usedSkystoneArm = SK_Block11691.SKYSTONE_ARM_LOCATION.Right;

        leftColorSensor.StoneCheck();
        if (leftColorSensor.StoneCheck()){
            SK_Grab_Left.GrabSkystone();
            usedSkystoneArm = SK_Block11691.SKYSTONE_ARM_LOCATION.Left;
        }
        else if(rightColorSensor.StoneCheck()){
            SK_Grab_Right.GrabSkystone();
        }
        else {
            straff(distanceToNextStone,0.5,1);
            waitStep(0.5);
            SK_Grab_Right.GrabSkystone();
            totalDistanceMoved += distanceToNextStone;
        }

        return totalDistanceMoved * -1;
    }


    protected void runFirstPartOfSkystone(COMPETITION_SIDE competitionSide, SKYSTONE_FULL isFull) {
        double turnAngle;
        double speed;
        double straffLeft;
        double straffRight;
        if( competitionSide == COMPETITION_SIDE.RED)
        {
            turnAngle = -90;
            speed = 0.25;
            straffLeft = -2;
            straffRight = -8;
        }
        else
        {
            turnAngle = 90;
            speed = -0.25;
            straffLeft = 8;
            straffRight = 2;
        }

        driveBackward(30, .75, 6);
        waitStep(0.1);
        double totalDistanceMoved = get_SkyStone(20, telemetry);
        if(usedSkystoneArm == SK_Block11691.SKYSTONE_ARM_LOCATION.Left) {
            waitStep(1.4);
        }
        else {
            waitStep(0.8);
        }


        turn_HighPowerAtEnd(turnAngle, speed, 0.35, 3);
        waitStep(0.2);
        turn_HighPowerAtEnd(turnAngle, 0.25, 0, 3);
        waitStep(0.1);

        if(usedSkystoneArm == SK_Block11691.SKYSTONE_ARM_LOCATION.Right) {
            straff(straffLeft, 0.5, 2);
        }
        else
        {
            straff(straffRight, 0.5, 2);
        }

        if( isFull == SKYSTONE_FULL.YES) {
            driveBackward(65 + totalDistanceMoved, 1, 5.5);
        }
        else {
            driveBackward(70 + totalDistanceMoved, 1, 5.5);
        }
        SK_Grab_Left.goToHomePosition();
        SK_Grab_Right.goToHomePosition();
    }

    protected void runFoundationRoutine(COMPETITION_SIDE competitionSide, PARK_POSITION parkPosition)
    {
        /*====================================== Settings section start */
        double initialStraffDistance = 16;
        double initialStraffDirection = 1;
        double foundationPullEndAngle = 90;
        double foundationPullSPeed = 0.5;
        double beforeParkingStraff = 0;

        if(competitionSide == COMPETITION_SIDE.RED) {
            initialStraffDirection = 1;
            foundationPullEndAngle = -90;
            foundationPullSPeed = 0.5;
        }
        else {
            initialStraffDirection = -1;
            foundationPullEndAngle = 90;
            foundationPullSPeed = -0.5;
        }

        if(parkPosition == PARK_POSITION.NEXT_TO_CENTER_BRIDGE){
            beforeParkingStraff = -14;
        } else {
            beforeParkingStraff = 10;
        }
        /*====================================== Settings section end */

        double initialStraff = initialStraffDistance * initialStraffDirection;

        driveBackward  (1,1,0.5);
        waitStep(0.2);

        straff(initialStraff, 0.75, 2);
        waitStep(0.2);

        autonTurn.AutonTurn (0, 0.25, 0.5,  this);
        waitStep(0.2);

        driveBackward  (27,1,2.5);
        DriveByBumperSwitches(0.25, 1);

        foundationDN();
        driveBackward  (4,1,0.5);
        //waitStep(0.8);
        turn_HighPowerAtEnd(foundationPullEndAngle, foundationPullSPeed, 0.25, 3);
        driveBackward  (18,1,2);

        foundationUP();
        waitStep(0.8);

        straff(beforeParkingStraff,0.75,2);

        waitStep(0.8);
        turn_HighPowerAtEnd(foundationPullEndAngle, 0.5, 0, 3);
        waitStep(0.8);

        driveForward(45,0.8,4);
    }

}
