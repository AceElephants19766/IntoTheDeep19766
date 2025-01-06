package org.firstinspires.ftc.teamcode.Test.SubsystemTests;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class RightElevator extends SubsystemBase {

    private DcMotor rightElevator;

    public RightElevator (HardwareMap hardwareMap){

        rightElevator = hardwareMap.get(DcMotor.class, "right_elevator");
    }
    public void setPower (double power){
        rightElevator.setPower(power);
    }
}
