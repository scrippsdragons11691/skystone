package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class AutonDrive11691 extends BaseAutonIMU {
    //Rev Hex HD Motor 2240 counts per rotation
    static final double COUNTS_PER_MOTOR_REV =      1120; //20 - 537.6, 40 - 1120, 60- 1680 (cpr) Gear Neverest Motor Encoder
    static final double DRIVE_GEAR_REDUTION =       2.2; // This is < 1.0 if geared UP
    static final double WHEEL_DIAMETER_INCHES =     3.0; // For figuring circumference
    static final double COUNTS_PER_INCH =(COUNTS_PER_MOTOR_REV / DRIVE_GEAR_REDUTION)/(WHEEL_DIAMETER_INCHES*3.1415);
    boolean is_moving = false;

    double minimumMotorSpeed = 0;
    double maximumMotorSpeed = 1;
    double rampTimeInSec = 1;

    ElapsedTime runtime     = new ElapsedTime();

    // Constructor
    public AutonDrive11691(HardwareMap11691 HMap){

        super(HMap);

        //set direction for each motor
        theHardwareMap11691.LR.setDirection(DcMotor.Direction.REVERSE);
        theHardwareMap11691.LF.setDirection(DcMotor.Direction.REVERSE);
        theHardwareMap11691.RR.setDirection(DcMotor.Direction.FORWARD);
        theHardwareMap11691.RF.setDirection(DcMotor.Direction.FORWARD);

        setPositionTolerance(20);
    }

    // Straffing Auton
    public void Auton_Straff (double dist_Straff_In, double speed_Straff, double timeoutS, Telemetry tele){


        int newStraffLeftFTarget;
        int newStraffRightFTarget;
        int newStraffLeftBTarget;
        int newStraffRightBTarget;

        // Reset the encoders
        theHardwareMap11691.LF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        theHardwareMap11691.LR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        theHardwareMap11691.RR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        theHardwareMap11691.RF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Determine new target postition, and pass to motor controller
        newStraffLeftFTarget  = (int)(-1*dist_Straff_In * COUNTS_PER_INCH / 0.707);
        newStraffRightFTarget = (int)(dist_Straff_In * COUNTS_PER_INCH / 0.707);
        newStraffLeftBTarget  = (int)(dist_Straff_In * COUNTS_PER_INCH / 0.707);
        newStraffRightBTarget = (int)(-1*dist_Straff_In * COUNTS_PER_INCH / 0.707);

        //Send the target position to the REV module
        theHardwareMap11691.LF.setTargetPosition(newStraffLeftFTarget);
        theHardwareMap11691.RF.setTargetPosition(newStraffRightFTarget);
        theHardwareMap11691.LR.setTargetPosition(newStraffLeftBTarget);
        theHardwareMap11691.RR.setTargetPosition(newStraffRightBTarget);


        //Set the motors to run to encoder mode
        theHardwareMap11691.LF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        theHardwareMap11691.RF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        theHardwareMap11691.LR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        theHardwareMap11691.RR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //Set the motor speed
        runtime.reset();
        theHardwareMap11691.LF.setPower(speed_Straff);
        theHardwareMap11691.RF.setPower(speed_Straff);
        theHardwareMap11691.LR.setPower(speed_Straff);
        theHardwareMap11691.RR.setPower(speed_Straff);

        runtime.reset();
        while ((runtime.seconds() < timeoutS) && (theHardwareMap11691.LR.isBusy())){



            tele.addData("is_moving drive", is_moving);
            tele.addData("LF encoder","position= %d", theHardwareMap11691.LF.getCurrentPosition());
            tele.addData("RF encoder","position= %d", theHardwareMap11691.RF.getCurrentPosition());
            tele.addData("LR encoder","position= %d", theHardwareMap11691.LR.getCurrentPosition());
            tele.addData("RR encoder","position= %d", theHardwareMap11691.RR.getCurrentPosition());
            tele.addData("Runtime",runtime.time());
            tele.addData("Timeout", timeoutS);
            tele.update();

        }

        theHardwareMap11691.LF.setPower(0);
        theHardwareMap11691.RF.setPower(0);
        theHardwareMap11691.LR.setPower(0);
        theHardwareMap11691.RR.setPower(0);

        theHardwareMap11691.LF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        theHardwareMap11691.RF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        theHardwareMap11691.RR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        theHardwareMap11691.LR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    // Drive Auton
    public void encoderDriveAuton(double distanceInches, double speed, double timeoutT, Telemetry tele){

        double leftInches   = distanceInches;
        double rightInches  = distanceInches;
        double motorspeed = speed;
        double timeoutS;

        int newLeftFTarget;
        int newRightFTarget;
        int newLeftBTarget;
        int newRightBTarget;

        //Set the motors to run to encoder mode
        theHardwareMap11691.LF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        theHardwareMap11691.RF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        theHardwareMap11691.RR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        theHardwareMap11691.LR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Reset the encoders
        theHardwareMap11691.LF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        theHardwareMap11691.LR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        theHardwareMap11691.RR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        theHardwareMap11691.RF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Determine new target postition, and pass to motor controller
        newLeftFTarget  = (int)(leftInches * COUNTS_PER_INCH);
        newRightFTarget = (int)(rightInches * COUNTS_PER_INCH);
        newLeftBTarget  = (int)(leftInches * COUNTS_PER_INCH);
        newRightBTarget = (int)(rightInches * COUNTS_PER_INCH);

        //Set the motors to run to encoder mode
        theHardwareMap11691.LF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        theHardwareMap11691.RF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        theHardwareMap11691.LR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        theHardwareMap11691.RR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //Send the target position to the REV module
        theHardwareMap11691.LF.setTargetPosition(newLeftFTarget);
        theHardwareMap11691.RF.setTargetPosition(newRightFTarget);
        theHardwareMap11691.LR.setTargetPosition(newLeftBTarget);
        theHardwareMap11691.RR.setTargetPosition(newRightBTarget);

        ElapsedTime rampTimer = new ElapsedTime();
        rampTimer.reset();
        runtime.reset();

        double DrivingAngle = globalAngle;

        while ((runtime.seconds() < timeoutT) &&
                (theHardwareMap11691.LR.isBusy())) {

            double rampedSpeed = speed;
            int remainingEncoderCounts = Math.abs(newLeftFTarget - theHardwareMap11691.LF.getCurrentPosition());

            if(rampTimer.seconds() <= rampTimeInSec) {
                // Spinning the wheels introduces an error when driving using encoders. Therefore ramp the wheel power up so that the wheels do not spin.
                rampedSpeed = Range.clip(speed * (rampTimer.seconds() / rampTimeInSec), minimumMotorSpeed, speed);
            }
            else if(remainingEncoderCounts < GlobalSettings11691.EncoderCountRampDownThreshold)
            {
                // If we shut down motor power suddenly, the robot will slide. Therefore ramp power down
                rampedSpeed = Range.clip(speed * (remainingEncoderCounts / GlobalSettings11691.EncoderCountRampDownThreshold), GlobalSettings11691.MinimumRampDownSpeed, speed);
            }

            // Use gyro to drive in a straight line.
            double correction = checkDirection(DrivingAngle);

            //Set the motor speed
            if (distanceInches > 0){
                correction *= -1;
            }
            theHardwareMap11691.LF.setPower(rampedSpeed - correction);
            theHardwareMap11691.LR.setPower(rampedSpeed - correction);
            theHardwareMap11691.RF.setPower(rampedSpeed + correction);
            theHardwareMap11691.RR.setPower(rampedSpeed + correction);

            tele.addData("1 imu heading", lastAngles.firstAngle);
            tele.addData("2 global heading", globalAngle);
            tele.addData("3 correction", correction);
            tele.addData("is_moving drive", is_moving);
            tele.addData("LF encoder","position= %d", theHardwareMap11691.LF.getCurrentPosition());
            tele.addData("RF encoder","position= %d", theHardwareMap11691.RF.getCurrentPosition());
            tele.addData("LR encoder","position= %d", theHardwareMap11691.LR.getCurrentPosition());
            tele.addData("RR encoder","position= %d", theHardwareMap11691.RR.getCurrentPosition());
            tele.addData("Runtime",runtime.time());
            tele.addData("Timeout", timeoutT);
            tele.update();

        }

        theHardwareMap11691.LF.setPower(0);
        theHardwareMap11691.RF.setPower(0);
        theHardwareMap11691.LR.setPower(0);
        theHardwareMap11691.RR.setPower(0);

        theHardwareMap11691.LF.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);
        theHardwareMap11691.RF.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);
        theHardwareMap11691.LR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);
        theHardwareMap11691.RR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);
    }

    // Basic Drive - turns motors to a set speed
    public void basicDrive (double speeda)    {

        theHardwareMap11691.LF.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);
        theHardwareMap11691.RF.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);
        theHardwareMap11691.LR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);
        theHardwareMap11691.RR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);

        theHardwareMap11691.LF.setPower(speeda);
        theHardwareMap11691.RF.setPower(speeda);
        theHardwareMap11691.LR.setPower(speeda);
        theHardwareMap11691.RR.setPower(speeda);
    }

    public void DriveByBumperSwitches (double speeda, double timeout)    {

        theHardwareMap11691.LF.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);
        theHardwareMap11691.RF.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);
        theHardwareMap11691.LR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);
        theHardwareMap11691.RR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);

        theHardwareMap11691.LF.setPower(-speeda);
        theHardwareMap11691.RF.setPower(-speeda);
        theHardwareMap11691.LR.setPower(-speeda);
        theHardwareMap11691.RR.setPower(-speeda);

        ElapsedTime timeoutTimer = new ElapsedTime();
        timeoutTimer.reset();

        while((timeoutTimer.seconds() < timeout) &&
                (theHardwareMap11691.LHFoundBumper.isPressed() == false) &&
                (theHardwareMap11691.RHFoundBumper.isPressed() == false)) {

        }
        theHardwareMap11691.LF.setPower(0);
        theHardwareMap11691.RF.setPower(0);
        theHardwareMap11691.LR.setPower(0);
        theHardwareMap11691.RR.setPower(0);
    }

    public void displayPositionTolerance(Telemetry tele)
    {
        int tol = ((DcMotorEx)(theHardwareMap11691.LF)).getTargetPositionTolerance();
        tele.addData("LF ","position tol= %d", tol);

        tol = ((DcMotorEx)(theHardwareMap11691.RF)).getTargetPositionTolerance();
        tele.addData("RF ","position tol= %d", tol);

        tol = ((DcMotorEx)(theHardwareMap11691.LR)).getTargetPositionTolerance();
        tele.addData("LR ","position tol= %d", tol);

        tol = ((DcMotorEx)(theHardwareMap11691.RR)).getTargetPositionTolerance();
        tele.addData("RR ","position tol= %d", tol);
        tele.update();
    }

    public void setPositionTolerance(int tolerance)
    {
        ((DcMotorEx)(theHardwareMap11691.LF)).setTargetPositionTolerance(tolerance);
        ((DcMotorEx)(theHardwareMap11691.RF)).setTargetPositionTolerance(tolerance);
        ((DcMotorEx)(theHardwareMap11691.LR)).setTargetPositionTolerance(tolerance);
        ((DcMotorEx)(theHardwareMap11691.RR)).setTargetPositionTolerance(tolerance);
    }

    private double checkDirection(double angle)
    {
        double correction;

        angle = angle - getAngle();

        if (angle == 0)
            correction = 0;             // no adjustment.
        else
            correction = -angle;        // reverse sign of angle for correction.

        correction = correction * GlobalSettings11691.ImuCorrectionFactor;

        return correction;
    }

}
