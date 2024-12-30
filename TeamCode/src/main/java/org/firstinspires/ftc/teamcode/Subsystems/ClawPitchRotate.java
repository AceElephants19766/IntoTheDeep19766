package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Commands.HangArmForSec;

public class ClawPitchRotate extends SubsystemBase {

    public CRServo clawPitch;

    public ClawPitchRotate (HardwareMap hardwareMap){
        clawPitch = hardwareMap.get(CRServo.class , "clawPitch");
    }
    public void SetPower(double power){
        clawPitch.setPower(power);
    }
}
