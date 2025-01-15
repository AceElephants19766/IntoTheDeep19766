package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.PerpetualCommand;
import com.arcrobotics.ftclib.command.RunCommand;

import org.firstinspires.ftc.teamcode.Subsystems.ExtenderArm;

import java.util.function.DoubleSupplier;

public class ExtenderArmJoystickCommand extends CommandBase {
    public ExtenderArm extenderArm;
    public double power;
    public ExtenderArmJoystickCommand (ExtenderArm extenderArm, double power){
        this.extenderArm = extenderArm;
        this.power = power;
        addRequirements(extenderArm);
    }
    @Override
    public void execute() {
        extenderArm.setPower(power);
    }

    @Override
    public void end(boolean interrupted) {
        extenderArm.setPower(0);
    }
}