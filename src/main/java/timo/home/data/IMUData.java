package timo.home.data;

/**
 * Created by lipponep on 22.11.2017.
 * Modified from AccDataResponse by tjrantal at gmail dot com 2019
 */

import com.google.gson.annotations.SerializedName;

public class IMUData {

    @SerializedName("Timestamp")
	public final long timestamp;

	@SerializedName("ArrayAcc")
	public final Array[] accArray;
	
	
	@SerializedName("ArrayGyro")
	public final Array[] gyrArray;
	
	@SerializedName("ArrayMagn")
	public final Array[] magArray;

	public IMUData(long timestamp, Array[] accArray, Array[] gyrArray, Array[] magArray) {
		this.timestamp = timestamp;
		this.accArray = accArray;
		this.gyrArray = gyrArray;
		this.magArray = magArray;
	}

    public static class Array {
        @SerializedName("x")
        public final double x;

        @SerializedName("y")
        public final double y;

        @SerializedName("z")
        public final double z;

        public Array(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

}
