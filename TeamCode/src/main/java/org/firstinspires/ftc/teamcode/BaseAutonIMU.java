package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class BaseAutonIMU {

    public static BNO055IMU imu;
    public static Orientation lastAngles = new Orientation();
    public static double globalAngle;

    public HardwareMap11691 theHardwareMap11691;

    // Constructor
    public BaseAutonIMU(HardwareMap11691 hMap)
    {
        theHardwareMap11691 = hMap;

        //todo DM : I do not think that the following parameters are used. IMU is initialized in the hardware map class
        // IMU parameter initialization
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();

        parameters.mode                = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled      = false;

        // Retrieve and initialize the IMU. We expect the IMU to be attached to an I2C port
        // on a Core Device Interface Module, configured to be a sensor of type "AdaFruit IMU",
        // and named "imu".
        imu = theHardwareMap11691.imu;
        resetAngle();
    }

    public void resetAngle()
    {
        lastAngles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        globalAngle = 0;
    }

    public double getAngle()
    {
        // We experimentally determined the Z axis is the axis we want to use for heading angle.
        // We have to process the angle because the imu works in euler angles so the Z axis is
        // returned as 0 to +180 or 0 to -180 rolling back to -179 or +179 when rotation passes
        // 180 degrees. We detect this transition and track the total cumulative angle of rotation.

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

    protected void SetZeroPowerBehavior(DcMotor.ZeroPowerBehavior behavior)
    {
        theHardwareMap11691.LF.setZeroPowerBehavior(behavior);
        theHardwareMap11691.RF.setZeroPowerBehavior(behavior);
        theHardwareMap11691.RR.setZeroPowerBehavior(behavior);
        theHardwareMap11691.LR.setZeroPowerBehavior(behavior);
    }

    protected void StopAndResetEncoders()
    {
        theHardwareMap11691.LF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        theHardwareMap11691.LR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        theHardwareMap11691.RR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        theHardwareMap11691.RF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    protected void SetMotorsToRunWithoutEncoders()
    {
        theHardwareMap11691.LF.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        theHardwareMap11691.LR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        theHardwareMap11691.RR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        theHardwareMap11691.RF.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
}
