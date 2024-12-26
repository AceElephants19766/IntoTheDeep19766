package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.logging.Handler;
public class RightElbow extends SubsystemBase {

    private DcMotor rightElbow;

    public RightElbow (HardwareMap hardwareMap){
        rightElbow = hardwareMap.get(DcMotor.class, "rightElbow");
    }

    public void setPower (double power){
            rightElbow.setPower(power);
    }
    public void stop (){
       setPower(0);
    }
}
