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
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.AutoMultySystem.AuotPreaperForCollect;
import org.firstinspires.ftc.teamcode.AutoMultySystem.AutoCollectSample;
import org.firstinspires.ftc.teamcode.AutoMultySystem.AutoPreaperForScore;
import org.firstinspires.ftc.teamcode.Commands.ActionCommand;
import org.firstinspires.ftc.teamcode.Commands.ElbowArmCommand;
import org.firstinspires.ftc.teamcode.Commands.ElbowKeepPos;
import org.firstinspires.ftc.teamcode.Commands.ExtenderArmCommand;
import org.firstinspires.ftc.teamcode.MultiSystem.PrepaereForScoreSample;
import org.firstinspires.ftc.teamcode.MultiSystem.PrepareForCollectSample;
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
public class Sample extends CommandOpMode {
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

        Pose2d initialPose = new Pose2d(-32, -62, Math.toRadians(90));
        autoDriveTrain = new AutoDriveTrain(hardwareMap, initialPose);

        elbowArm.setDefaultCommand(
                new ElbowKeepPos(elbowArm,extenderArm)
        );

        TrajectoryActionBuilder goToBasket = autoDriveTrain.getMecanumDrive().actionBuilder(
                        initialPose
                )
                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(
                        new Pose2d(-57, -55, Math.toRadians(45)),
                        Math.toRadians(180)
                );
        TrajectoryActionBuilder goToSample = goToBasket.endTrajectory().fresh()
                .setTangent(Math.toRadians(0))
                .strafeToLinearHeading(new Vector2d(-50, -40),
                        Math.toRadians(90));

        TrajectoryActionBuilder goToBasket2 = goToSample.endTrajectory().fresh()
                .setTangent(Math.toRadians(-90))
                .splineToLinearHeading(
                        new Pose2d(-57, -55, Math.toRadians(45)),
                        Math.toRadians(135)
                );
        TrajectoryActionBuilder goToSample2 = goToBasket2.endTrajectory().fresh()
                .setTangent(Math.toRadians(0))
                .strafeToLinearHeading(new Vector2d(-59, -40),
                        Math.toRadians(90));

        TrajectoryActionBuilder goToBasket3 = goToSample2.endTrajectory().fresh()
                .setTangent(Math.toRadians(-90))
                .splineToLinearHeading(
                        new Pose2d(-57, -55, Math.toRadians(45)),
                        Math.toRadians(135)
                );

        TrajectoryActionBuilder goToParkAtBar = goToBasket3.endTrajectory().fresh()
                .setTangent(Math.toRadians(0))
                .splineToSplineHeading(
                        new Pose2d(-30,0,Math.toRadians(180)),
                        Math.toRadians(0)
                )
                .splineToLinearHeading(
                        new Pose2d(-22,-0,Math.toRadians(180)),
                        Math.toRadians(0)
                );

        schedule(
                new InstantCommand(),
                new SequentialCommandGroup(
                        new ParallelCommandGroup(
                                new ActionCommand(goToBasket.build()),
                                new SequentialCommandGroup(
                                        new WaitUntilCommand(
                                                () -> autoDriveTrain.getMecanumDrive().localizer.getPose().position.x < -45
                                        ),
                                        new AutoPreaperForScore(elbowArm, extenderArm, clawUpDown, clawRollRotat,claw)
                                )
                        ),
                        new WaitCommand(1000),
                        new ParallelCommandGroup(
                                new ActionCommand(goToSample.build()),
                                new SequentialCommandGroup(
                                        new WaitUntilCommand(
                                                () -> autoDriveTrain.getMecanumDrive().localizer.getPose().position.y > -50
                                        ),
                                        new AuotPreaperForCollect(elbowArm,extenderArm,claw,clawUpDown,clawRollRotat)
                                )
                        ),
                        new WaitCommand(500),
                        new AutoCollectSample(elbowArm, extenderArm, claw,clawUpDown),
                        new ActionCommand(goToBasket2.build()),
                        new SequentialCommandGroup(
                                new WaitUntilCommand(
                                        () -> autoDriveTrain.getMecanumDrive().localizer.getPose().position.y < -45
                                ),
                                new AutoPreaperForScore(elbowArm, extenderArm, clawUpDown, clawRollRotat,claw)
                        ),
                        new WaitCommand(1000),
                        new ParallelCommandGroup(
                                new ActionCommand(goToSample2.build()),
                                new SequentialCommandGroup(
                                        new WaitUntilCommand(
                                                () -> autoDriveTrain.getMecanumDrive().localizer.getPose().position.y > -50
                                        ),
                                        new AuotPreaperForCollect(elbowArm,extenderArm,claw,clawUpDown,clawRollRotat)
                                )
                        ),
                        new WaitCommand(500),
                        new AutoCollectSample(elbowArm, extenderArm, claw,clawUpDown),
                        new ParallelCommandGroup(
                                new ActionCommand(goToBasket3.build()),
                                new SequentialCommandGroup(
                                        new WaitUntilCommand(
                                                () -> autoDriveTrain.getMecanumDrive().localizer.getPose().position.x < -45
                                        ),
                                        new AutoPreaperForScore(elbowArm, extenderArm, clawUpDown, clawRollRotat,claw)
                                )
                        ),
                        new WaitCommand(1000),
                        new ParallelCommandGroup(
                                new ActionCommand(goToParkAtBar.build()),
                                new SequentialCommandGroup(
                                        new WaitUntilCommand(
                                                () -> autoDriveTrain.getMecanumDrive().localizer.getPose().position.y > -50
                                        ),
                                        new AuotPreaperForCollect(elbowArm,extenderArm,claw,clawUpDown,clawRollRotat)
                                )
                        ),
                        new ExtenderArmCommand(extenderArm,0),
                        new WaitCommand(500),
                        new ElbowArmCommand(elbowArm,130)
                )
        );
    }
}
