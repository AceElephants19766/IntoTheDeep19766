package org.firstinspires.ftc.teamcode.MultiSystem;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.command.WaitUntilCommand;

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
                new InstantCommand(() -> claw.SetPose(Claw.CLOSE),claw),
                new WaitCommand(1000),
                new ElbowArmCommand(elbowArm, ElbowArm.SCORING_SPECIMEN),
                new InstantCommand(() -> clawUpDown.setPos(ClawUpDown.PREAPER_SCORING_BACKWARD), clawUpDown),
                new WaitCommand(1000),
                new InstantCommand(() -> clawRollRotate.setPose(ClawRollRotate.SCORE_SPECIMEN),clawRollRotate),

                new InstantCommand(() -> clawUpDown.setPos(ClawUpDown.SCORE_SPECIMEN), clawUpDown)
                );
        addRequirements(
                extenderArm,
                claw,
                clawRollRotate,
                clawUpDown
        );
    }
}