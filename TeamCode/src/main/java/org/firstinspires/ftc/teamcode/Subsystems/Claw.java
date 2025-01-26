package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Claw  extends SubsystemBase {

    private Servo claw;
    public static final double OPEN = 0.66;
    public static final double CLOSE = 0.84;

    public Claw (HardwareMap hardwareMap) {
        claw = hardwareMap.get(Servo.class, "claw");
        SetPose(Claw.CLOSE);
    }
    public void SetPose (double pose){
        claw.setPosition(pose);
    }
}
