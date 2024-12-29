package org.firstinspires.ftc.teamcode.Test.CommandTests.SubsystemTests;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class LeftElbow extends SubsystemBase {

    private DcMotor leftElbow;

    public LeftElbow (HardwareMap hardwareMap){
        leftElbow = hardwareMap.get(DcMotor.class, "left_up");
    }
    public void setPower (double power){

        leftElbow.setPower(power);
    }
}