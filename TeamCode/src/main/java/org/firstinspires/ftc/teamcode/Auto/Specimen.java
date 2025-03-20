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

import org.firstinspires.ftc.teamcode.Commands.ActionCommand;
import org.firstinspires.ftc.teamcode.Commands.ClawSetPose;
import org.firstinspires.ftc.teamcode.Commands.ElbowArmCommand;
import org.firstinspires.ftc.teamcode.Commands.ElbowKeepPos;
import org.firstinspires.ftc.teamcode.Commands.ExtenderArmCommand;
import org.firstinspires.ftc.teamcode.Commands.ResetExtnderEncoder;
import org.firstinspires.ftc.teamcode.MultiSystem.PreaperForScoreSpecimen;
import org.firstinspires.ftc.teamcode.MultiSystem.PrepareForCollectSpecimen;
import org.firstinspires.ftc.teamcode.MultiSystem.PrepareForCollectSpecimen3;
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

//    public Trigger extenderReset;

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
                new ElbowKeepPos(elbowArm, extenderArm)
        );

//        extenderReset = new Trigger(() -> extenderArm.isTouched());

//        extenderReset.whenActive(
//                new ResetExtnderEncoder(extenderArm)
//        );

        //scoring preload - done
        TrajectoryActionBuilder preLoad = autoDriveTrain.getMecanumDrive().actionBuilder(
                        initialPose
                )
                .setTangent(Math.toRadians(90))
                .splineToConstantHeading(
                        new Vector2d(-3, -28),
                        Math.toRadians(90)
                );
        TrajectoryActionBuilder BackUpAfterScoringPreload = preLoad.endTrajectory().fresh()
                .setTangent(Math.toRadians(-90))
                .splineToConstantHeading(
                        new Vector2d(-4, -40),
                        Math.toRadians(-90)
                );



        //bring sec sample - done
        TrajectoryActionBuilder goToSample = BackUpAfterScoringPreload.endTrajectory().fresh()
                .setTangent(Math.toRadians(0))
                .splineToConstantHeading(
                        new Vector2d(36, -35),
                        Math.toRadians(0)
                );

        TrajectoryActionBuilder goToSample2 = goToSample.endTrajectory().fresh()
                .setTangent(Math.toRadians(90))
                .splineToConstantHeading(
                        new Vector2d(45, -12),
                        Math.toRadians(0)
                );

        TrajectoryActionBuilder goToHUmanPlayer = goToSample2.endTrajectory().fresh()
                .setTangent(Math.toRadians(-90))
                .splineToConstantHeading(
                        new Vector2d(45, -57),
                        Math.toRadians(-90)
                );



        //score sec sample - done
        TrajectoryActionBuilder goToScoreSecSample = goToHUmanPlayer.endTrajectory().fresh()
                .setTangent(Math.toRadians(180))
                .strafeToLinearHeading(
                        new Vector2d(-1, -28),
                        Math.toRadians(-90)
                );

        TrajectoryActionBuilder backUpAfterScoringSecSample = goToScoreSecSample.endTrajectory().fresh()
                .setTangent(Math.toRadians(-90))
                .splineToConstantHeading(
                        new Vector2d(-2, -40),
                        Math.toRadians(-90)
                );



        //bring third sample - done
        TrajectoryActionBuilder goToThirdSample = backUpAfterScoringSecSample.endTrajectory().fresh()
                .setTangent(Math.toRadians(0))
                .splineToConstantHeading(
                        new Vector2d(36, -33),
                        Math.toRadians(0)
                );

        TrajectoryActionBuilder goToThirdSample2 = goToThirdSample.endTrajectory().fresh()
                .setTangent(Math.toRadians(90))
                .splineToConstantHeading(
                        new Vector2d(55, -12),
                        Math.toRadians(0)
                );

        TrajectoryActionBuilder goToHUmanPlayerSec = goToThirdSample2.endTrajectory().fresh()
                .setTangent(Math.toRadians(-90))
                .splineToConstantHeading(
                        new Vector2d(50, -57),
                        Math.toRadians(-90)
                );



        //score third sample - done
        TrajectoryActionBuilder goToScoreThirdSample = goToHUmanPlayerSec.endTrajectory().fresh()
                .setTangent(Math.toRadians(180))
                .strafeToLinearHeading(
                        new Vector2d(1, -28),
                        Math.toRadians(-90)
                );
        TrajectoryActionBuilder backUpAfterScoringThirdSample = goToScoreThirdSample.endTrajectory().fresh()
                .setTangent(Math.toRadians(180))
                .strafeToLinearHeading(
                        new Vector2d(0, -37),
                        Math.toRadians(-90)
                );


        //bring forth sample from human player
        TrajectoryActionBuilder goToHUmanPlayerThird = backUpAfterScoringThirdSample.endTrajectory().fresh()
                .setTangent(Math.toRadians(180))
                .strafeToLinearHeading(
                        new Vector2d(45, -57),
                        Math.toRadians(-90)
                );




        //score forth sample
        TrajectoryActionBuilder goToScoreForthSample = goToHUmanPlayerThird.endTrajectory().fresh()
                .setTangent(Math.toRadians(180))
                .strafeToLinearHeading(
                        new Vector2d(-2, -28),
                        Math.toRadians(-90)
                );

        //park
        TrajectoryActionBuilder park = goToScoreForthSample.endTrajectory().fresh()
                .setTangent(Math.toRadians(-90))
                .strafeToLinearHeading(
                        new Vector2d(40, -53),
                        Math.toRadians(-90));



        schedule(
                new InstantCommand(),
                new SequentialCommandGroup(
                        new ExtenderArmCommand(extenderArm,elbowArm,ExtenderArm.COLLECT),

                        // go to score pre load - working well
                        new ParallelCommandGroup(
                                new ActionCommand(preLoad.build()),
                                new SequentialCommandGroup(
                                        new WaitUntilCommand(
                                                () -> autoDriveTrain.getMecanumDrive().localizer.getPose().position.y < -30
                                        ),
                                        new PreaperForScoreSpecimen(elbowArm, extenderArm, claw, clawRollRotat, clawUpDown)
                                )
                        ),

                        //score pre load - working well
                        new InstantCommand(() -> clawUpDown.setPos(ClawUpDown.SCORE_SPECIMEN), clawUpDown),                                        new ElbowArmCommand(elbowArm,135),
                        new ElbowArmCommand(elbowArm,135),
                        new WaitCommand(500),
                        new ParallelCommandGroup(
                                new ActionCommand(BackUpAfterScoringPreload.build()),
                                new SequentialCommandGroup(
//                                        new WaitUntilCommand(
//                                                ()-> autoDriveTrain.getMecanumDrive().localizer.getPose().position.y < -33
//                                        ),
                                        new WaitCommand(450),
                                        new ClawSetPose(claw,Claw.OPEN)
                                )
                        ),

                        new PrepareForCollectSpecimen3(extenderArm,elbowArm,clawRollRotat,clawUpDown,claw),

                        //sec sample
                        new ActionCommand(goToSample.build()),
                        new ActionCommand(goToSample2.build()),
                        new ActionCommand(goToHUmanPlayer.build()),

                        //collect sec sample
                        new ClawSetPose(claw,Claw.CLOSE),
                        new WaitCommand(100),
                        new InstantCommand(() -> clawUpDown.setPos(ClawUpDown.PREAPER_SCORING_BACKWARD_SPECIMEN), clawUpDown),

                        new WaitCommand(100),

                        //go to score sec sample
                        new ParallelCommandGroup(
                                new ActionCommand(goToScoreSecSample.build()),
                                new SequentialCommandGroup(
                                        new WaitUntilCommand(
                                                () -> autoDriveTrain.getMecanumDrive().localizer.getPose().position.y > -55
                                        ),
                                        new PreaperForScoreSpecimen(elbowArm, extenderArm, claw, clawRollRotat, clawUpDown)
                                )
                        ),


                        //score sec sample
                        new InstantCommand(() -> clawUpDown.setPos(ClawUpDown.SCORE_SPECIMEN), clawUpDown),
                        new ElbowArmCommand(elbowArm,135),
                        new WaitCommand(200),
                        new ParallelCommandGroup(
                                new ActionCommand(backUpAfterScoringSecSample.build()),
                                new SequentialCommandGroup(
//                                        new WaitUntilCommand(
//                                        ()-> autoDriveTrain.getMecanumDrive().localizer.getPose().position.y < -33
//                                        ),
                                        new WaitCommand(450),
                                        new ClawSetPose(claw,Claw.OPEN)
                                    )
                        ),


                        //collecting third sample
                        new PrepareForCollectSpecimen3(extenderArm,elbowArm,clawRollRotat,clawUpDown,claw),

                        new ActionCommand(goToThirdSample.build()),
                        new ActionCommand(goToThirdSample2.build()),
                        new ActionCommand(goToHUmanPlayerSec.build()),

                        new ClawSetPose(claw,Claw.CLOSE),
                        new WaitCommand(200),
                        new InstantCommand(() -> clawUpDown.setPos(ClawUpDown.PREAPER_SCORING_BACKWARD_SPECIMEN), clawUpDown),

                        new WaitCommand(100),

                        //go to score third sample
                        new ParallelCommandGroup(
                                new ActionCommand(goToScoreThirdSample.build()),
                                new SequentialCommandGroup(
                                        new WaitUntilCommand(
                                                () -> autoDriveTrain.getMecanumDrive().localizer.getPose().position.x > -55
                                        ),
                                        new PreaperForScoreSpecimen(elbowArm, extenderArm, claw, clawRollRotat, clawUpDown)
                                )
                        ),

                        //score third sample
                        new InstantCommand(() -> clawUpDown.setPos(ClawUpDown.SCORE_SPECIMEN), clawUpDown),
                        new ElbowArmCommand(elbowArm,135),
                        new WaitCommand(500),
                        new ParallelCommandGroup(
                                new ActionCommand(backUpAfterScoringThirdSample.build()),
                                new SequentialCommandGroup(
//                                        new WaitUntilCommand(
//                                                ()-> autoDriveTrain.getMecanumDrive().localizer.getPose().position.y < -37
//                                        ),
                                        new WaitCommand(450),
                                        new ClawSetPose(claw,Claw.OPEN)
                                )
                        ),

                        new PrepareForCollectSpecimen3(extenderArm,elbowArm,clawRollRotat,clawUpDown,claw),

                        new ActionCommand(goToHUmanPlayerThird.build()),
                        new ClawSetPose(claw,Claw.CLOSE)

//                        //collect forth sample
//                        new ClawSetPose(claw,Claw.CLOSE),
//                        new InstantCommand(() -> clawUpDown.setPos(ClawUpDown.PREAPER_SCORING_BACKWARD_SPECIMEN), clawUpDown),
//                        //go to score forth sample
//                        new ParallelCommandGroup(
//                                new ActionCommand(goToScoreForth.build()),
//                                new SequentialCommandGroup(
//                                        new WaitUntilCommand(
//                                                () -> autoDriveTrain.getMecanumDrive().localizer.getPose().position.x < 15
//                                        ),
//                                        new PreaperForScoreSpecimen(elbowArm, extenderArm, claw, clawRollRotat, clawUpDown)
//                                )
//                        ),
//                        new ActionCommand(goToScoreForth1.build()),
//                        new InstantCommand(() -> clawUpDown.setPos(ClawUpDown.SCORE_SPECIMEN), clawUpDown),
//                        new WaitCommand(200),
//                        new ClawSetPose(claw,Claw.OPEN),
//                        //back up after scoring forth sample
//                        new ActionCommand(BackUpForth.build()),
//                        new ActionCommand(park.build())
                )
        );


    }
}
