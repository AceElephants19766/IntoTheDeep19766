package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class specimen {
    public static void main(String[] args) {

        MeepMeep meepMeep = new MeepMeep(700);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 17.5)
                .build();


        Pose2d initialPose = new Pose2d(15, -62, Math.toRadians(-90));

        //scoring preload - done
        TrajectoryActionBuilder PreLoad = myBot.getDrive().actionBuilder(
                        initialPose
                )
                .setTangent(Math.toRadians(90))
                .splineToConstantHeading(
                        new Vector2d(-4, -28),
                        Math.toRadians(90)
                );
        TrajectoryActionBuilder BackUpAfterScoringPreload = PreLoad.endTrajectory().fresh()
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
                        new Vector2d(45, -59),
                        Math.toRadians(-90)
                );



        //score sec sample - done
        TrajectoryActionBuilder goToScoreSecSample = goToHUmanPlayer.endTrajectory().fresh()
                .setTangent(Math.toRadians(180))
                .strafeToLinearHeading(
                        new Vector2d(-2, -28),
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
                        new Vector2d(55, -59),
                        Math.toRadians(-90)
                );



        //score third sample - done
        TrajectoryActionBuilder goToScoreThirdSample = goToHUmanPlayerSec.endTrajectory().fresh()
                .setTangent(Math.toRadians(180))
                .strafeToLinearHeading(
                        new Vector2d(-2, -28),
                        Math.toRadians(-90)
                );

        //bring forth sample from human player
        TrajectoryActionBuilder goToHUmanPlayerThird = goToScoreThirdSample.endTrajectory().fresh()
                .setTangent(Math.toRadians(180))
                .strafeToLinearHeading(
                        new Vector2d(45, -59),
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

        myBot.runAction(
                new SequentialAction(

                        //scoring preload - done
                        PreLoad.build(),
                        BackUpAfterScoringPreload.build(),

                        //bring sec sample - done
                        goToSample.build(),
                        goToSample2.build(),
                        goToHUmanPlayer.build(),

                        //score sec sample - done
                        goToScoreSecSample.build(),
                        backUpAfterScoringSecSample.build(),

                        //bring third sample - done
                        goToThirdSample.build(),
                        goToThirdSample2.build(),
                        goToHUmanPlayerSec.build(),

                        //score third sample
                        goToScoreThirdSample.build(),

                        //bring forth sample
                        goToHUmanPlayerThird.build(),

                        //score forth sample
                        goToScoreForthSample.build(),

                        //park
                        park.build()
                )
        );

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}
