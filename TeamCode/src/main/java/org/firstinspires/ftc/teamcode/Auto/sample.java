package org.firstinspires.ftc.teamcode.Auto;

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

import org.firstinspires.ftc.teamcode.AutoMultySystem.AuotPreaperForCollect;
import org.firstinspires.ftc.teamcode.AutoMultySystem.AutoCollectSample;
import org.firstinspires.ftc.teamcode.AutoMultySystem.AutoPreaperForScore;
import org.firstinspires.ftc.teamcode.Commands.ActionCommand;
import org.firstinspires.ftc.teamcode.Commands.ElbowArmCommand;
import org.firstinspires.ftc.teamcode.Commands.ElbowKeepPos;
import org.firstinspires.ftc.teamcode.Commands.ExtenderArmCommand;
import org.firstinspires.ftc.teamcode.Subsystems.AutoDriveTrain;
import org.firstinspires.ftc.teamcode.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Subsystems.ClawRollRotate;
import org.firstinspires.ftc.teamcode.Subsystems.ClawUpDown;
import org.firstinspires.ftc.teamcode.Subsystems.ElbowArm;
import org.firstinspires.ftc.teamcode.Subsystems.ExtenderArm;
import org.firstinspires.ftc.teamcode.Subsystems.HangArm;


@Autonomous
public class sample extends CommandOpMode {
    //Subsystem
    private AutoDriveTrain autoDriveTrain;

    public Claw claw;
    public ClawRollRotate clawRollRotat;
    public ClawUpDown clawUpDown;
    public HangArm hangArm;
    public ExtenderArm extenderArm;
    public ElbowArm elbowArm;

    public Trigger extenderReset;

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
                new ElbowKeepPos(elbowArm, extenderArm)
        );


        TrajectoryActionBuilder preLoad = autoDriveTrain.getMecanumDrive().actionBuilder(
                        initialPose
                )
                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(
                        new Pose2d(-54, -54, Math.toRadians(45)),
                        Math.toRadians(180)
                );

        TrajectoryActionBuilder Sample = preLoad.endTrajectory().fresh()
                .setTangent(Math.toRadians(0))
                .strafeToLinearHeading(
                        new Vector2d(-47, -40),
                        Math.toRadians(90)
                );

        TrajectoryActionBuilder Basket = Sample.endTrajectory().fresh()
                .setTangent(Math.toRadians(-90))
                .splineToLinearHeading(
                        new Pose2d(-55, -55, Math.toRadians(45)),
                        Math.toRadians(-135)
                );

        TrajectoryActionBuilder secSample = Basket.endTrajectory().fresh()
                .setTangent(Math.toRadians(0))
                .strafeToLinearHeading(
                        new Vector2d(-56, -40),
                        Math.toRadians(90)
                );

        TrajectoryActionBuilder Basket2 = secSample.endTrajectory().fresh()
                .setTangent(Math.toRadians(-90))
                .splineToLinearHeading(
                        new Pose2d(-55, -55, Math.toRadians(45)),
                        Math.toRadians(-135)
                );

        TrajectoryActionBuilder ThirdSample = Basket2.endTrajectory().fresh()
                .setTangent(Math.toRadians(0))
                .strafeToLinearHeading(
                        new Vector2d(-55, -28),
                        Math.toRadians(180)
                );

        TrajectoryActionBuilder Basket3 = ThirdSample.endTrajectory().fresh()
                .setTangent(Math.toRadians(-90))
                .splineToLinearHeading(
                        new Pose2d(-55, -55, Math.toRadians(45)),
                        Math.toRadians(-135)
                );

        TrajectoryActionBuilder goToParkAtBar = Basket.endTrajectory().fresh()
                .setTangent(Math.toRadians(0))
                .splineToSplineHeading(
                        new Pose2d(-25, -10, Math.toRadians(180)),
                        Math.toRadians(0)
                );


        schedule(
                new InstantCommand(),

                new SequentialCommandGroup(
                        //score preload
                        new ParallelCommandGroup(
                                new ActionCommand(preLoad.build()),
                                new SequentialCommandGroup(
                                        new WaitUntilCommand(
                                                () -> autoDriveTrain.getMecanumDrive().localizer.getPose().position.x < -45
                                        ),
                                        new AutoPreaperForScore(elbowArm, extenderArm, clawUpDown, clawRollRotat, claw)
                                )
                        )/*,

                        new WaitCommand(1000),

                        //collect sample
                        new ParallelCommandGroup(
                                new ActionCommand(Sample.build()),
                                new SequentialCommandGroup(
                                        new WaitUntilCommand(
                                                () -> autoDriveTrain.getMecanumDrive().localizer.getPose().position.y > -50
                                        ),
                                        new AuotPreaperForCollect(elbowArm, extenderArm, claw, clawUpDown, clawRollRotat)
                                )
                        ),
                        new WaitCommand(500),

                        //collect sample
                        new AutoCollectSample(elbowArm, extenderArm, claw, clawUpDown),

                        new ParallelCommandGroup(
                                new ActionCommand(Basket.build()),
                                new SequentialCommandGroup(
                                        new WaitUntilCommand(
                                                () -> autoDriveTrain.getMecanumDrive().localizer.getPose().position.y < -45
                                        ),
                                        new AutoPreaperForScore(elbowArm, extenderArm, clawUpDown, clawRollRotat, claw)
                                )
                        ),
                        new WaitCommand(1000),

                        new ParallelCommandGroup(
                                new ActionCommand(secSample.build()),
                                new SequentialCommandGroup(
                                        new WaitUntilCommand(
                                                () -> autoDriveTrain.getMecanumDrive().localizer.getPose().position.y > -50
                                        ),
                                        new AuotPreaperForCollect(elbowArm, extenderArm, claw, clawUpDown, clawRollRotat)
                                )
                        ),

                        new WaitCommand(500),


                        new AutoCollectSample(elbowArm, extenderArm, claw, clawUpDown),
                        new ParallelCommandGroup(
                                new ActionCommand(Basket2.build()),
                                new SequentialCommandGroup(
                                        new WaitUntilCommand(
                                                () -> autoDriveTrain.getMecanumDrive().localizer.getPose().position.x < -45
                                        ),
                                        new AutoPreaperForScore(elbowArm, extenderArm, clawUpDown, clawRollRotat, claw)
                                )
                        ),
                        new WaitCommand(1000),
                        new ParallelCommandGroup(
                                new ActionCommand(goToParkAtBar.build()),
                                new SequentialCommandGroup(
                                        new WaitUntilCommand(
                                                () -> autoDriveTrain.getMecanumDrive().localizer.getPose().position.y > -50
                                        ),
                                        new AuotPreaperForCollect(elbowArm, extenderArm, claw, clawUpDown, clawRollRotat)
                                )
                        ),
                        new ExtenderArmCommand(extenderArm, elbowArm, 0),
                        new WaitCommand(500),
                        new ElbowArmCommand(elbowArm, 130)*/
                )
        );
    }
}
