package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.RunCommand;

import org.firstinspires.ftc.teamcode.Subsystems.ExtenderArm;

public class ExtenderkeepPos extends RunCommand {
    public ExtenderkeepPos(ExtenderArm extenderArm){
        super(
                ()->extenderArm.setPower(
                        extenderArm.getPidController().calculate(
                                extenderArm.getLength()
                        )
                ),
                extenderArm
        );
    }
}
