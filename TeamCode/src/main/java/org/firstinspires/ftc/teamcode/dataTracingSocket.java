package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import java.io.PrintWriter;

public class dataTracingSocket extends baseTracingSocket {

    public BaseAuton theAuton;

    dataTracingSocket()
    {
    }

    public void sendAllData()
    {
        if(socketIsOpen) {
            //sendWheelEncoders();
            //sendWheelAngularVelocity();
            //sendWheelPower();
            //sendAngularData();
            sendDistanceSensorData();
        }
    }
    public void sendWheelEncoders()
    {
        if( socketIsOpen) {
            PrintWriter writer = new PrintWriter(output, true);
            writer.format("$$wheelEncoders:%.3f:%d:%d:%d:%d:%d:%d:%d##",
                    theAuton.runtime.time(),
                    /*i,i+1,i+2,i+3*/
                    theAuton.hMap.LF.getCurrentPosition(),
                    theAuton.hMap.RF.getCurrentPosition(),
                    theAuton.hMap.LR.getCurrentPosition(),
                    theAuton.hMap.RR.getCurrentPosition(),
                    theAuton.autonDrive.newLeftFTarget,
                    theAuton.autonDrive.remainingEncoderCountsAbsolute,
                    theAuton.autonDrive.minRemainingEncoderCountsAbsolute
            );
        }
    }

    public void sendWheelAngularVelocity()
    {
        if( socketIsOpen) {
            PrintWriter writer = new PrintWriter(output, true);
            writer.format("$$wheelAngVel:%.3f:%.2f:%.2f:%.2f:%.2f##",
                    theAuton.runtime.time(),
                    /*i,i+1,i+2,i+3*/
                    ((DcMotorEx)theAuton.hMap.LF).getVelocity(AngleUnit.RADIANS),
                    ((DcMotorEx)theAuton.hMap.RF).getVelocity(AngleUnit.RADIANS),
                    ((DcMotorEx)theAuton.hMap.LR).getVelocity(AngleUnit.RADIANS),
                    ((DcMotorEx)theAuton.hMap.RR).getVelocity(AngleUnit.RADIANS)
            );
        }
    }

    public void sendWheelPower()
    {
        if(socketIsOpen) {
            PrintWriter writer = new PrintWriter(output, true);
            writer.format("$$wheelPower:%.3f:%.2f:%.2f:%.2f:%.2f:%.2f##",
                    theAuton.runtime.time(),
                    /*i,i+1,i+2,i+3*/
                    theAuton.hMap.LF.getPower(),
                    theAuton.hMap.RF.getPower(),
                    theAuton.hMap.LR.getPower(),
                    theAuton.hMap.RR.getPower(),
                    theAuton.autonDrive.correction
            );
        }
    }

    public void sendDistanceSensorData()
    {
        if(socketIsOpen) {
            PrintWriter writer = new PrintWriter(output, true);
            writer.format("$$DistanceSensor:%.3f:%.2f:%.2f##",
                    theAuton.runtime.time(),
                    theAuton.autonDrive.distanceSensorLH,
                    theAuton.autonDrive.distanceSensorRH
            );
        }
    }

    public void sendAngularData()
    {
        if(socketIsOpen) {
            PrintWriter writer = new PrintWriter(output, true);
            //  writer.format("$$Angular:%.2f:%.2f:%.2f##", i,i+1,i+2);
            writer.format("$$Angular:%.3f:%.2f:%.2f:%.2f:%.2f:%.2f##",
                    theAuton.runtime.time(),
                    /*i,i+1,i+2*/
                    theAuton.autonTurn.actualangle,
                    theAuton.autonTurn.targetAngle,
                    theAuton.autonTurn.error,
                    theAuton.autonDrive.DrivingAngle,
                    theAuton.autonDrive.GlobalAngle
            );
        }
    }
}
