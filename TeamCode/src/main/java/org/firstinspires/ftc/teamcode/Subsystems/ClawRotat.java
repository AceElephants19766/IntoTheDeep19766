package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ClawRotat extends SubsystemBase {
    public Servo clawRotate;

    public ClawRotat (HardwareMap hardwareMap) {
        clawRotate = hardwareMap.get(Servo.class, "clawRotate");
    }

    public void SetPose (double pose){
        clawRotate.setPosition(pose);
    }
}
