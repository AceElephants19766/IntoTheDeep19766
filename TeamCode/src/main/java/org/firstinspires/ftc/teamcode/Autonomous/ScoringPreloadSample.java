package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
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
import org.firstinspires.ftc.teamcode.MultiSystem.PrepaereForScore;
import org.firstinspires.ftc.teamcode.MultiSystem.PrepareForCollectCommand;
import org.firstinspires.ftc.teamcode.Subsystems.AutoDriveTrain;
import org.firstinspires.ftc.teamcode.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Subsystems.ClawRollRotate;
import org.firstinspires.ftc.teamcode.Subsystems.ClawUpDown;
import org.firstinspires.ftc.teamcode.Subsystems.ElbowArm;
import org.firstinspires.ftc.teamcode.Subsystems.ExtenderArm;
import org.firstinspires.ftc.teamcode.Subsystems.HangArm;

@Autonomous
public class ScoringPreloadSample extends CommandOpMode {

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

        Pose2d initialPose = new Pose2d(-8, -62, Math.toRadians(90));
        autoDriveTrain = new AutoDriveTrain(hardwareMap, initialPose);

        elbowArm.setDefaultCommand(
                new ElbowKeepPos(elbowArm)
        );

        TrajectoryActionBuilder goToBasket = autoDriveTrain.getMecanumDrive().actionBuilder(
                        initialPose
                )
                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(
                        new Pose2d(-50, -50, Math.toRadians(-135)),
                        Math.toRadians(180)
                );
        TrajectoryActionBuilder park = goToBasket.endTrajectory().fresh()
                .setTangent(Math.toRadians(0))
                .splineToSplineHeading(
                        new Pose2d(36, -60, Math.toRadians(-90)),
                        Math.toRadians(0)
                );

        schedule(
                new InstantCommand(),
                new SequentialCommandGroup(
                        new ParallelCommandGroup(
                                new ActionCommand(goToBasket.build()),
                                new SequentialCommandGroup(
                                        new WaitUntilCommand(
                                                () -> autoDriveTrain.getMecanumDrive().localizer.getPose().position.x < -40
                                        ),
                                        new PrepaereForScore(elbowArm, extenderArm, clawUpDown, clawRollRotat)
                                )
                        ),
                        new ClawSetPose(claw, Claw.OPEN),
                        //todo like the other one
                        new ActionCommand(park.build()),
                        new PrepareForCollectCommand(elbowArm, extenderArm, clawUpDown, clawRollRotat)
                )
        );

    }
}
