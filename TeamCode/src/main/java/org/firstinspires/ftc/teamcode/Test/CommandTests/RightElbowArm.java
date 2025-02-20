package org.firstinspires.ftc.teamcode.Test.CommandTests;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Test.SubsystemTests.RightElbow;

public class RightElbowArm extends CommandBase {

    public RightElbow rightElbow;
    public double power;

    public RightElbowArm (RightElbow rightElbow, double power){
        this.rightElbow = rightElbow;
        this.power = power;
        addRequirements(rightElbow);
    }

    @Override
    public void initialize() {
        rightElbow.setPower(power);
    }

    @Override
    public void end(boolean interrupted) {
        rightElbow.setPower(0);
    }
}
