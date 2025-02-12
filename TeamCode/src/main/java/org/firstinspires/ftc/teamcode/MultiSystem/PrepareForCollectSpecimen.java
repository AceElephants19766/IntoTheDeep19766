package org.firstinspires.ftc.teamcode.MultiSystem;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Commands.ClawSetPose;
import org.firstinspires.ftc.teamcode.Commands.ElbowArmCommand;
import org.firstinspires.ftc.teamcode.Commands.ExtenderArmCommand;
import org.firstinspires.ftc.teamcode.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Subsystems.ClawRollRotate;
import org.firstinspires.ftc.teamcode.Subsystems.ClawUpDown;
import org.firstinspires.ftc.teamcode.Subsystems.ElbowArm;
import org.firstinspires.ftc.teamcode.Subsystems.ExtenderArm;

public class PrepareForCollectSpecimen extends SequentialCommandGroup {
    public PrepareForCollectSpecimen(ExtenderArm extenderArm, ElbowArm elbowArm, ClawRollRotate clawRollRotate, ClawUpDown clawUpDown, Claw claw) {
        addCommands(
                new ExtenderArmCommand(extenderArm, ExtenderArm.COLLECT),
                new ElbowArmCommand(elbowArm, ElbowArm.SPECIMEN_COLLECT),
                new InstantCommand(() -> clawRollRotate.setPose(ClawRollRotate.DEFAULT), clawRollRotate),
                new InstantCommand(() -> clawUpDown.setPos(ClawUpDown.SCORING), clawUpDown),
                new ClawSetPose(claw, Claw.OPEN)
        );
        addRequirements(
                extenderArm,
                clawRollRotate,
                clawUpDown,
                claw

        );
    }
}
