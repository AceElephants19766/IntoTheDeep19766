package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Commands.HangArmForSec;

public class ClawPitchRotate extends SubsystemBase {

    public Servo clawPitch;

    public ClawPitchRotate (HardwareMap hardwareMap){
        clawPitch = hardwareMap.get(Servo.class , "clawUpDown");
    }
    public void setPos(double power){
        clawPitch.setPosition(power);
    }
}
