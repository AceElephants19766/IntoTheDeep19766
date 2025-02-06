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
    public PreaperForScoreSpecimen(ElbowArm elbowArm,ExtenderArm extenderArm,ClawRollRotate clawRollRotate, ClawUpDown clawUpDown) {
        addCommands(
                new InstantCommand(() -> clawRollRotate.setPose(ClawUpDown.SCORINGBACKWARD), clawUpDown),
                new InstantCommand(() -> clawRollRotate.setPose(ClawRollRotate.DEFAULT)),
                new ExtenderArmCommand(extenderArm, ExtenderArm.COLLECT),
                new ElbowArmCommand(elbowArm, ElbowArm.SCORING_SPECIMEN),
                new WaitCommand(1000),
                new ExtenderArmCommand(extenderArm, ExtenderArm.SCORINGSPACIMEN)
        );
        addRequirements(
                extenderArm,
                clawRollRotate,
                clawUpDown
        );
    }
}
