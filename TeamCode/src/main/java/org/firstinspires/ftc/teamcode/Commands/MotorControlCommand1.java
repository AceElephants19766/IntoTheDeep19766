package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.MotorControllTest;

public class MotorControlCommand1 extends CommandBase {

    private MotorControllTest motorControllTest;

    public MotorControlCommand1 (MotorControllTest motorControllTest){
        this.motorControllTest = motorControllTest;
        addRequirements(motorControllTest);
    }

    @Override
    public void initialize() {
        motorControllTest.setPower(0.3*(0- motorControllTest.getRevs()));
    }

    @Override
    public boolean isFinished() {
        return (motorControllTest.getRevs()) <= 0;
    }

    @Override
    public void end(boolean interrupted) {
        motorControllTest.setPower(0);
    }
}