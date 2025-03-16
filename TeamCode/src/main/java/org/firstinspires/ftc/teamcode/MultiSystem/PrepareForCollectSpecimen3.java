package org.firstinspires.ftc.teamcode.MultiSystem;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.command.WaitUntilCommand;

import org.firstinspires.ftc.teamcode.Commands.ClawSetPose;
import org.firstinspires.ftc.teamcode.Commands.ElbowArmCommand;
import org.firstinspires.ftc.teamcode.Commands.ExtenderArmCommand;
import org.firstinspires.ftc.teamcode.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Subsystems.ClawRollRotate;
import org.firstinspires.ftc.teamcode.Subsystems.ClawUpDown;
import org.firstinspires.ftc.teamcode.Subsystems.ElbowArm;
import org.firstinspires.ftc.teamcode.Subsystems.ExtenderArm;

public class PrepareForCollectSpecimen3 extends SequentialCommandGroup {
    public PrepareForCollectSpecimen3(ExtenderArm extenderArm, ElbowArm elbowArm, ClawRollRotate clawRollRotate, ClawUpDown clawUpDown, Claw claw) {
        addCommands(
                new InstantCommand(()-> claw.SetPose(Claw.OPEN)),
                new ExtenderArmCommand(extenderArm, elbowArm,ExtenderArm.COLLECT).withTimeout(300),
                new ElbowArmCommand(elbowArm, ElbowArm.AUTO_SPECIMEN_COLLECT),
                new InstantCommand(() -> clawRollRotate.setPose(ClawRollRotate.DEFAULT), clawRollRotate),
                new InstantCommand(() -> clawUpDown.setPos(ClawUpDown.P_F_COLLECT_SPECIMEN), clawUpDown),
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
