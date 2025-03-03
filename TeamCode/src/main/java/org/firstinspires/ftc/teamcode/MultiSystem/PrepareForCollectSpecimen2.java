package org.firstinspires.ftc.teamcode.MultiSystem;

import com.acmerobotics.dashboard.FtcDashboard;
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

public class PrepareForCollectSpecimen2 extends SequentialCommandGroup {
    public PrepareForCollectSpecimen2(ExtenderArm extenderArm, ElbowArm elbowArm, ClawRollRotate clawRollRotate, ClawUpDown clawUpDown, Claw claw) {
        addCommands(
                new ElbowArmCommand(elbowArm,(int)(elbowArm.getDeg()-5)),
                new InstantCommand(() -> clawRollRotate.setPose(ClawRollRotate.DEFAULT), clawRollRotate),
                new InstantCommand(()->clawUpDown.setPos(ClawUpDown.PREAPER_SCORING_BACKWARD_SPECIMEN)),
                new InstantCommand(() -> claw.SetPose(Claw.OPEN)),
                new WaitCommand(200),
                new InstantCommand(()->clawUpDown.setPos(ClawUpDown.COLLECT)),
                new WaitCommand(700),
                new ExtenderArmCommand(extenderArm, elbowArm,ExtenderArm.COLLECT).raceWith(
                        new WaitUntilCommand(()->extenderArm.isPressed())
                ),
                new ElbowArmCommand(elbowArm, ElbowArm.SPECIMEN_COLLECT),
                new InstantCommand(()-> {
                    FtcDashboard.getInstance().getTelemetry().addLine("w");
                    FtcDashboard.getInstance().getTelemetry().update();
                }),
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