package org.firstinspires.ftc.teamcode.Test.CommandTests.SubsystemTests;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Roller extends SubsystemBase {

    private CRServo roller;

    public Roller(HardwareMap hardwareMap) {
        roller = hardwareMap.get(CRServo.class, "roller");
    }
    public void setPower (double power)
    {
        roller.setPower(power);
    }
}
