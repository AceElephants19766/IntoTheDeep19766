package org.firstinspires.ftc.teamcode.Subsystems;

import com.acmerobotics.dashboard.FtcDashboard;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class  ClawRollRotate extends SubsystemBase {
    private Servo clawRotate;

    public static final double SCORING = 0.6549;
    public static final double COLLECTING = 1;

    public ClawRollRotate(HardwareMap hardwareMap) {
        clawRotate = hardwareMap.get(Servo.class, "clawRotate");
        clawRotate.setPosition(ClawRollRotate.COLLECTING);
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