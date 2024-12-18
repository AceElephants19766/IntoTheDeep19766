package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.Subsystems.MotorControllTest;

public class MotorControlCommand extends CommandBase {

    private MotorControllTest motorControllTest;

    public MotorControlCommand (MotorControllTest motorControllTest){
        this.motorControllTest = motorControllTest;
        addRequirements(motorControllTest);
    }

    @Override
    public void initialize() {
        motorControllTest.setPower(0.3*(1-motorControllTest.getRevs()));
    }

    @Override
    public boolean isFinished() {
        return (motorControllTest.getRevs()) > 1;
    }

    @Override
    public void end(boolean interrupted) {
        motorControllTest.setPower(0);
    }
}
