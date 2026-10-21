package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.algorithm.Foresight;
import com.pedropathing.algorithm.ForesightConfig;
import com.pedropathing.controllers.Controller;
import com.pedropathing.drivetrain.Drivetrain;
import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Localizer;
import com.pedropathing.math.Matrix;
import com.pedropathing.math.Vector2D;
import com.pedropathing.revhub.drivetrains.Mecanum;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.pedropathing.revhub.localizers.PinpointConfig;
import com.pedropathing.revhub.localizers.PinpointLocalizer;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Constants {
    /** AutoTune values for the drivetrain, localizer, and Foresight have been applied. */
    public static boolean TUNED = true;

    public static MecanumConfig drivetrainConfig = new MecanumConfig(c -> {
        c.frontLeftName.set("fl");
        c.frontRightName.set("fr");
        c.backLeftName.set("bl");
        c.backRightName.set("br");
        c.frontLeftDirection.set(DcMotorSimple.Direction.REVERSE);
        c.frontRightDirection.set(DcMotorSimple.Direction.FORWARD);
        c.backLeftDirection.set(DcMotorSimple.Direction.REVERSE);
        c.backRightDirection.set(DcMotorSimple.Direction.FORWARD);
    });
    public static PinpointConfig localizerConfig = new PinpointConfig(c -> {
        c.name.set("odo");
        c.podType.set(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        c.xPodOffset.set(1.7411260529765933);
        c.yPodOffset.set(2.8340803732083537);
        c.xPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
        c.yPodDirection.set(GoBildaPinpointDriver.EncoderDirection.REVERSED);
        c.globalDistanceUnit.set(DistanceUnit.INCH);
        c.offsetUnits.set(DistanceUnit.INCH);
    });
    public static ForesightConfig foresightConfig = new ForesightConfig(
            c -> {
                Controller primaryTranslationalForward = Controller.proportional(0.21453120725138056);
                Controller secondaryTranslationalForward = Controller.proportional(0.07926354869995263);
                Controller primaryTranslationalLateral = Controller.proportional(0.6040820751469075);
                Controller secondaryTranslationalLateral = Controller.proportional(0.22319218539645452);

                c.forwardTranslational.set(Controller.piecewise(secondaryTranslationalForward).put(2.5, primaryTranslationalForward));
                c.strafeTranslational.set(Controller.piecewise(secondaryTranslationalLateral).put(2.5, primaryTranslationalLateral));

                c.coast.set(Controller.proportionalFeedforward(0.012029038986777858));
                c.brake.set(Controller.proportionalFeedforward(0.01022468313876118));

                c.headingFeedback.set(Controller.proportional(2.2647109684583615));
                c.headingBrakeCoefficients.set(Vector2D.cartesian(0.04309343491905225, 0.0056021109414757944));

                c.linearBrakeCoefficients.set(Matrix.diag(0.04505418529263715, 0.056714211738284045));
                c.quadraticBrakeCoefficients.set(Matrix.diag(0.0024626610406869296, 0.0020272202255675886));

                c.maxAchievableForwardVelocity.set(82.84211053388537);
                c.maxAchievableStrafeVelocity.set(58.48790051044408);
                c.naturalForwardDeceleration.set(37.37052892913414);
                c.naturalStrafeDeceleration.set(50.76577351258863);
            }
    );
    public static Drivetrain createDrivetrain(HardwareMap h) {
        return new Mecanum(h, drivetrainConfig);
    }

    public static Localizer createOdometryLocalizer(HardwareMap h) {
        return new PinpointLocalizer(h, localizerConfig);
    }

    public static Follower create(HardwareMap h) {
        return new Follower(createOdometryLocalizer(h), createDrivetrain(h), new Foresight(foresightConfig));
    }
}
