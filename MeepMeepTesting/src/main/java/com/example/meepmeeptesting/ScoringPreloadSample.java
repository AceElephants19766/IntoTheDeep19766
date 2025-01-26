package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class ScoringPreloadSample {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(700);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 18)
                .build();

        Pose2d initialPose = new Pose2d(-8, -62, Math.toRadians(90));

        TrajectoryActionBuilder goToBasket = myBot.getDrive().actionBuilder(
                        initialPose
                )
                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(
                        new Pose2d(-50, -50, Math.toRadians(45)),
                        Math.toRadians(180)
                );
        TrajectoryActionBuilder goToParkAtBar = goToBasket.endTrajectory().fresh()
                .setTangent(Math.toRadians(90))
                .splineToSplineHeading(
                        new Pose2d(-25,-10,Math.toRadians(0)),
                        Math.toRadians(0)
                );
        TrajectoryActionBuilder parkAtBar = goToParkAtBar.endTrajectory().fresh()
                .setTangent(Math.toRadians(0))
                .splineToConstantHeading(
                        new Vector2d(-25,-10),
                        Math.toRadians(0)
                );

        TrajectoryActionBuilder park = goToBasket.endTrajectory().fresh()
                .setTangent(Math.toRadians(0))
                .splineToSplineHeading(
                        new Pose2d(36, -62, Math.toRadians(90)),
                        Math.toRadians(0)
                );


        myBot.runAction(
                new SequentialAction(
                        goToBasket.build(),
                        goToParkAtBar.build(),
                        parkAtBar.build()
                )
        );
        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();

    }
}