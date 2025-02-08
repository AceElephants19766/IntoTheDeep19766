package org.firstinspires.ftc.teamcode.Subsystems;

import com.acmerobotics.dashboard.FtcDashboard;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ClawUpDown extends SubsystemBase {
    private Servo clawUpDown;
    public static final double COLLECT = 1;
    public static final double SCORINGBACKWARD = 0.52;
    public static final double SCORE_SPECIMEN = 0.4;
    public static final double SCORING = 0.73;

    public ClawUpDown(HardwareMap hardwareMap){
        clawUpDown = hardwareMap.get(Servo.class , "clawUpDown");
        clawUpDown.setPosition(ClawUpDown.COLLECT);
    }
    public void setPos(double pos){
        clawUpDown.setPosition(pos);
    }

    @Override
    public void periodic() {
        FtcDashboard.getInstance().getTelemetry().addData("claw Up Down pos", clawUpDown.getPosition());
        FtcDashboard.getInstance().getTelemetry().update();
    }
}