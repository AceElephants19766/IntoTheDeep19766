package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

public class HangArm extends SubsystemBase {
    private DcMotor hangArm;
    private TouchSensor touchSensor;

    public HangArm(HardwareMap hardwareMap) {
        hangArm = hardwareMap.get(DcMotor.class, "hangArm");

//        touchSensor = hardwareMap.get(TouchSensor.class, "HangTouchSen");
    }

    public void setPower(double power) {
        hangArm.setPower(power);
    }

//    public boolean isPressed() {
//        return touchSensor.isPressed();
//    }
}