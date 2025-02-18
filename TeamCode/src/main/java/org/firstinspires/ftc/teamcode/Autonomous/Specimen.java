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
import org.firstinspires.ftc.teamcode.MultiSystem.PrepareForCollectSpecimen;
import org.firstinspires.ftc.teamcode.Subsystems.AutoDriveTrain;
import org.firstinspires.ftc.teamcode.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Subsystems.ClawRollRotate;
import org.firstinspires.ftc.teamcode.Subsystems.ClawUpDown;
import org.firstinspires.ftc.teamcode.Subsystems.ElbowArm;
import org.firstinspires.ftc.teamcode.Subsystems.ExtenderArm;
import org.firstinspires.ftc.teamcode.Subsystems.HangArm;

@Autonomous
public class Specimen extends CommandOpMode {
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

        Pose2d initialPose = new Pose2d(15, -62, Math.toRadians(-90));
        autoDriveTrain = new AutoDriveTrain(hardwareMap, initialPose);

        elbowArm.setDefaultCommand(
                new ElbowKeepPos(elbowArm,extenderArm)
        );

        TrajectoryActionBuilder PreLoad = autoDriveTrain.getMecanumDrive().actionBuilder(
                        initialPose
                )
                .setTangent(Math.toRadians(90))
                .strafeTo(
                        new Vector2d(-4, -30)
                );
        TrajectoryActionBuilder BackUp = PreLoad.endTrajectory().fresh()
                .setTangent(Math.toRadians(-90))
                .strafeTo(
                        new Vector2d(-4,-40)
                );

        TrajectoryActionBuilder goToSample = BackUp.endTrajectory().fresh()
                .setTangent(Math.toRadians(-90))
                .strafeTo(
                        new Vector2d(36, -33)
                );

        TrajectoryActionBuilder goToSample2 = goToSample.endTrajectory().fresh()
                .setTangent(Math.toRadians(90))
                .strafeTo(
                        new Vector2d(40,-12)
                );

        TrajectoryActionBuilder goToSample3 = goToSample2.endTrajectory().fresh()
                .strafeTo(
                        new Vector2d(45, -12)
                );

        TrajectoryActionBuilder goToHUmanPlayer = goToSample3.endTrajectory().fresh()
                .setTangent(Math.toRadians(-90))
                .strafeToLinearHeading(
                        new Vector2d(45, -59),
                        Math.toRadians(-90)
                );

        TrajectoryActionBuilder goToScore = goToHUmanPlayer.endTrajectory().fresh()
                .setTangent(Math.toRadians(90))
                .strafeTo(
                        new Vector2d(-2, -30)
                );

        TrajectoryActionBuilder BackUpSec = goToScore.endTrajectory().fresh()
                .setTangent(Math.toRadians(-90))
                .strafeTo(
                        new Vector2d(-2,-40)
                );
        //sec sample
        TrajectoryActionBuilder goToSampleSec = BackUpSec.endTrajectory().fresh()
                .setTangent(-90)
                .strafeTo(
                        new Vector2d(36, -33)
                );

        TrajectoryActionBuilder goToSample2Sec = goToSampleSec.endTrajectory().fresh()
                .setTangent(Math.toRadians(90))
                .strafeTo(
                        new Vector2d(40, -12)
                );

        TrajectoryActionBuilder goToSample3Sec = goToSample2Sec.endTrajectory().fresh()
                .strafeToLinearHeading(
                        new Vector2d(52, -12),
                        Math.toRadians(-90)
                );

        TrajectoryActionBuilder goToHUmanPlayerSec = goToSample3Sec.endTrajectory().fresh()
                .setTangent(Math.toRadians(-90))
                .strafeToLinearHeading(
                        new Vector2d(52, -59),
                        Math.toRadians(-90));

        TrajectoryActionBuilder goToScoreSec = goToHUmanPlayerSec.endTrajectory().fresh()
                .setTangent(Math.toRadians(90))
                .strafeTo(
                        new Vector2d(0, -30)
                );
        // third sample
        TrajectoryActionBuilder goToHUmanPlayerThird = goToScoreSec.endTrajectory().fresh()
                .setTangent(Math.toRadians(-90))
                .strafeToLinearHeading(
                        new Vector2d(45, -59),
                        Math.toRadians(-90));
        TrajectoryActionBuilder goToScoreThird = goToHUmanPlayerThird.endTrajectory().fresh()
                .setTangent(Math.toRadians(90))
                .strafeTo(
                        new Vector2d(3, -30)
                );
        TrajectoryActionBuilder BackUpThird = goToScoreThird.endTrajectory().fresh()
                .setTangent(Math.toRadians(-90))
                .strafeTo(
                        new Vector2d(-4,-40)
                );
        //forth sample
        TrajectoryActionBuilder goToHUmanPlayerForth = BackUpThird.endTrajectory().fresh()
                .setTangent(Math.toRadians(-90))
                .strafeToLinearHeading(
                        new Vector2d(45, -59),
                        Math.toRadians(-90));

        TrajectoryActionBuilder goToScoreForth = goToHUmanPlayerForth.endTrajectory().fresh()
                .setTangent(Math.toRadians(90))
                .strafeTo(
                        new Vector2d(3, -30)
                );

        TrajectoryActionBuilder goToScoreForth1 = goToScoreForth.endTrajectory().fresh()
                .setTangent(Math.toRadians(90))
                .strafeTo(
                        new Vector2d(7, -30)
                );
        TrajectoryActionBuilder BackUpForth = goToScoreForth1.endTrajectory().fresh()
                .setTangent(Math.toRadians(-90))
                .strafeTo(
                        new Vector2d(-4,-40)
                );
        TrajectoryActionBuilder park = goToScoreForth.endTrajectory().fresh()
                .setTangent(Math.toRadians(-90))
                .strafeToLinearHeading(
                        new Vector2d(40, -53),
                        Math.toRadians(-90));

