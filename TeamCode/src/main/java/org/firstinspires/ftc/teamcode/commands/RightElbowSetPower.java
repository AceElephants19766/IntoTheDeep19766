package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Subsystems.RightElbow;

public class RightElbowSetPower extends CommandBase {

    public RightElbow rightElbow;

    public RightElbowSetPower (RightElbow rightElbow){
        this.rightElbow = rightElbow;
    }

    @Override
    public void initialize() {
        rightElbow.setPower(0.5);
    }

    @Override
    public void execute() {

    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void end(boolean interrupted) {

    }
}
