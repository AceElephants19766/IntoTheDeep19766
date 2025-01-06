package org.firstinspires.ftc.teamcode.Test.SubsystemTests;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class RightElbow extends SubsystemBase {

    private DcMotor rightElbow;

    public RightElbow (HardwareMap hardwareMap){
        rightElbow = hardwareMap.get(DcMotor.class, "rightElbow");
    }

    public void setPower (double power){
            rightElbow.setPower(power);
    }
    public void stop (){
       setPower(0);
    }
}
