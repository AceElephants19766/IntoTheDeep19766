package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class CollectingArmServo extends SubsystemBase {
    private Servo elbowArm;

    public CollectingArmServo(HardwareMap hardwareMap) {
        elbowArm = hardwareMap.get(Servo.class, "elbowArm");
    }
    public void setPos (double pos)
    {
        elbowArm.setPosition(pos);
    }
}