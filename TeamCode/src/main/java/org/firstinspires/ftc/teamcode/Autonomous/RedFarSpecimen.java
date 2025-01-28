package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.dashboard.FtcDashboard;
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
import org.firstinspires.ftc.teamcode.Commands.ElbowArmCommand;
import org.firstinspires.ftc.teamcode.Commands.ElbowKeepPos;
import org.firstinspires.ftc.teamcode.Commands.ExtenderArmCommand;
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
public class RedFarSpecimen extends CommandOpMode {
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

        Pose2d initialPose = new Pose2d(15, -62, Math.toRadians(90));
         autoDriveTrain = new AutoDriveTrain(hardwareMap, initialPose);

        elbowArm.setDefaultCommand(
                new ElbowKeepPos(elbowArm)
        );

        TrajectoryActionBuilder PrepaerForSpicimen = autoDriveTrain.getMecanumDrive().actionBuilder(
                        initialPose
                )
                .setTangent(Math.toRadians(90))
                .splineToConstantHeading(
                        new Vector2d(10, -33)
                        , Math.toRadians(90) //tangent
                );
        TrajectoryActionBuilder BackingUpAfterSpecimen = PrepaerForSpicimen.endTrajectory().fresh()
                .setTangent(Math.toRadians(90))
                .splineToConstantHeading(
                        new Vector2d(10, -45),
                        Math.toRadians(90)
                );
        TrajectoryActionBuilder park = BackingUpAfterSpecimen.endTrajectory().fresh()
                .setTangent(-90)
                .splineToConstantHeading(
                        new Vector2d(60,-62),
                        Math.toRadians(-90)
                );

        schedule(
                new InstantCommand(),
                new SequentialCommandGroup(
                        new InstantCommand(() -> FtcDashboard.getInstance().getTelemetry().addLine("bjk")),
                        new ElbowArmCommand(elbowArm, ElbowArm.AUTO_SCORING_SPECIMEN),
                        new WaitCommand(1000),
                        new ActionCommand(PrepaerForSpicimen.build()),
                        new WaitCommand(1000),
                        new ElbowArmCommand(elbowArm, ElbowArm.AFTER_COLLECT_SPECIMEN),
                        new WaitCommand(500),
                        new ActionCommand(BackingUpAfterSpecimen.build(), autoDriveTrain),
                        new ClawSetPose(claw, Claw.OPEN),
                        new ExtenderArmCommand(extenderArm,0),
                        new ActionCommand(park.build())
                )
        );
    }
}
