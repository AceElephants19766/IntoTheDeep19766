package org.firstinspires.ftc.teamcode.Subsystems;

import com.acmerobotics.dashboard.FtcDashboard;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class  ClawRollRotate extends SubsystemBase {
    private Servo clawRotate;

    public static final double DEFAULT = 0.65;
    public static final double SPECIAL = 0.32;

    public ClawRollRotate(HardwareMap hardwareMap) {
        clawRotate = hardwareMap.get(Servo.class, "clawRotate");
        clawRotate.setPosition(ClawRollRotate.DEFAULT);
    }

    public void setPose(double pose){
        clawRotate.setPosition(pose);
    }

    @Override
    public void periodic() {
        FtcDashboard.getInstance().getTelemetry().addData("claw roll rotate pos", clawRotate.getPosition());
        FtcDashboard.getInstance().getTelemetry().update();
    }
}