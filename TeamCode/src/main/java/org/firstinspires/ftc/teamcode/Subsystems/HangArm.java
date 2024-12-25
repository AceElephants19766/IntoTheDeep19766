package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class HangArm extends SubsystemBase {

    private DcMotor motor;

    public HangArm(HardwareMap hardwareMap) {

        motor = hardwareMap.get(DcMotor.class, "motor");
    }

    public void setPower(double power) {

        motor.setPower(power);
    }
}

