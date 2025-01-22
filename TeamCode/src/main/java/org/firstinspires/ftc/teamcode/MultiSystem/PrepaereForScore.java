package org.firstinspires.ftc.teamcode.MultiSystem;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitUntilCommand;

import org.firstinspires.ftc.teamcode.Commands.ElbowArmCommand;
import org.firstinspires.ftc.teamcode.Commands.ExtenderArmCommand;
import org.firstinspires.ftc.teamcode.Subsystems.ClawRollRotate;
import org.firstinspires.ftc.teamcode.Subsystems.ClawUpDown;
import org.firstinspires.ftc.teamcode.Subsystems.ElbowArm;
import org.firstinspires.ftc.teamcode.Subsystems.ExtenderArm;

public class PrepaereForScore extends SequentialCommandGroup {
    public PrepaereForScore (ElbowArm elbowArm, ExtenderArm extenderArm, ClawUpDown clawUpDown, ClawRollRotate clawRollRotate){
        addCommands(
                new ElbowArmCommand(elbowArm,ElbowArm.SCORING),
                new WaitUntilCommand(() -> elbowArm.getPidController().getPositionError() < 40),
                new ExtenderArmCommand(extenderArm,42).withTimeout(2000),
                new InstantCommand(() -> clawUpDown.setPos(ClawUpDown.SCORINGBACKWARD)),
                new InstantCommand(()-> clawRollRotate.setPose(ClawRollRotate.COLLECTING))
        );
        addRequirements(
                extenderArm,
                clawUpDown,
                clawRollRotate
        );
    }
}