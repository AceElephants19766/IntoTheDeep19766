package org.firstinspires.ftc.teamcode.Test.CommandTests.SubsystemTests;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class LeftElevator extends SubsystemBase {

    private DcMotor leftElevator;

    public LeftElevator (HardwareMap hardwareMap){

        leftElevator = hardwareMap.get(DcMotor.class, "left_elevator");
    }
    public void setPower (double power){

        leftElevator.setPower(power);
    }

}
