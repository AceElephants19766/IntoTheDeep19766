package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.ElbowArm;

import java.util.Calendar;

public class ElbowArmCommand extends CommandBase {
    private ElbowArm elbowArm;
    private int targetInDeg;
    private long startTime;

    public ElbowArmCommand (ElbowArm elbowArm, int targetInDeg){
        this.elbowArm = elbowArm;
        this.targetInDeg = targetInDeg;
    }

    @Override
    public void initialize() {
        elbowArm.getPidController().setSetPoint(targetInDeg);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}