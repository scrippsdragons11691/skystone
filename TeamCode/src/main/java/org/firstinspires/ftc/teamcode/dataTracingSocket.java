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
            sendWheelEncoders();
            sendWheelAngularVelocity();
            sendWheelPower();
            sendAngularData();
        }
    }
    public void sendWheelEncoders()
    {
        if( socketIsOpen) {
            PrintWriter writer = new PrintWriter(output, true);
            writer.format("$$wheelEncoders:%.3f:%d:%d:%d:%d##",
                    theAuton.runtime.time(),
                    /*i,i+1,i+2,i+3*/
                    theAuton.hMap.LF.getCurrentPosition(),
                    theAuton.hMap.RF.getCurrentPosition(),
                    theAuton.hMap.LR.getCurrentPosition(),
                    theAuton.hMap.RR.getCurrentPosition()
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
            writer.format("$$wheelPower:%.3f:%.2f:%.2f:%.2f:%.2f##",
                    theAuton.runtime.time(),
                    /*i,i+1,i+2,i+3*/
                    theAuton.hMap.LF.getPower(),
                    theAuton.hMap.RF.getPower(),
                    theAuton.hMap.LR.getPower(),
                    theAuton.hMap.RR.getPower()
            );
        }
    }


    public void sendAngularData()
    {
        if(socketIsOpen) {
            PrintWriter writer = new PrintWriter(output, true);
            //  writer.format("$$Angular:%.2f:%.2f:%.2f##", i,i+1,i+2);
            writer.format("$$Angular:%.3f:%.2f:%.2f:%.2f##",
                    theAuton.runtime.time(),
                    /*i,i+1,i+2*/
                    theAuton.autonTurn.actualangle/90, // normalize to 90 == 1, -90 == -1
                    theAuton.autonTurn.targetAngle/90,
                    theAuton.autonTurn.error/90
            );
        }
    }
}
