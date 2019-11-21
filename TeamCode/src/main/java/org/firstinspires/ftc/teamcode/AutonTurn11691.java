package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;


public class AutonTurn11691 extends BaseAutonIMU{

    static double   ANGLE_TOL           = 0.5;
    public boolean  is_moving           = false;
    private double  motorSetPoint       = 0;
    public double   actualangle;
    public double   error;
    ElapsedTime     runtime;
    public double initialangle;
    public double   targetAngle;
    public double   targetSpeed;
    public double   targetDeltaSpeed;
    public double   timeout;
    double  power = .30, correction;
    Telemetry telemetry;


    public AutonTurn11691(HardwareMap11691 HMap/*, LinearOpMode opMode*/){
        super(HMap);

        runtime         = new ElapsedTime();
        initialangle     = getAbsoluteHeading();
        // telemetry=opMode.telemetry;

        //set direction for each motor
        theHardwareMap11691.LR.setDirection(DcMotor.Direction.REVERSE);
        theHardwareMap11691.LF.setDirection(DcMotor.Direction.REVERSE);
        theHardwareMap11691.RR.setDirection(DcMotor.Direction.FORWARD);
        theHardwareMap11691.RF.setDirection(DcMotor.Direction.FORWARD);
    }

    public void AutonTurn (double Angle, double speed, double timeoute, LinearOpMode theOpMode) {
        is_moving=true;
        targetAngle = Angle;
        targetSpeed = speed;
        timeout = timeoute;
        telemetry = theOpMode.telemetry;
        telemetry.addData("T_angle;",targetAngle);
        telemetry.update();
        gotoPosition(0.15,theOpMode);
    }
    public void AutonTurn_HighPowerAtEnd (double Angle, double speed, double timeoute, LinearOpMode theOpMode) {
        AutonTurn_HighPowerAtEnd (Angle,  speed, 0,  timeoute,  theOpMode);
    }

    public void AutonTurn_HighPowerAtEnd (double Angle, double speed, double deltaSpeed, double timeoute, LinearOpMode theOpMode) {
        is_moving=true;
        targetAngle = Angle;
        targetSpeed = speed;
        targetDeltaSpeed = deltaSpeed;
        timeout = timeoute;
        telemetry = theOpMode.telemetry;
        telemetry.addData("T_angle;",targetAngle);
        telemetry.update();
        gotoPosition(0.25,theOpMode);
    }

    public void gotoPosition(double powerForFinalAdjust, LinearOpMode theOpMode) {
        actualangle     = getAbsoluteHeading();

        error = targetAngle - actualangle;

        ElapsedTime rampTimer = new ElapsedTime();
        rampTimer.reset();
        double time = runtime.time();
        while((Math.abs(error) > 3) && (runtime.time() - time < timeout)
                && !theOpMode.isStopRequested() && theOpMode.opModeIsActive())
        {
            actualangle = getAbsoluteHeading();
            error = targetAngle - actualangle;

            telemetry.addData("Target Angle","error= %.2f", error);
            telemetry.addData("Target Angle","value= %.2f", targetAngle);
            telemetry.addData("Actual Angle","value= %.2f", actualangle);
            telemetry.update();

            double rampedTargetSpeed = targetSpeed;
            double rampedTargetDeltaSpeed = targetDeltaSpeed;

            if(rampTimer.seconds() <= GlobalSettings11691.rotationRampTimeInSec) {
                rampedTargetSpeed = targetSpeed * (rampTimer.seconds() / GlobalSettings11691.rotationRampTimeInSec);
                rampedTargetSpeed = Range.clip(rampedTargetSpeed,rampedTargetSpeed,targetSpeed);

                rampedTargetDeltaSpeed = targetDeltaSpeed * (rampTimer.seconds() / GlobalSettings11691.rotationRampTimeInSec);
                rampedTargetDeltaSpeed = Range.clip(rampedTargetDeltaSpeed,rampedTargetDeltaSpeed,targetDeltaSpeed);
            }

            if (error < 0) {
                rotate(rampedTargetSpeed, rampedTargetDeltaSpeed); //clockwise
            } else if (error > 0) {
                rotate(-rampedTargetSpeed, -rampedTargetDeltaSpeed); //counterclockwise
            }
        }

        rotate(0,0);

        //waitStep(0.5, theOpMode);

        //todo with ramping speed into a turn, do we still need this?
        while ((Math.abs(error) > ANGLE_TOL) && (runtime.time() - time < timeout)
                && !theOpMode.isStopRequested() && theOpMode.opModeIsActive()) {

            actualangle = getAbsoluteHeading();
            error = targetAngle - actualangle;

            telemetry.addData("Target Angle", "error= %.2f", error);
            telemetry.addData("Target Angle", "value= %.2f", targetAngle);
            telemetry.addData("Actual Angle", "value= %.2f", actualangle);
            telemetry.update();


            if (error < 0) {
                rotate(powerForFinalAdjust, 0); //clockwise
            } else if (error > 0) {
                rotate(-1 * powerForFinalAdjust, 0); //counterclockwise
            }
        }

        rotate(0,0);
    }


    private void waitStep(double wait_in_sec, LinearOpMode theOpMode)
    {
        double timer = runtime.time();
        while ((runtime.time() - timer < wait_in_sec)
                && !theOpMode.isStopRequested() && theOpMode.opModeIsActive())
        {
        }
    }

    public double getAbsoluteHeading() {

        Orientation angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        double deltaAngle = angles.firstAngle - lastAngles.firstAngle;

        if (deltaAngle < -180)
            deltaAngle += 360;
        else if (deltaAngle > 180)
            deltaAngle -= 360;

        globalAngle += deltaAngle;

        lastAngles = angles;

        return globalAngle;
    }

    void rotate(double motorSetPoint, double deltaPower){
        if(Math.abs(deltaPower) < 0.0001) {
            theHardwareMap11691.LF.setPower(motorSetPoint);
            theHardwareMap11691.LR.setPower(motorSetPoint);
            theHardwareMap11691.RF.setPower(-motorSetPoint);
            theHardwareMap11691.RR.setPower(-motorSetPoint);
        }
        else {
            theHardwareMap11691.LF.setPower(motorSetPoint + deltaPower);
            theHardwareMap11691.LR.setPower(motorSetPoint + deltaPower);
            theHardwareMap11691.RF.setPower(motorSetPoint - deltaPower);
            theHardwareMap11691.RR.setPower(motorSetPoint - deltaPower);
        }
    }
}
