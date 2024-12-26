package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class HangArm extends SubsystemBase {

    private DcMotor hangArm;

    public HangArm(HardwareMap hardwareMap) {

        hangArm = hardwareMap.get(DcMotor.class, "hangArm");
    }

    public void setPower(double power) {

        hangArm.setPower(power);
    }
}

