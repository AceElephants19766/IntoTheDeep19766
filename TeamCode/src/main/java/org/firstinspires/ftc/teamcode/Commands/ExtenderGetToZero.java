package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.command.WaitUntilCommand;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.Subsystems.ExtenderArm;

public class ExtenderGetToZero extends SequentialCommandGroup {
    public ExtenderGetToZero (ExtenderArm extenderArm){
        addCommands(
                new ExtenderArmCommand(extenderArm,2),
                new InstantCommand(() -> extenderArm.setPower(-0.5)),
                new WaitUntilCommand(() -> extenderArm.isPressed()).withTimeout(1000),
                new InstantCommand(() -> extenderArm.setPower(0)),
                new InstantCommand(() -> extenderArm.resetEncoder())
        );
        addRequirements(extenderArm);
    }

}
