package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ClawUpDown extends SubsystemBase {
    public Servo clawUpDown;
    public static final double COLLECT = 0.35;
    public static final double SCORING = 0;

    public ClawUpDown(HardwareMap hardwareMap){
        clawUpDown = hardwareMap.get(Servo.class , "clawUpDown");
    }
    public void SetPos(double pos){
        clawUpDown.setPosition(pos);
    }
}
