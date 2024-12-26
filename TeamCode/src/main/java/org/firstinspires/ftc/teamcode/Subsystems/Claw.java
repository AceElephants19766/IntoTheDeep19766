package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Claw  extends SubsystemBase {

    public Servo claw;

    public Claw (HardwareMap hardwareMap) {
        claw = hardwareMap.get(Servo.class, "claw");
    }
    public void SetPose (double pose){
        claw.setPosition(pose);
    }

}
