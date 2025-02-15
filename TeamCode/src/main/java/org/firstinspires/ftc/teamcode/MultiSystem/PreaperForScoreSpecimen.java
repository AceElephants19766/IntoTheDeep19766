package org.firstinspires.ftc.teamcode.MultiSystem;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Commands.ElbowArmCommand;
import org.firstinspires.ftc.teamcode.Commands.ExtenderArmCommand;
import org.firstinspires.ftc.teamcode.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Subsystems.ClawRollRotate;
import org.firstinspires.ftc.teamcode.Subsystems.ClawUpDown;
import org.firstinspires.ftc.teamcode.Subsystems.ElbowArm;
import org.firstinspires.ftc.teamcode.Subsystems.ExtenderArm;

public class PreaperForScoreSpecimen extends SequentialCommandGroup {
    public PreaperForScoreSpecimen(ElbowArm elbowArm, ExtenderArm extenderArm, Claw claw, ClawRollRotate clawRollRotate, ClawUpDown clawUpDown) {
        addCommands(
                new ExtenderArmCommand(extenderArm,0),
                new InstantCommand(() -> clawUpDown.setPos(ClawUpDown.PREAPER_SCORING_BACKWARD_SPECIMEN), clawUpDown),
                new InstantCommand(() -> clawRollRotate.setPose(ClawRollRotate.SCORE_SPECIMEN),clawRollRotate),
                new ElbowArmCommand(elbowArm, ElbowArm.SCORING_SPECIMEN)
                );
        addRequirements(
                extenderArm,
                claw,
                clawRollRotate,
                clawUpDown
        );
    }
}