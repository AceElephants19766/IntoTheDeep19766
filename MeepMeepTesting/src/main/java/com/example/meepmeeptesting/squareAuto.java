package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class squareAuto {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(700);
        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 17.5)
                .build();
        Pose2d initialPose = new Pose2d(15, -62, Math.toRadians(-90));

        TrajectoryActionBuilder PreLoad = myBot.getDrive().actionBuilder(
                        initialPose
                )
                .setTangent(Math.toRadians(90))
                .strafeTo(
                        new Vector2d(-4, -28)
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
                        new Vector2d(-2, -28)
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
                        new Vector2d(55, -12),
                        Math.toRadians(-90)
                );

        TrajectoryActionBuilder goToHUmanPlayerSec = goToSample3Sec.endTrajectory().fresh()
                .setTangent(Math.toRadians(-90))
                .strafeToLinearHeading(
                        new Vector2d(45, -59),
                        Math.toRadians(-90));

        TrajectoryActionBuilder goToScoreSec = goToHUmanPlayerSec.endTrajectory().fresh()
                .setTangent(Math.toRadians(90))
                .strafeTo(
                        new Vector2d(0, -28)
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
                        new Vector2d(3, -28)
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
                        new Vector2d(7, -28)
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
        myBot.runAction(
                new SequentialAction(
                        PreLoad.build(),
                        BackUp.build(),
                        goToSample.build(),
                        goToSample2.build(),
                        goToSample3.build(),
                        goToHUmanPlayer.build(),
                        goToScore.build(),
                        BackUpSec.build(),
                        goToSampleSec.build(),
                        goToSample2Sec.build(),
                        goToSample3Sec.build(),
                        goToHUmanPlayerSec.build(),
                        goToScoreSec.build(),
                        goToHUmanPlayerThird.build(),
                        goToScoreThird.build(),
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
