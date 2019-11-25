package org.firstinspires.ftc.teamcode;

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
            sendWheelPower();
            sendAngularData();
        }
    }
    public void sendWheelEncoders()
    {
        if( socketIsOpen) {
            PrintWriter writer = new PrintWriter(output, true);
            writer.format("$$wheelEncoders:%.3f:%d:%d:%d:%d##", /*i,i+1,i+2,i+3*/
                    theAuton.runtime.time(),
                    theAuton.hMap.LF.getCurrentPosition(),
                    theAuton.hMap.RF.getCurrentPosition(),
                    theAuton.hMap.LR.getCurrentPosition(),
                    theAuton.hMap.RR.getCurrentPosition()
            );
        }
    }

    public void sendWheelPower()
    {
        if(socketIsOpen) {
            PrintWriter writer = new PrintWriter(output, true);
            writer.format("$$wheelPower:%.3f:%.2f:%.2f:%.2f:%.2f##", /*i,i+1,i+2,i+3*/
                    theAuton.runtime.time(),
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
                    theAuton.autonTurn.actualangle/90, // normalize to 90 == 1, -90 == -1
                    theAuton.autonTurn.targetAngle/90,
                    theAuton.autonTurn.error/90);
        }
    }
}
