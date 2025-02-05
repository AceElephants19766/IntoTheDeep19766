package org.firstinspires.ftc.teamcode.AutoMultysystem;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitUntilCommand;

import org.firstinspires.ftc.teamcode.Commands.ActionCommand;
import org.firstinspires.ftc.teamcode.MultiSystem.ScoringBasketAutonomuos;
import org.firstinspires.ftc.teamcode.Subsystems.AutoDriveTrain;
import org.firstinspires.ftc.teamcode.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Subsystems.ClawUpDown;
import org.firstinspires.ftc.teamcode.Subsystems.ElbowArm;
import org.firstinspires.ftc.teamcode.Subsystems.ExtenderArm;

public class GoToBasketAndScore extends SequentialCommandGroup {

    Pose2d initialPose = new Pose2d(-32, -62, Math.toRadians(90));

    public GoToBasketAndScore(
            AutoDriveTrain autoDriveTrain,
            TrajectoryActionBuilder goToBasket,
            ElbowArm elbowArm,
            ExtenderArm extenderArm,
            ClawUpDown clawUpDown,
            Claw claw
    ) {
        addCommands(
                new ActionCommand(goToBasket.build()),
                new SequentialCommandGroup(
                        new WaitUntilCommand(
                                () -> autoDriveTrain.getMecanumDrive().localizer.getPose().position.x < -51
                        ),
                        new ScoringBasketAutonomuos(elbowArm, extenderArm, clawUpDown, claw)
                )
        );
        addRequirements(
                autoDriveTrain,
                extenderArm,
                clawUpDown,
                claw
        );

    }
}
