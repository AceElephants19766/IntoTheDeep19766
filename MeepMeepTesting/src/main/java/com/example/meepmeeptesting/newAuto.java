package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class newAuto {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(700);
        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 17.5)
                .build();
        Pose2d initialPose = new Pose2d(15, -62, Math.toRadians(90));

        TrajectoryActionBuilder PrepaerForSpicimen = myBot.getDrive().actionBuilder(
                        initialPose
                )
                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(
                        new Pose2d(4, -33,Math.toRadians(-90))
                        , Math.toRadians(90) //tangent
                );
        TrajectoryActionBuilder BackUp = PrepaerForSpicimen.endTrajectory().fresh()
                .setTangent(Math.toRadians(-90))
                .splineToConstantHeading(
                        new Vector2d(10,-45),Math.toRadians(-90));

        TrajectoryActionBuilder goToSample = BackUp.endTrajectory().fresh()
                .setTangent(-90)
                .splineToSplineHeading(
                        new Pose2d(36,-22,Math.toRadians(0)),
                                Math.toRadians(90));

        TrajectoryActionBuilder goToSample2 = goToSample.endTrajectory().fresh()
                .splineToSplineHeading(new Pose2d(45,-12,Math.toRadians(-90))
                        ,Math.toRadians(-90));

        TrajectoryActionBuilder goToHUmanPlayer = goToSample2.endTrajectory().fresh()
                .setTangent(Math.toRadians(-90))
                .splineToSplineHeading(
                        new Pose2d(40,-60,Math.toRadians(-90)),
                        Math.toRadians(-90));

        myBot.runAction(
                new SequentialAction(
                        PrepaerForSpicimen.build(),
                        BackUp.build(),
                        goToSample.build(),
                        goToSample2.build(),
                        goToHUmanPlayer.build()
                )
        );
        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}
