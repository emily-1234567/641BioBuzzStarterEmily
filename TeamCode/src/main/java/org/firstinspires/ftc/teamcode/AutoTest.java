package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.pedro.Constants;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import static com.pedropathing.api.Paths.*;
import com.pedropathing.paths.Path;
import com.pedropathing.ivy.Scheduler;
import com.pedropathing.ivy.Command;
import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.commands.Commands.waitMs;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;

@Autonomous(name="AutoTest")
public class AutoTest extends OpMode {
    private final PoseFactory poseFactory = PoseFactory.degrees();
    // our poses
    private final Pose start = poseFactory.of(58, 8.5517, 90);
    private final Pose point1 = poseFactory.of(58, 33.1511, 90);
    private final Pose point2 = poseFactory.of(8.9357, 105.7173, 93.7958);
    private final Pose point2Control1 = poseFactory.of(13.2846, 36.7992, 0);
    private Follower follower; // <-- add this back

    public Path path1() {
        return line(start, point1).tangent();
    }

    public Path path2() {
        return curve(point1, point2Control1, point2).tangent();
    }
    private Command autoRoutine() {
        return sequential(
                follow(follower, path1()),
                // Add mechanism commands here.
                waitMs(500),
                follow(follower, path2())
        );
    }
    //poses from before
    @Override
    public void init() {
        Scheduler.reset();
        follower = Constants.create(hardwareMap);
        follower.setPose(start);
        follower.update();
    }

    @Override
    public void start() {
        schedule(autoRoutine());
    }

    @Override
    public void loop() {
        follower.update();
        Scheduler.execute();

        telemetry.addData("X", follower.pose().x());
        telemetry.addData("Y", follower.pose().y());
        telemetry.addData("Heading", Math.toDegrees(follower.pose().heading()));
        telemetry.addData("Follower Mode", follower.mode());
        telemetry.update();

    }
}
