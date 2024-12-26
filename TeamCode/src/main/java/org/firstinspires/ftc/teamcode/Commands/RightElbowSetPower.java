package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.RightElbow;

public class RightElbowSetPower extends CommandBase {

    RightElbow rightElbow;
    double power;

    public RightElbowSetPower (RightElbow rightElbow, double power){
        this.rightElbow = rightElbow;
        this.power = power;
        addRequirements(rightElbow);
    }

    @Override
    public void initialize() {
        rightElbow.setPower(power);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}