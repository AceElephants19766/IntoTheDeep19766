package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class RedLeftSpicimen {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(700);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 18)
                .build();

        Pose2d initialPose = new Pose2d(-40, -62, Math.toRadians(90));

        TrajectoryActionBuilder redLeftPrepaerForSpicimen = myBot.getDrive().actionBuilder(
                       initialPose
                )
                .setTangent(Math.toRadians(0))
                .splineToSplineHeading(
                        new Pose2d(-10, -35, Math.toRadians(90)),
                        Math.toRadians(90) //tangent
                );

        TrajectoryActionBuilder redLeftBackingUpAfterSpicimen = redLeftPrepaerForSpicimen.endTrajectory().fresh()
                .splineToConstantHeading(new Vector2d(0, -42), Math.toRadians(90));

        TrajectoryActionBuilder redLeftGoToSamples = redLeftBackingUpAfterSpicimen.endTrajectory().fresh()
                .setTangent(Math.toRadians(-90))
                .splineToConstantHeading(new Vector2d(-34, -40), Math.toRadians(90));

        myBot.runAction(
                new SequentialAction(
                        redLeftPrepaerForSpicimen.build(),
                        new SleepAction(2),
                        redLeftBackingUpAfterSpicimen.build(),
                        redLeftGoToSamples.build()
                )
        );


        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}