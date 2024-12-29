package org.firstinspires.ftc.teamcode.Test.CommandTests;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Test.CommandTests.SubsystemTests.RightElbow;

public class CommandTest extends CommandBase {

    RightElbow rightElbow;
    boolean motorActive = false;

    public CommandTest(RightElbow rightElbow) {
        this.rightElbow = rightElbow;
        addRequirements(rightElbow);
    }

    @Override
    public void initialize() {
        if (motorActive) {
            rightElbow.setPower(0);
            motorActive = false;
        }
        else {
            rightElbow.setPower(0.5);
            motorActive = true;
        }

    }
    @Override
    public boolean isFinished() {
        return true;
    }

}
