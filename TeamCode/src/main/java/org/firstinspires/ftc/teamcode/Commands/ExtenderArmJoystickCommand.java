package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.PerpetualCommand;
import com.arcrobotics.ftclib.command.RunCommand;

import org.firstinspires.ftc.teamcode.Subsystems.ExtenderArm;

import java.util.function.DoubleSupplier;

public class ExtenderArmJoystickCommand  extends PerpetualCommand {
    public ExtenderArmJoystickCommand (ExtenderArm extenderArm, DoubleSupplier currentLength , int jump){
        super(
                new ExtenderArmCommand(extenderArm, (int) (currentLength.getAsDouble() + jump))
        );
    }
}