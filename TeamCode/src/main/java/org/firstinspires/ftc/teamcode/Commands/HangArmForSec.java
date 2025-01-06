package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import org.firstinspires.ftc.teamcode.Subsystems.HangArm;

public class HangArmForSec extends SequentialCommandGroup {

    public HangArmForSec(HangArm hangArm, double power, long miliSec) {
        addCommands(
                new HangArmCommand(hangArm, power),
                new WaitCommand(miliSec),
                new HangArmCommand(hangArm, 0)
        );
        addRequirements(hangArm);
    }

}