        schedule(
                new SequentialCommandGroup(
                        // go to score pre load
                        new ParallelCommandGroup(
                                new ActionCommand(PreLoad.build()),
                                new SequentialCommandGroup(
                                        new WaitUntilCommand(
                                                () -> autoDriveTrain.getMecanumDrive().localizer.getPose().position.y < -30
                                        ),
                                        new PreaperForScoreSpecimen(elbowArm, extenderArm, claw, clawRollRotat, clawUpDown)
                                )
                        ),
                        //score pre load
                        new InstantCommand(() -> clawUpDown.setPos(ClawUpDown.SCORE_SPECIMEN), clawUpDown),
                        new WaitCommand(500),
                        new ClawSetPose(claw,Claw.OPEN),
                        //back up after scoring
                        new ActionCommand(BackUp.build()),
                        //sec sample
                        new PrepareForCollectSpecimen(extenderArm,elbowArm,clawRollRotat,clawUpDown,claw),
                        new ActionCommand(goToSample.build()),
                        new ActionCommand(goToSample2.build()),
                        new ActionCommand(goToHUmanPlayer.build()),
                        //collect sec sample
                        new ClawSetPose(claw,Claw.CLOSE),
                        new WaitCommand(100),
                        new InstantCommand(() -> clawUpDown.setPos(ClawUpDown.PREAPER_SCORING_BACKWARD_SPECIMEN), clawUpDown),
                        //go to score sec sample
                        new ParallelCommandGroup(
                                new ActionCommand(goToScore.build()),
                                new SequentialCommandGroup(
                                        new WaitUntilCommand(
                                                () -> autoDriveTrain.getMecanumDrive().localizer.getPose().position.y > -55
                                        ),
                                        new PreaperForScoreSpecimen(elbowArm, extenderArm, claw, clawRollRotat, clawUpDown)
                                )
                        ),
                        //score sec sample
                        new ParallelCommandGroup(
                                new ActionCommand(BackUpSec.build()),
                                new SequentialCommandGroup(
                                        new WaitUntilCommand(
                                                () -> autoDriveTrain.getMecanumDrive().localizer.getPose().position.y < -30
                                        ),
                                        new InstantCommand(() -> clawUpDown.setPos(ClawUpDown.SCORE_SPECIMEN), clawUpDown),
                                        new ClawSetPose(claw,Claw.OPEN)
                                )
                        ),
                        new PrepareForCollectSpecimen(extenderArm,elbowArm,clawRollRotat,clawUpDown,claw),
                        new ActionCommand(goToSampleSec.build()),
                        new ActionCommand(goToSample2Sec.build()),
                        new ActionCommand(goToHUmanPlayerSec.build()),
                        //collect third sample
                        new ClawSetPose(claw,Claw.CLOSE),
                        new InstantCommand(() -> clawUpDown.setPos(ClawUpDown.PREAPER_SCORING_BACKWARD_SPECIMEN), clawUpDown),
                        //go to score third sample
                        new ParallelCommandGroup(
                                new ActionCommand(goToScoreThird.build()),
                                new SequentialCommandGroup(
                                        new WaitUntilCommand(
                                                () -> autoDriveTrain.getMecanumDrive().localizer.getPose().position.x < 15
                                        ),
                                        new PreaperForScoreSpecimen(elbowArm, extenderArm, claw, clawRollRotat, clawUpDown)
                                )
                        ),
                        //score third sample
                        new WaitCommand(100),
                        new InstantCommand(() -> clawUpDown.setPos(ClawUpDown.SCORE_SPECIMEN), clawUpDown),
                        new WaitCommand(200),
                        new ClawSetPose(claw,Claw.OPEN),
                        //back up after scoring third sample
                        new ActionCommand(BackUpThird.build()),
                        new PrepareForCollectSpecimen(extenderArm,elbowArm,clawRollRotat,clawUpDown,claw),
                        new ActionCommand(goToHUmanPlayerForth.build()),
                        //collect forth sample
                        new ClawSetPose(claw,Claw.CLOSE),
                        new InstantCommand(() -> clawUpDown.setPos(ClawUpDown.PREAPER_SCORING_BACKWARD_SPECIMEN), clawUpDown),
                        //go to score forth sample
                        new ParallelCommandGroup(
                                new ActionCommand(goToScoreForth.build()),
                                new SequentialCommandGroup(
                                        new WaitUntilCommand(
                                                () -> autoDriveTrain.getMecanumDrive().localizer.getPose().position.x < 15
                                        ),
                                        new PreaperForScoreSpecimen(elbowArm, extenderArm, claw, clawRollRotat, clawUpDown)
                                )
                        ),
                        new ActionCommand(goToScoreForth1.build()),
                        new InstantCommand(() -> clawUpDown.setPos(ClawUpDown.SCORE_SPECIMEN), clawUpDown),
                        new WaitCommand(200),
                        new ClawSetPose(claw,Claw.OPEN),
                        //back up after scoring forth sample
                        new ActionCommand(BackUpForth.build()),
                        new ActionCommand(park.build())
                )
        );
    }

    @Override
    public void run() {
        super.run();
        if (elbowArm.getDeg() < 60){
            elbowArm.getPidController().setP(0.04);
            elbowArm.getPidController().setI(0.003);
        }else {
            elbowArm.getPidController().setP(0.01);
            elbowArm.getPidController().setI(0.001);
        }
        telemetry.addData("elbow",elbowArm.getDeg());
        telemetry.update();
    }
}
