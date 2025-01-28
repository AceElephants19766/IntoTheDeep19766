package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.command.WaitUntilCommand;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Commands.ActionCommand;
import org.firstinspires.ftc.teamcode.Commands.ClawSetPose;
import org.firstinspires.ftc.teamcode.Commands.ElbowKeepPos;
import org.firstinspires.ftc.teamcode.MultiSystem.PreaperForScoreSpecimen;
import org.firstinspires.ftc.teamcode.MultiSystem.ScoreSpecimen;
import org.firstinspires.ftc.teamcode.MultiSystem.ScoringBasketAutonomuos;
import org.firstinspires.ftc.teamcode.Subsystems.AutoDriveTrain;
import org.firstinspires.ftc.teamcode.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Subsystems.ClawRollRotate;
import org.firstinspires.ftc.teamcode.Subsystems.ClawUpDown;
import org.firstinspires.ftc.teamcode.Subsystems.ElbowArm;
import org.firstinspires.ftc.teamcode.Subsystems.ExtenderArm;
import org.firstinspires.ftc.teamcode.Subsystems.HangArm;

@Autonomous
public class RedCloseSpecimen extends CommandOpMode {
    //Subsystem
    private AutoDriveTrain autoDriveTrain;

    public Claw claw;
    public ClawRollRotate clawRollRotat;
    public ClawUpDown clawUpDown;
    public HangArm hangArm;
    public ExtenderArm extenderArm;
    public ElbowArm elbowArm;

    @Override
    public void initialize() {

        //Subsystems
        claw = new Claw(hardwareMap);
        clawRollRotat = new ClawRollRotate(hardwareMap);
        clawUpDown = new ClawUpDown(hardwareMap);
        extenderArm = new ExtenderArm(hardwareMap);
        elbowArm = new ElbowArm(hardwareMap);
        hangArm = new HangArm(hardwareMap);

        Pose2d initialPose = new Pose2d(-32, -62, Math.toRadians(90));
        autoDriveTrain = new AutoDriveTrain(hardwareMap, initialPose);

        elbowArm.setDefaultCommand(
                new ElbowKeepPos(elbowArm)
        );
        TrajectoryActionBuilder PrepaerForSpicimen = autoDriveTrain.getMecanumDrive().actionBuilder(
                        initialPose
                )
                .setTangent(Math.toRadians(90))
                .splineToConstantHeading(
                        new Vector2d(-10, -34)
                        , Math.toRadians(90) //tangent
                );
        TrajectoryActionBuilder BackingUpAfterSpecimen = PrepaerForSpicimen.endTrajectory().fresh()
                .setTangent(Math.toRadians(90))
                .splineToConstantHeading(
                        new Vector2d(-10, -45),
                        Math.toRadians(90)
                );
        TrajectoryActionBuilder goToSample = BackingUpAfterSpecimen.endTrajectory().fresh()
                .setTangent(Math.toRadians(0))
                .strafeToLinearHeading(new Vector2d(-47,-40),
                        Math.toRadians(90));

        TrajectoryActionBuilder goToParkAtBar = goToSample.endTrajectory().fresh()
                .setTangent(Math.toRadians(0))
                .splineToSplineHeading(
                        new Pose2d(-25,-10,Math.toRadians(180)),
                        Math.toRadians(0)
                );
        schedule(
                new InstantCommand(),
                        new SequentialCommandGroup(
                                new ParallelCommandGroup(
                                        new ActionCommand(PrepaerForSpicimen.build()),
                                        new SequentialCommandGroup(
                                                new WaitUntilCommand(
                                                        () -> autoDriveTrain.getMecanumDrive().localizer.getPose().position.x > -20
                                                ),
                                                new PreaperForScoreSpecimen(elbowArm, clawRollRotat)
                                        )
                                ),
                        new WaitCommand(500),
                        new ScoreSpecimen(elbowArm, extenderArm, clawUpDown, clawRollRotat, claw),
                        new ActionCommand(BackingUpAfterSpecimen.build(), autoDriveTrain),
                        new ClawSetPose(claw, Claw.OPEN),
                        new ActionCommand(goToParkAtBar.build())
                )
        );
    }
}
