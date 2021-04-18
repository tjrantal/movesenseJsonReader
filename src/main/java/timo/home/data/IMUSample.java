package timo.home.data;

public class IMUSample implements Comparable<IMUSample>{
	public int index;
	public long tStamp;
	public double ax;
	public double ay;
	public double az;
	public double gx;
	public double gy;
	public double gz;
	public double mx;
	public double my;
	public double mz;
	public IMUSample(long tStamp
					,double ax,double ay,double az
					,double gx,double gy,double gz
					,double mx,double my,double mz
					,int index){
		this.tStamp = tStamp;
		this.ax = ax;
		this.ay = ay;
		this.az = az;
		this.gx = gx;
		this.gy = gy;
		this.gz = gz;
		this.mx = mx;
		this.my = my;
		this.mz = mz;
		this.index = index;
						
	}
	
	/*Enable sorting the samples*/
	public int compareTo(IMUSample a){
		if (this.tStamp < a.tStamp){
			return -1;
		}
		if (this.tStamp == a.tStamp){
			if (this.index < a.index){
				return -1;
			}
			if (this.index == a.index){
				return 0;
			}
		}
		return 1;
	}
     
}