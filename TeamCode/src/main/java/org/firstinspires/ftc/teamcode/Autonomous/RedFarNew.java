package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.command.button.Trigger;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.AutoMultysystem.GoToBasketAndScore;
import org.firstinspires.ftc.teamcode.Commands.ActionCommand;
import org.firstinspires.ftc.teamcode.Commands.ElbowArmCommand;
import org.firstinspires.ftc.teamcode.Commands.ElbowKeepPos;
import org.firstinspires.ftc.teamcode.Commands.ExtenderArmCommand;
import org.firstinspires.ftc.teamcode.MultiSystem.CollectSample;
import org.firstinspires.ftc.teamcode.MultiSystem.PrepareForCollectSample;
import org.firstinspires.ftc.teamcode.Subsystems.AutoDriveTrain;
import org.firstinspires.ftc.teamcode.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Subsystems.ClawRollRotate;
import org.firstinspires.ftc.teamcode.Subsystems.ClawUpDown;
import org.firstinspires.ftc.teamcode.Subsystems.DriveTrainMecanum;
import org.firstinspires.ftc.teamcode.Subsystems.ElbowArm;
import org.firstinspires.ftc.teamcode.Subsystems.ExtenderArm;
import org.firstinspires.ftc.teamcode.Subsystems.HangArm;

@Autonomous
public class RedFarNew extends CommandOpMode {
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

        Pose2d initialPose = new Pose2d(15, -62, Math.toRadians(-90));
        autoDriveTrain = new AutoDriveTrain(hardwareMap, initialPose);

        elbowArm.setDefaultCommand(
                new ElbowKeepPos(elbowArm,extenderArm)
        );

        TrajectoryActionBuilder PrepaerForSpicimen = autoDriveTrain.getMecanumDrive().actionBuilder(
                        initialPose
                )
                .setTangent(Math.toRadians(90))
                .splineToConstantHeading(
                        new Vector2d(4, -33)
                        , Math.toRadians(90) //tangent
                );
        TrajectoryActionBuilder BackUp = PrepaerForSpicimen.endTrajectory().fresh()
                .setTangent(Math.toRadians(-90))
                .splineToConstantHeading(
                        new Vector2d(10, -45), Math.toRadians(-90));

        TrajectoryActionBuilder goToSample = BackUp.endTrajectory().fresh()
                .setTangent(-90)
                .splineToSplineHeading(
                        new Pose2d(36, -22, Math.toRadians(0)),
                        Math.toRadians(90));

        TrajectoryActionBuilder goToSample2 = goToSample.endTrajectory().fresh()
                .splineToSplineHeading(
                        new Pose2d(45, -12, Math.toRadians(-90))
                        , Math.toRadians(-90));

        TrajectoryActionBuilder goToHUmanPlayer = goToSample2.endTrajectory().fresh()
                .setTangent(Math.toRadians(-90))
                .splineToSplineHeading(
                        new Pose2d(40, -60, Math.toRadians(-90)),
                        Math.toRadians(-90));

        TrajectoryActionBuilder goToScore = goToHUmanPlayer.endTrajectory().fresh()
                .setTangent(Math.toRadians(90))
                .splineToConstantHeading(
                        new Vector2d(4, -33),
                        Math.toRadians(180)
                );
        TrajectoryActionBuilder goToSample3 = goToScore.endTrajectory().fresh()
                .setTangent(-90)
                .splineToSplineHeading(
                        new Pose2d(36, -22, Math.toRadians(0)),
                        Math.toRadians(90));

        TrajectoryActionBuilder goToSecSample = goToSample3.endTrajectory().fresh()
                .splineToSplineHeading(
                        new Pose2d(57, -12, Math.toRadians(-90))
                        , Math.toRadians(-90));

        TrajectoryActionBuilder goToHUmanPlayer2 = goToSecSample.endTrajectory().fresh()
                .setTangent(Math.toRadians(-90))
                .splineToSplineHeading(
                        new Pose2d(40, -60, Math.toRadians(-90)),
                        Math.toRadians(-90));
        TrajectoryActionBuilder goToSecScore = goToHUmanPlayer.endTrajectory().fresh()
                .setTangent(Math.toRadians(90))
                .splineToConstantHeading(
                        new Vector2d(4, -33),
                        Math.toRadians(180));
        TrajectoryActionBuilder goToHUmanPlayer3 = goToSecScore.endTrajectory().fresh()
                .setTangent(Math.toRadians(-90))
                .splineToSplineHeading(
                        new Pose2d(40, -60, Math.toRadians(-90)),
                        Math.toRadians(-90));
        TrajectoryActionBuilder goToThirdScore = goToHUmanPlayer3.endTrajectory().fresh()
                .setTangent(Math.toRadians(90))
                .splineToConstantHeading(
                        new Vector2d(4, -33),
                        Math.toRadians(180));

        schedule(
                new SequentialCommandGroup(
                        new ActionCommand(PrepaerForSpicimen.build()),
                        new ActionCommand(BackUp.build()),
                        new ActionCommand(goToSample.build()),
                        new ActionCommand(goToSample2.build()),
                        new ActionCommand(goToHUmanPlayer.build()),
                        new ActionCommand(goToScore.build()),
                        new ActionCommand(goToSample3.build()),
                        new ActionCommand(goToSecSample.build()),
                        new ActionCommand(goToHUmanPlayer2.build()),
                        new ActionCommand(goToSecScore.build()),
                        new ActionCommand(goToHUmanPlayer3.build()),
                        new ActionCommand(goToThirdScore.build())
                )
        );
    }
}
