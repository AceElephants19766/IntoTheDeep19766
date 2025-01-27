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
import com.arcrobotics.ftclib.command.button.Trigger;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Commands.ActionCommand;
import org.firstinspires.ftc.teamcode.Commands.ClawSetPose;
import org.firstinspires.ftc.teamcode.Commands.ElbowKeepPos;
import org.firstinspires.ftc.teamcode.Commands.ExtenderArmCommand;
import org.firstinspires.ftc.teamcode.MultiSystem.CollectSample;
import org.firstinspires.ftc.teamcode.MultiSystem.PreaperForScoreSpecimen;
import org.firstinspires.ftc.teamcode.MultiSystem.PrepaereForScoreSample;
import org.firstinspires.ftc.teamcode.MultiSystem.PrepareForCollectSample;
import org.firstinspires.ftc.teamcode.MultiSystem.ScoreSpecimen;
import org.firstinspires.ftc.teamcode.Subsystems.AutoDriveTrain;
import org.firstinspires.ftc.teamcode.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Subsystems.ClawRollRotate;
import org.firstinspires.ftc.teamcode.Subsystems.ClawUpDown;
import org.firstinspires.ftc.teamcode.Subsystems.DriveTrainMecanum;
import org.firstinspires.ftc.teamcode.Subsystems.ElbowArm;
import org.firstinspires.ftc.teamcode.Subsystems.ExtenderArm;
import org.firstinspires.ftc.teamcode.Subsystems.HangArm;

// for the red close and the blue close (mirror)
@Autonomous
public class RedCloseSample extends CommandOpMode {
    //Subsystem
    private AutoDriveTrain autoDriveTrain;

    public Claw claw;
    public ClawRollRotate clawRollRotat;
    public ClawUpDown clawUpDown;
    public HangArm hangArm;
    public ExtenderArm extenderArm;
    public ElbowArm elbowArm;

    public DriveTrainMecanum driveTrainMecanum;

    public Trigger joystickRightYUpCondition;
    public Trigger joystickRightYDownCondition;

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

        TrajectoryActionBuilder PrepaerForSpicimen = autoDriveTrain.getMecanumDrive().actionBuilder(
                        initialPose
                )
                .setTangent(Math.toRadians(90))
                .splineToConstantHeading(
                        new Vector2d(-10, -38)
                        , Math.toRadians(90) //tangent
                );
        TrajectoryActionBuilder BackingUpAfterSpecimen = PrepaerForSpicimen.endTrajectory().fresh()
                .setTangent(Math.toRadians(90))
                .splineToConstantHeading(
                        new Vector2d(-10, -45),
                        Math.toRadians(90)
                );
        TrajectoryActionBuilder goToSample = BackingUpAfterSpecimen.endTrajectory().fresh()
                .setTangent(Math.toRadians(180))
                .splineToConstantHeading(
                        new Vector2d(-47, -33),
                        Math.toRadians(90)
                );
        TrajectoryActionBuilder goToBasket = goToSample.endTrajectory().fresh()
                .setTangent(Math.toRadians(-90))
                .splineToSplineHeading(
                        new Pose2d(-50, -50, Math.toRadians(45)),
                        Math.toRadians(-90)
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
                        new ActionCommand(PrepaerForSpicimen.build(), autoDriveTrain),
                        new PreaperForScoreSpecimen(elbowArm, extenderArm, clawUpDown, clawRollRotat),
                        new WaitCommand(500),
                        new ScoreSpecimen(elbowArm, extenderArm, clawUpDown, clawRollRotat, claw),
                        new ActionCommand(BackingUpAfterSpecimen.build(), autoDriveTrain),
                        new ClawSetPose(claw, Claw.OPEN),
                        new PrepareForCollectSample(elbowArm, extenderArm, clawUpDown, clawRollRotat),
                        new ParallelCommandGroup(
                                new ActionCommand(goToSample.build(), autoDriveTrain),
                                new ExtenderArmCommand(extenderArm, ExtenderArm.COLLECT)
                        ),
                        new CollectSample(elbowArm, extenderArm, claw, clawRollRotat)
//                        new ParallelCommandGroup(
//                            new ActionCommand(goToBasket.build()),
//                            new SequentialCommandGroup(
//                                    new WaitUntilCommand(
//                                        () -> autoDriveTrain.getMecanumDrive().localizer.getPose().position.x < -40
//                        ),
//                            new PrepaereForScoreSample(elbowArm, extenderArm, clawUpDown, clawRollRotat)
//                            )
//                        ),
//                        new ClawSetPose(claw, Claw.OPEN),
//                        //todo like the other one
//                        new ActionCommand(park.build()),
//                        new PrepareForCollectSample(elbowArm, extenderArm, clawUpDown, clawRollRotat)
//                        new PrepaereForScoreSample(elbowArm, extenderArm, clawUpDown, clawRollRotat),
//                        new ActionCommand(goToBasket.build(), autoDriveTrain),
//                        new ClawSetPose(claw,Claw.OPEN)
                )
        );
    }

    @Override
    public void run() {
        super.run();
        telemetry.addData("extender", extenderArm.getLength());
        telemetry.update();
    }
}
