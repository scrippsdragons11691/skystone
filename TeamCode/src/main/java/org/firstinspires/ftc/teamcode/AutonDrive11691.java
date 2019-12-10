package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class AutonDrive11691 extends BaseAutonIMU {

    boolean is_moving = false;

    double minimumMotorPower = 0;

    ElapsedTime runtime     = new ElapsedTime();
    ElapsedTime rampTimer   = new ElapsedTime();

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
    public void Auton_Straff (double dist_Straff_In, double power, double timeoutS, LinearOpMode theOpMode){


        int newStraffLeftFTarget;
        int newStraffRightFTarget;
        int newStraffLeftBTarget;
        int newStraffRightBTarget;

        theHardwareMap11691.LF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        theHardwareMap11691.RF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        theHardwareMap11691.RR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        theHardwareMap11691.LR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        // Reset the encoders
        theHardwareMap11691.LF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        theHardwareMap11691.LR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        theHardwareMap11691.RR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        theHardwareMap11691.RF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Determine new target postition, and pass to motor controller
        newStraffLeftFTarget  = (int)(-1*dist_Straff_In * GlobalSettings11691.COUNTS_PER_INCH / 0.707);
        newStraffRightFTarget = (int)(dist_Straff_In * GlobalSettings11691.COUNTS_PER_INCH / 0.707);
        newStraffLeftBTarget  = (int)(dist_Straff_In * GlobalSettings11691.COUNTS_PER_INCH / 0.707);
        newStraffRightBTarget = (int)(-1*dist_Straff_In * GlobalSettings11691.COUNTS_PER_INCH / 0.707);

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
        setMotorPowerForLinearMove(power);

        runtime.reset();
        while ((runtime.seconds() < timeoutS)
                && (theHardwareMap11691.LR.isBusy())
                && !theOpMode.isStopRequested() && theOpMode.opModeIsActive()){

            theOpMode.telemetry.addData("is_moving drive", is_moving);
            theOpMode.telemetry.addData("LF encoder","position= %d", theHardwareMap11691.LF.getCurrentPosition());
            theOpMode.telemetry.addData("RF encoder","position= %d", theHardwareMap11691.RF.getCurrentPosition());
            theOpMode.telemetry.addData("LR encoder","position= %d", theHardwareMap11691.LR.getCurrentPosition());
            theOpMode.telemetry.addData("RR encoder","position= %d", theHardwareMap11691.RR.getCurrentPosition());
            theOpMode.telemetry.addData("Runtime",runtime.time());
            theOpMode.telemetry.addData("Timeout", timeoutS);
            theOpMode.telemetry.update();

        }

        setMotorPowerForLinearMove(0);

        theHardwareMap11691.LF.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        theHardwareMap11691.RF.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        theHardwareMap11691.RR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        theHardwareMap11691.LR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public double correction=0;
    double DrivingAngle = 0;
    int newLeftFTarget;
    int remainingEncoderCountsAbsolute;
    int minRemainingEncoderCountsAbsolute;

    /**
     *
     * @param distanceInches - The distance to move. Positive means move forward, negative means move backward
     * @param power - Maximum power to apply to all motors. Power ramp up and ramp down is handled within this function
     * @param timeoutT - If this time is exceeded, control will exit this function
     * @param theOpMode - Provides access to the LinearOpMode parameters
     * @param rampDown - If true, power will be ramped down at the end of travel. Otherwise, power will remain at "power" until the very end
     * @param brakeAtEnd - If true, the motors will brake at the end. If false they will be set to float
     * @param incremental - If true, the encoders will not be reset. The target encoder count is simply incremented according by distanceInches.
     *                    This is not guaranteed to work if the direction is the opposite from the previous linear movement.
     * @param nearest90 - Set the DrivingAngle to one of -90, 0 or 90 depending which is closest to the current heading
     */
    public void encoderDriveAutonNew(double distanceInches, double power, double timeoutT, LinearOpMode theOpMode, boolean rampDown, boolean brakeAtEnd, boolean incremental, boolean nearest90){

        double rampTimeInSec = GlobalSettings11691.RampUpTime;

        double leftInches   = distanceInches;

        if( brakeAtEnd )
            SetZeroPowerBehavior( DcMotor.ZeroPowerBehavior.BRAKE);
        else
            SetZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        if(incremental) {
            newLeftFTarget += (int) (leftInches * GlobalSettings11691.COUNTS_PER_INCH);
        }
        else {
            newLeftFTarget = (int) (leftInches * GlobalSettings11691.COUNTS_PER_INCH);
            rampTimer.reset();
            StopAndResetEncoders();
        }


        SetMotorsToRunWithoutEncoders();

        // DrivingAngle is the heading at which we want to drive
        DrivingAngle = nearest90 ? Math.round(globalAngle / 90) * 90 : globalAngle;

        double effectiveEncoderCountRampDownThreshold = GlobalSettings11691.EncoderCountRampDownThreshold;

        minRemainingEncoderCountsAbsolute = 100000; // assume that we will never reach this high count

        BaseAuton.dataTracing.sendAllData();

        int minEncoderValueLatch = 3;
        runtime.reset();
        int state = 0;
        while ((runtime.seconds() < timeoutT)
                && !theOpMode.isStopRequested() && theOpMode.opModeIsActive()) {

            double rampedPower = power;
            int remainingEncoderCounts = newLeftFTarget - theHardwareMap11691.LF.getCurrentPosition();
            remainingEncoderCountsAbsolute = Math.abs(remainingEncoderCounts);

            if(remainingEncoderCountsAbsolute < minRemainingEncoderCountsAbsolute) {
                minRemainingEncoderCountsAbsolute = remainingEncoderCountsAbsolute;
                if(minEncoderValueLatch >0)
                    minEncoderValueLatch--;
            }


            if((remainingEncoderCountsAbsolute < 20) ||
                    ((minEncoderValueLatch == 0) && (remainingEncoderCountsAbsolute > minRemainingEncoderCountsAbsolute))) {
                break;
            }

            if ( rampDown && (remainingEncoderCountsAbsolute < effectiveEncoderCountRampDownThreshold)) {
                state = 2;
                // If we shut down motor power suddenly, the robot will slide. Therefore ramp power down
                rampedPower = Range.clip(power * (((double) remainingEncoderCountsAbsolute) / effectiveEncoderCountRampDownThreshold), GlobalSettings11691.LinearRampDownMinimumPower, power);
            } else {
                state = 1;
                // The slower the robot speed is, delay the start of the power ramp down so that we do not waste time
                double rampDownStartModifier = ((DcMotorEx) theHardwareMap11691.LF).getVelocity(AngleUnit.RADIANS) / GlobalSettings11691.topWheelAngularVelocity_radPerSec;
                rampDownStartModifier = 1;
                effectiveEncoderCountRampDownThreshold = Math.abs(GlobalSettings11691.EncoderCountRampDownThreshold * rampDownStartModifier);

                if (rampTimer.seconds() <= rampTimeInSec) {
                    // Spinning the wheels introduces an error when driving using encoders. Therefore ramp the wheel power up so that the wheels do not spin.
                    rampedPower = Range.clip(power * (rampTimer.seconds() / rampTimeInSec), minimumMotorPower, power);
                }
            }

            if( distanceInches < 0 )
                rampedPower *= -1;

            // Use gyro to drive in a straight line.
            correction = checkDirection(DrivingAngle);

            setMotorPowerForLinearMove(rampedPower - correction, rampedPower + correction,
                                       rampedPower + correction,rampedPower - correction);

            BaseAuton.dataTracing.sendAllData();

            if( GlobalSettings11691.SendTelemetry) {
                theOpMode.telemetry.addData("Rem encoder counts", "%d", remainingEncoderCounts);
                theOpMode.telemetry.addData("State", "%d", state);
                theOpMode.telemetry.addData("Power", "%.3f", rampedPower);
/*                theOpMode.telemetry.addData("1 imu heading", lastAngles.firstAngle);
                theOpMode.telemetry.addData("2 global heading", globalAngle);
                theOpMode.telemetry.addData("3 correction", correction);
                theOpMode.telemetry.addData("is_moving drive", is_moving);
                theOpMode.telemetry.addData("LF encoder", "position= %d", theHardwareMap11691.LF.getCurrentPosition());
                theOpMode.telemetry.addData("RF encoder", "position= %d", theHardwareMap11691.RF.getCurrentPosition());
                theOpMode.telemetry.addData("LR encoder", "position= %d", theHardwareMap11691.LR.getCurrentPosition());
                theOpMode.telemetry.addData("RR encoder", "position= %d", theHardwareMap11691.RR.getCurrentPosition());
                theOpMode.telemetry.addData("Runtime", runtime.time());
                theOpMode.telemetry.addData("Timeout", timeoutT);*/
                theOpMode.telemetry.update();
            }

        }

        if( rampDown || theOpMode.isStopRequested() || !theOpMode.opModeIsActive() ) {
            setMotorPowerForLinearMove(0);
        }

        BaseAuton.dataTracing.sendAllData();
    }

    // Basic Drive - turns motors to a set speed
    public void basicDrive (double power)    {

        theHardwareMap11691.LF.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        theHardwareMap11691.RF.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        theHardwareMap11691.LR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        theHardwareMap11691.RR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        setMotorPowerForLinearMove(power);
    }

    public void DriveByBumperSwitches (double power, double timeout, LinearOpMode theOpMode)    {

        theHardwareMap11691.LF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        theHardwareMap11691.RF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        theHardwareMap11691.RR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        theHardwareMap11691.LR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        theHardwareMap11691.LF.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        theHardwareMap11691.RF.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        theHardwareMap11691.LR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        theHardwareMap11691.RR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        setMotorPowerForLinearMove(-power);

        ElapsedTime timeoutTimer = new ElapsedTime();
        timeoutTimer.reset();

        while((timeoutTimer.seconds() < timeout)
                && (theHardwareMap11691.LHFoundBumper.isPressed() == false)
                && (theHardwareMap11691.RHFoundBumper.isPressed() == false)
                && !theOpMode.isStopRequested() && theOpMode.opModeIsActive()) {

        }

        setMotorPowerForLinearMove(0);
    }

    double distanceSensorRH = 0;
    double distanceSensorLH = 0;
    public void DriveByDistanceSensors (double power, double distance, double timeout, LinearOpMode theOpMode)    {

        // the minimum distance that the sensor can see is 2.15 inch
        distance = Range.clip(distance, 2.3, 20);

        theHardwareMap11691.LF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        theHardwareMap11691.RF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        theHardwareMap11691.RR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        theHardwareMap11691.LR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        theHardwareMap11691.LF.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        theHardwareMap11691.RF.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        theHardwareMap11691.LR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        theHardwareMap11691.RR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        setMotorPowerForLinearMove(-power);

        ElapsedTime timeoutTimer = new ElapsedTime();
        timeoutTimer.reset();

        do{
            distanceSensorRH = getDistanceSensorValue_RH();
            distanceSensorLH = getDistanceSensorValue_LH();
            BaseAuton.dataTracing.sendAllData();

            if( GlobalSettings11691.SendTelemetry) {
                theOpMode.telemetry.addData("RH distance", "%.3f", distanceSensorRH);
                theOpMode.telemetry.addData("LH distance", "%.3f", distanceSensorLH);

                theOpMode.telemetry.update();
            }
        }while((timeoutTimer.seconds() < timeout)
                && (distanceSensorRH >= distance)
                && (distanceSensorLH >= distance)
                && !theOpMode.isStopRequested() && theOpMode.opModeIsActive());

        setMotorPowerForLinearMove(0);
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

    double GlobalAngle;
    private double checkDirection(double angle)
    {
        double correction;

        GlobalAngle = getAngle();
        angle = angle - GlobalAngle;

        correction = angle * GlobalSettings11691.ImuCorrectionFactor;

        return correction;
    }

    private double getDistanceSensorValue_RH()
    {
        double distance = theHardwareMap11691.autonSensorDRH.getDistance(DistanceUnit.INCH);
        if(distance < 12.0)
            return distance;

        return 1000.0;
    }

    private double getDistanceSensorValue_LH()
    {
        double distance = theHardwareMap11691.autonSensorDLH.getDistance(DistanceUnit.INCH);
        if(distance < 12.0)
            return distance;

        return 1000.0;
    }


}

//todo implement heading correction for straffing

