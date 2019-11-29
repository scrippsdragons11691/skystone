package org.firstinspires.ftc.teamcode;


public class GlobalSettings11691 {

    static String dataServerHostIP = "192.168.49.117";
    static int dataServerHostPort = 9876;

    //Rev Hex HD Motor 2240 counts per rotation
    static double COUNTS_PER_MOTOR_REV =      1120; //20 - 537.6, 40 - 1120, 60- 1680 (cpr) Gear Neverest Motor Encoder
    static double DRIVE_GEAR_REDUTION =       2.2; // This is < 1.0 if geared UP
    static double WHEEL_DIAMETER_INCHES =     3.0; // For figuring circumference
    static double COUNTS_PER_INCH =(COUNTS_PER_MOTOR_REV / DRIVE_GEAR_REDUTION)/(WHEEL_DIAMETER_INCHES*3.1415);

    static double pushHome = 1;
    static double pushBlock = 0.55;
    static double pushEject = 0;
    static double armHome = 0.03;
    static double armNinety = 0.38;
    static double armOneEighty = 0.75;
    static double foundationHome = 0.75;
    static double foundationHook = 0.2;
    static double intakeInpos = 1.0;
    static double intakeOutpos = -1.0;
    static double intakeStop = 0;
    static double grabRelease = 0.95;
    static double grabBlock = 0.4;
    static double skystoneSaturationValue = 0.5;


    static double RightSKGrabber_homePosition = 1;
    static double RightSKGrabber_GrabPosition = 0.65;
    static double RightSKGrabber_InitPosition = RightSKGrabber_homePosition;

    static double LeftSKGrabber_homePosition = 0;
    static double LeftSKGrabber_GrabPosition = 0.77;
    static double LeftSKGrabber_InitPosition = LeftSKGrabber_homePosition;

    // The gain value determines how sensitive the correction is to direction changes.
    // You will have to experiment with your robot to get small smooth direction changes
    // to stay on a straight line.
    static double ImuCorrectionFactor = 0.075;

    // Robot linear speed ramp up settings
    static double RampUpTime = 0.6;

    // Robot linear speed ramp down settings
    static int EncoderCountRampDownThreshold = (int)(35*COUNTS_PER_INCH);
    static double topWheelAngularVelocity_radPerSec = 15; // the highest wheel ang velocity that the robot reaches at full power
    static double LinearRampDownMinimumPower = 0.2; // Do not ramp down speed to a smaller value so as to not get stuck due to friction

    // Settings for rotating
    static double rotationRampTimeInSec = 0.5;
    static double powerRampdownStartAngle = 20;
    static double RotationRampDownMinimumPower = 0.2; // Do not ramp down speed to a smaller value so as to not get stuck due to friction

    static double OneTileLength_inch = 23 + (3.0/8.0);
}

/* ============================================ */
//                   NOTES
/* ============================================ */
// 1) With power = 1 at each wheel, the top speed is 15 rad/s (11/27/2019)
//
//
//
//
//
//
/* ============================================ */
