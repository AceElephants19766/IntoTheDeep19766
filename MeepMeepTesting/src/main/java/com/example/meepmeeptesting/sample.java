package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class sample {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(700);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 18)
                .build();

        Pose2d initialPose = new Pose2d(-32, -62, Math.toRadians(90));

        TrajectoryActionBuilder preLoad = myBot.getDrive().actionBuilder(
                        initialPose
                )
                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(
                        new Pose2d(-55, -55, Math.toRadians(45)),
                        Math.toRadians(180)
                );

        TrajectoryActionBuilder Sample = preLoad.endTrajectory().fresh()
                .setTangent(Math.toRadians(0))
                .strafeToLinearHeading(
                        new Vector2d(-47,-40),
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
                        new Vector2d(-56,-40),
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
                        new Vector2d(-55,-28),
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
                        new Pose2d(-25,-10,Math.toRadians(180)),
                        Math.toRadians(0)
                );


        myBot.runAction(
                new SequentialAction(
                        preLoad.build(),
                        Sample.build(),
                        Basket.build(),
                        secSample.build(),
                        Basket2.build(),
                        ThirdSample.build(),
                        Basket3.build(),
                        goToParkAtBar.build()
                )
        );

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();

    }
}
