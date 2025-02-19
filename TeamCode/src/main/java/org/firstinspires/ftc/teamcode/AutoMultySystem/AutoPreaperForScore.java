package org.firstinspires.ftc.teamcode.AutoMultySystem;

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

public class AutoPreaperForScore extends SequentialCommandGroup {
    public AutoPreaperForScore(ElbowArm elbowArm, ExtenderArm extenderArm, ClawUpDown clawUpDown, ClawRollRotate clawRollRotate,Claw claw) {
        addCommands(
                new InstantCommand(() -> clawRollRotate.setPose(ClawRollRotate.DEFAULT)),
                new InstantCommand(() -> clawUpDown.setPos(ClawUpDown.SCORING)),
                new ElbowArmCommand(elbowArm, ElbowArm.SCORING_SAMPLE),
                new WaitUntilCommand(() -> elbowArm.getPidController().getPositionError() < 40),
                new ExtenderArmCommand(extenderArm, ExtenderArm.SCORE).withTimeout(2000),
                new InstantCommand(()->clawUpDown.setPos(ClawUpDown.PREAPER_SCORING_BACKWARD_SPECIMEN)),
                new InstantCommand(() -> claw.SetPose(Claw.OPEN)),
                new WaitCommand(200)
        );
        addRequirements(
                extenderArm,
                clawUpDown,
                clawRollRotate
        );

    }
}
