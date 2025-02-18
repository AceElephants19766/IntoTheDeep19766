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
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Commands.ActionCommand;
import org.firstinspires.ftc.teamcode.Commands.ClawSetPose;
import org.firstinspires.ftc.teamcode.Commands.ElbowArmCommand;
import org.firstinspires.ftc.teamcode.Commands.ElbowKeepPos;
import org.firstinspires.ftc.teamcode.MultiSystem.CollectSample;
import org.firstinspires.ftc.teamcode.MultiSystem.PreaperForScoreSampleAuto;
import org.firstinspires.ftc.teamcode.MultiSystem.PrepareForCollectSample;
import org.firstinspires.ftc.teamcode.MultiSystem.ScoringBasketAutonomuos;
import org.firstinspires.ftc.teamcode.Subsystems.AutoDriveTrain;
import org.firstinspires.ftc.teamcode.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Subsystems.ClawRollRotate;
import org.firstinspires.ftc.teamcode.Subsystems.ClawUpDown;
import org.firstinspires.ftc.teamcode.Subsystems.ElbowArm;
import org.firstinspires.ftc.teamcode.Subsystems.ExtenderArm;
import org.firstinspires.ftc.teamcode.Subsystems.HangArm;

@Disabled
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

        Pose2d initialPose = new Pose2d(-32, -62, Math.toRadians(90));
        Pose2d basket = new Pose2d(-52, -53, Math.toRadians(40));
        autoDriveTrain = new AutoDriveTrain(hardwareMap, initialPose);

        elbowArm.setDefaultCommand(
                new ElbowKeepPos(elbowArm,extenderArm)
        );

        TrajectoryActionBuilder goToBasket = autoDriveTrain.getMecanumDrive().actionBuilder(
                        initialPose
                )
                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(
                        basket,
                        Math.toRadians(180)
                );

        TrajectoryActionBuilder goToSample = goToBasket.endTrajectory().fresh()
                .setTangent(Math.toRadians(0))
                .strafeToLinearHeading(new Vector2d(-48, -37),
                        Math.toRadians(90));

        TrajectoryActionBuilder goToBasket2 = goToSample.endTrajectory().fresh()
                .setTangent(Math.toRadians(-90))
                .splineToLinearHeading(
                        basket,
                        Math.toRadians(180)
                );
        TrajectoryActionBuilder goToParkAtBar = goToBasket.endTrajectory().fresh()
                .setTangent(Math.toRadians(0))
                .splineToSplineHeading(
                        new Pose2d(-25, -6, Math.toRadians(180)),
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
                                        new InstantCommand(() -> {
                                            FtcDashboard.getInstance().getTelemetry().addLine("tyyyyy");
                                            FtcDashboard.getInstance().getTelemetry().update();
                                        }),
                                        new ScoringBasketAutonomuos(elbowArm, extenderArm, clawUpDown, claw)
                                )
                        ),
                        new ParallelCommandGroup(
                                new ActionCommand(goToSample.build()),
                                new SequentialCommandGroup(
                                        new WaitCommand(700),
                                        new PrepareForCollectSample(elbowArm, extenderArm,claw, clawUpDown, clawRollRotat)
                                )
                        ),
                        new WaitCommand(500),
                        new CollectSample(elbowArm, extenderArm, claw,clawUpDown),
                        new ParallelCommandGroup(
                                new ActionCommand(goToBasket2.build()),
                                new SequentialCommandGroup(
                                        new WaitUntilCommand(
                                                () -> autoDriveTrain.getMecanumDrive().localizer.getPose().position.x < -41
                                        ),
                                        new ScoringBasketAutonomuos(elbowArm, extenderArm, clawUpDown, claw)
                                )
                        ),
                        new ParallelCommandGroup(
                                new ActionCommand(goToParkAtBar.build()),
                                new SequentialCommandGroup(
                                        new WaitCommand(700),
                                        new PrepareForCollectSample(elbowArm, extenderArm,claw,clawUpDown, clawRollRotat)
                                )
                        ),
                        new ElbowArmCommand(elbowArm, ElbowArm.SCORING_SAMPLE)
                )
        );
    }
}
