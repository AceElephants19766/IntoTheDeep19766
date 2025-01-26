package org.firstinspires.ftc.teamcode.MultiSystem;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.command.WaitUntilCommand;

import org.firstinspires.ftc.teamcode.Commands.ElbowArmCommand;
import org.firstinspires.ftc.teamcode.Commands.ExtenderArmCommand;
import org.firstinspires.ftc.teamcode.Subsystems.ClawRollRotate;
import org.firstinspires.ftc.teamcode.Subsystems.ClawUpDown;
import org.firstinspires.ftc.teamcode.Subsystems.ElbowArm;
import org.firstinspires.ftc.teamcode.Subsystems.ExtenderArm;

public class PreaperForScoreSpecimen extends SequentialCommandGroup {
    public PreaperForScoreSpecimen(ElbowArm elbowArm, ExtenderArm extenderArm, ClawUpDown clawUpDown, ClawRollRotate clawRollRotate) {
        addCommands(
                new ElbowArmCommand(elbowArm, ElbowArm.SCORING_SPECIMEN),
                new WaitCommand(400),
                new ExtenderArmCommand(extenderArm,ExtenderArm.SCORINGSPACIMEN),
                new InstantCommand(() -> clawUpDown.setPos(ClawUpDown.SCORING)),
                new InstantCommand(() -> clawRollRotate.setPose(ClawRollRotate.DEFAULT))
        );
        addRequirements(
                extenderArm,
                clawUpDown,
                clawRollRotate
        );
    }
}
